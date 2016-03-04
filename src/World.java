import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class World extends BuildWorld {
    private ArrayList<Room> rooms;
    private Room currentRoom;
    private int numberOfRooms;
    private Avatar player;
    private int sfinxRoomIndex;
    private ArrayList<Book> books;
    private ArrayList<Course> courses;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;

    public World () {
	numberOfRooms = 0;
    }

    public Avatar getAvatar() {
	return this.player;
    }

    public int getNumberOfRooms() {
	return this.numberOfRooms;
    }

    public ArrayList<Room> getRooms() {
	return this.rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
	this.rooms = rooms;
    }


    public void createWorld(String name) {
        
	this.rooms = createRooms("rooms.txt");
	this.numberOfRooms = rooms.size();
	System.out.print("Number of rooms: " + numberOfRooms + "\n");
		
	this.sfinxRoomIndex = placeSfinx();

	this.books = createBooks("books.txt");
	this.courses = createCourses("courses.txt", this.books);
	this.teachers = createTeachers("teachers.txt", this.courses);
	this.students = createStudents("students.txt", this.courses);

	this.player = createAvatar(courses, name);
	
	placeKeys();
	placeBooks();
	placeCreatures();
	//printRooms();
		
	System.out.println(currentRoom);	
    }


    public void runGame() {
	Scanner reader = new Scanner(System.in);
	Boolean continueGame = true;
	
	while (continueGame) {
	    reader = new Scanner(System.in);
	    System.out.print("What's next?\n> ");
	    String input = reader.nextLine();
	    String[] splitInput = input.split(" ");
	    Boolean twoWords = (splitInput.length >= 2);
	    Boolean threeWords = (splitInput.length >= 3);
	    
	    switch (splitInput[0].toLowerCase()) {
	    case "help": printHelp(); break;
		
		//EX2
	    case "show": printShow(twoWords, splitInput[1]); break;

	    case "drop":
		if (twoWords)
		    drop(splitInput); break;
	    case "enroll": 
		if (twoWords)
		    enrollCourse(splitInput); break;
				
	    case "go":	
		if (twoWords) 
		    go(splitInput[1]); break;
				
	    case "inventory": printInventory(); break;

	    case "pick":
		if (threeWords && splitInput[1].toLowerCase().equals("up")) 
		    pickUp(splitInput); break;
					
	    case "talk": 
		if (twoWords) 
		    talk(splitInput[1]); break;

	    case "trade":
		if (twoWords)
		    trade(splitInput[1]); break;
				
	    case "use": 
		if (threeWords && splitInput[1].toLowerCase().equals("key"))
		    useKey(splitInput[2]); break;
			
			
	    case "exit": continueGame = false; break;

	    case "cheat": player.cheat(); break;
				
	    default: 
		System.out.println("Unvalid input");
		printHelp(); 
		break;
	    }
	    reader.reset();
	}
		

	reader.close();
    }

    private void printShow(Boolean twoWords, String splitInput) {
		if (twoWords && splitInput.toLowerCase().equals("hp")) 
		    System.out.println("Current HP: " + player.getHp());
		if (twoWords && splitInput.toLowerCase().equals("ongoing"))
		    player.printOngoing();
		if (twoWords && splitInput.toLowerCase().equals("completed"))
		    player.printCompleted();
	}

    private void printHelp() {
	System.out.println(currentRoom);
	String help = "--------------------------------------------\n";
	help += "COMMANDS\tEXPLANATION\n";
	help += "help\t\tshows this help menu\n";
	help += "drop x\t\tdrop item x\n";
	help += "enroll x\tenroll the course x\n";
	help += "go x\t\tgoes to the room in direction x\n";
	help += "inventory\tshows items in your backpack\n";
	help += "pick up x\tpick up item x\n";
	help += "show hp \tshows your current hp\n";
	help += "talk x\t\ttalk to the student with name x\n";
	help += "trade x\t\ttrade a book with student x\n";
	help += "show ongoing\tshow ongoing courses\n";
	help += "show completed\tshow completed courses\n";
	help += "use key x\tunlock the door in direction x\n";
	help += "exit\t\texit game\n";	
		
	help += "";
	help += "";
	help += "";
	help += "--------------------------------------------\n";

	System.out.println(help);
    }

    private void enrollCourse(String[] enrollInfo) { //om det ej finns en lärare i rummet?
	String courseName = enrollInfo[1];
	for (int i = 2; i < enrollInfo.length; i++) {
	    courseName += (" " + enrollInfo[i]);
	}
	Teacher t = currentRoom.teacherInRoom();
	if (t != null) {
	    String teachersCourse = t.getCourseName();
	    if (teachersCourse.equalsIgnoreCase(courseName)) {
		player.addOngoing(teachersCourse);
		System.out.println("You've enrolled " + teachersCourse);
	    }
	    else
		System.out.print("Unvalid course name: " + courseName); 
	}
    }

  private void go(String direction) {
	Room newRoom = currentRoom.move(direction);
	if (newRoom != null) {
	    this.currentRoom = newRoom;
	    System.out.println(currentRoom);
	    currentRoom.enterRoom();
	}
    }

    private void printInventory() {
	String s;
	if (player != null) {
	    s = "--------------------------------------------\n";
	    s += player.backpackToString() + "\n";
	    s += "--------------------------------------------\n";
	    System.out.println(s);
	}
    }
	

    private void pickUp(String[] input) {
	String item = "";
	for (int i = 2; i < input.length; i++){
	    item += input[i];

	    if (i < input.length -1) {
		item += " ";
	    }
	}
	
       	Item i = null;
	if(item.toLowerCase().equals("key")) {
	    i = currentRoom.hasKey();
	} else {
	    i = currentRoom.hasBook(item);
	}
	
	if (i != null) {
	    Boolean pickedup = player.pickUp(i);
	   
	    if (pickedup) {
		currentRoom.removeItem(i);	
		System.out.println("You picked up " + item);
	    } else {
		System.out.println("There is no " + item + " to pick up! Stop trying to cheat!");
	    }
	}       
    }

    private void drop(String[] input) {
	String item = "";
	for (int i = 1; i < input.length; i++){
	    item += input[i];

	    if (i < input.length -1) {
		item += " ";
	    }
	}
	
       	Item i = null;
	if(item.toLowerCase().equals("key")) {
	    i = player.hasKey();
	} else {
	    i = player.hasBook(item);
	}	   
	if (i != null) {
	    Boolean dropped = player.drop(i);
	    currentRoom.addToRoom(i);
	    if (dropped) System.out.println("You dropped " + item);
	} else {
	    System.out.println("There is no " + item + " to drop!");
	}
    }  


    //todo: inget krav enligt specifikationen men om vi orkar kan vi
    //fixa så att lärarna kan prata
    private void talk(String studentName) {
	if (studentName == null) System.out.println("Unvalid input");
	Student s = currentRoom.studentInRoom(studentName);
	if (s != null) {
	    String words = s.talk();
	    System.out.println(words); 
	}		
	else System.out.println("That's not a student.");
    }
    
    private void trade(String studentName){
	
	Student s = currentRoom.studentInRoom(studentName);
	if (s != null) {
	    
	    Book studentBook = s.getCompletedBook();
	    Course studentCourse = s.getOngoingCourse();
	    Book wantedBook = studentCourse.getBook();
	    Book avatarBook = player.hasBook(wantedBook.getName());
	    if (wantedBook.equals(avatarBook)) {
	        player.drop(avatarBook);
		s.setOngoingBook(avatarBook);
		System.out.println("You just traded a book with " + s.getName());
		Boolean pickedUp = player.pickUp(studentBook);
		if (pickedUp == false) {
		    System.out.println(s.getName() + " says: I'll put it on the floor instead.");
		    currentRoom.addToRoom(studentBook);
		}
        
	    }
	    else {
		System.out.println(s.getName() + " says: You don't have the book I want. I want the book "+ wantedBook.getName() + "." );
	    }
	}
	else System.out.println("That's not a student.");
    }

    private void useKey(String direction) {
	currentRoom.unlockDoor(direction);

    }


    private Room findRoom(String roomName) {
	for (int i = 0; i < numberOfRooms; i++) {
	    Room crnt = rooms.get(i);
	    if ((crnt.getName()).compareToIgnoreCase(roomName) == 0) return crnt;
	}
	return null;
    }

    private int placeSfinx() {
	Sfinx s = new Sfinx("Sfinx");
	Random r = new Random();
	int sfinxIndex = r.nextInt(numberOfRooms);
	Room sfinxRoom = rooms.get(sfinxIndex);

	while (!(sfinxRoom.hasOneDoor())) {
	    sfinxIndex = r.nextInt(numberOfRooms);
	    sfinxRoom = rooms.get(sfinxIndex);
	}
	sfinxRoom.addToRoom(s);
	return sfinxIndex;
    }
	
    private void placeKeys() {
	double numberOfKeys =  numberOfRooms * 1.5;
		
	for (int i = 0; i < numberOfKeys; i++) {
	    Key newKey = new Key();
	    placeRandom(newKey);
	}
    }
    private void placeCreatures() {
	//place Avatar
	placeRandom(this.player);

	//place teachers
	int j = 0;
	for (int i = 0; j < teachers.size() && i < numberOfRooms-1; i++) {
	    Teacher t = teachers.get(j);
	    if (i != sfinxRoomIndex) {
		Room tRoom = rooms.get(i);
		tRoom.addToRoom(t);
		j++;
	    }
	    else i++;
	}

	//place students 
	for (int i = 0; i < students.size(); i++) {
	    Student s = students.get(i);
	    placeRandom(s);
	}
    }

    //EX3
    private void placeBooks() {
	int numberOfCourses = courses.size();
	int numberOfStudents = students.size();
		
	for (int i = numberOfStudents; i < numberOfCourses; i++) { 
	    Course c = courses.get(i);
	    Book b = c.getBook();
	    System.out.println("In room: " + b);
	    placeRandom(b);
	}
    }
    private void placeRandom(Object o) {
	Random r = new Random();
	int roomIndex = r.nextInt(numberOfRooms);
	while(roomIndex == sfinxRoomIndex) {
	    roomIndex = r.nextInt(numberOfRooms);
	}
	Room randomRoom = rooms.get(roomIndex);
	randomRoom.addToRoom(o);

	if (o instanceof Avatar) {
	    this.currentRoom = randomRoom;
	}
    }

    //EX1
    // TODO: Ta bort när allt är klart!
    public void printRooms() {
	for (int i = 0; i < numberOfRooms; i++) {
	    Room crnt = rooms.get(i);
	    System.out.println(crnt);
	}
    }
}
