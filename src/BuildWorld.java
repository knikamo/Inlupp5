import java.util.ArrayList;
import java.util.Random;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.management.RuntimeErrorException;
import java.lang.Exception;
/** This class contains methods to build the MUD-game world */
abstract class BuildWorld {

    private static ArrayList<String[]> textfileToSringArr(String filename) throws EmptyFileException {
	ArrayList<String> lines = new ArrayList<String>();
	try {
	    lines = readTextFile(filename);
	} 
	catch (FileNotFoundException e) {
	    System.out.println("File not found....  " + e.getMessage());
	    System.exit(1);
	}
	catch (IOException e) {
	    System.out.println("Caught IOException...." + e.getMessage());
	    System.exit(1);
	} 

	if (lines.isEmpty()) throw new EmptyFileException("This file is empty");

	ArrayList<String[]> allInfo = new ArrayList<String[]>();
		
	for (int i = 0; i < lines.size(); i++) {
	    String singleLine = lines.get(i);
	    String[] splittedLine = singleLine.split(";");
	    allInfo.add(i, splittedLine);
	}
		
	return allInfo;
    }


	
    private static ArrayList<String> readTextFile(String fileName) throws FileNotFoundException, IOException {
	BufferedReader br = new BufferedReader(new FileReader(fileName));
	ArrayList<String> allLines = new ArrayList<String>();
	String line;
	
	while ((line = br.readLine()) != null) {
	    allLines.add(line);
		
	}
	return allLines;

    }
    /** Creates rooms with input from a file.
     * <pre> The file is not empty and each line of the file represents a room on the format: 
     * roomName;northRoomName;eastRoomName;southRoomName;westRoomName;northDoor;eastDoor;southDoor;westDoor 
     * roomName must match one of the roomNames in the file
     * At least one of the rooms must have only one door.</pre>
     * @param filename The name of the file containing information about the rooms
     * @return a list of rooms
     */
    
    public static ArrayList<Room> createRooms(String filename) {
	ArrayList<String[]> allRoomsInfo = new ArrayList<String[]>();
	try {
	    allRoomsInfo = textfileToSringArr(filename);
	} catch (EmptyFileException e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
	
	ArrayList<Room> gameMap = new ArrayList<Room>();

	for (int i = 0; i < allRoomsInfo.size(); i++) {
	    String currentName = (allRoomsInfo.get(i))[0];
	    Boolean[] currentOpenDoors = convertToBoolean(allRoomsInfo.get(i));
	    Room currentRoom = new Room(currentName, currentOpenDoors);
	    gameMap.add(currentRoom);
	}

	connectRooms(gameMap, allRoomsInfo);	
	return gameMap;
    }

    /** Creates books with input from a file.
     * <pre> The file is not empty and each line of the file represents a book on the format:
     * bookName;Author;year;volume </pre>
     * @param filename The name of the file containing information about the books
     * @return A list of books
     */

    public static ArrayList<Book> createBooks(String filename) {
	ArrayList<String[]> bookInfo = new ArrayList<String[]>();
	try {
	    bookInfo = textfileToSringArr(filename);
	} 
	catch (EmptyFileException e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}

	ArrayList<Book> allBooks = new ArrayList<Book>();

	for (int i = 0; i < bookInfo.size(); i++) {
	    String[] crntInfo = bookInfo.get(i);
	    String bookName = crntInfo[0];
	    String author = crntInfo[1];
	    int year = Integer.parseInt(crntInfo[2]);
	    double volume = Double.parseDouble(crntInfo[3]);
	    Book newBook = new Book(bookName, author, year, volume);
	    allBooks.add(newBook);
	}

	return allBooks;

    }

    private static Book findBook(ArrayList<Book> books, String name) {
	for (int i = 0; i < books.size(); i++) {
	    Book crnt = books.get(i);
		
	    if ((crnt.getName()).equals(name)) return crnt;
	}
	return null;
    }
      /** Creates courses with input from a file and connects each course with its book.
     * <pre> The file is not empty and each line of the file represents a course on the format: 
     * courseName;bookName;credits;question;alternativ1;alternative2;alternativ3;correctAlternative 
     * CorrectAlternative is "a" if alternative1 is the correct answer 
     * bookName matches the name of a book in the list of books</pre>
     * @param filename The name of the file containing information about the books
     * @param books A list of books
     * @return A list of courses
     */
    public static ArrayList<Course> createCourses(String filename, ArrayList<Book> books) {
	ArrayList<String[]> courseInfo = new ArrayList<String[]>();
	try {
	    courseInfo = textfileToSringArr(filename);
	} 
	catch (EmptyFileException e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
 		

	ArrayList<Course> courses = new ArrayList<Course>();

	for (int i = 0; i < courseInfo.size(); i++) {
	    String[] crntInfo = courseInfo.get(i);
	    String courseName = crntInfo[0];
	    String bookName = crntInfo[1];
	    Book book = findBook(books, bookName);
	    if (book == null) System.out.println("book is null");
	    int hp = Integer.parseInt(crntInfo[2]);
 			
	    String question = crntInfo[3];
	    String[] answers = new String[3];
	    answers[0] = crntInfo[4];
	    answers[1] = crntInfo[5];
	    answers[2] = crntInfo[6];
	    String correctAlt = crntInfo[7];

	    Course newCourse = new Course(courseName, book, hp, question, answers, correctAlt);
	    courses.add(newCourse);
	}
	return courses;
    }
    /** Creates teachers with input from a file and connects the teachers to a course.
     * @param filename The name of the file containing information about the teachers
     * @param courses A list of courses
     * <pre> The file is not empty and each line of the file represents a name of a teacher on the format: 
     * <br> Gunnar <br> Britta </pre>
     * @return A list of teachers
     */
    public static ArrayList<Teacher> createTeachers(String filename, ArrayList<Course> courses) {
	ArrayList<String[]> teacherInfo = new ArrayList<String[]>();
	try {
	    teacherInfo = textfileToSringArr(filename);
	} 
	catch (EmptyFileException e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
 		
	ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	for (int i = 0; (i < courses.size() && i < teacherInfo.size()); i++) {
	    String name = (teacherInfo.get(i))[0];
	    Course c = courses.get(i);
	    Teacher t = new Teacher(name, c);
	    teachers.add(t);
	}
	return teachers;
    }
    /** Creates students with input from a file and connect each student with a completed course and a ongoing course.
     * @param filename The name of the file containing information about the teachers
     * @param courses A list of courses
     * <pre> The file is not empty and each line of the file represents a name of a teacher on the format: 
     * <br> Karro <br> Patrik <br> The number of students is around half the amount of courses. </pre>
     * @return A list of students
     */

    public static ArrayList<Student> createStudents(String filename, ArrayList<Course> courses) {
	ArrayList<String[]> studentInfo = new ArrayList<String[]>();
	try {
	    studentInfo = textfileToSringArr(filename);
	} 
	catch (EmptyFileException e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
 		
	ArrayList<Student> students = new ArrayList<Student>();
	int limit = courses.size()-1;
	for (int i = 0; (i < studentInfo.size() && i < limit); i++) {
	    String[] crntInfo = studentInfo.get(i);
	    String name = crntInfo[0];
 			
	    Course ongoing = courses.get(limit-i);//.getName();
	    Course completed = courses.get(i); //.getName();
	    Book book = courses.get(i).getBook();
	    System.out.println("Student has: " + book);
	   
	    Student s = new Student(name, ongoing, completed, book);
	    students.add(s);
	}
	return students;
    }
    
    /** Creates a new avatar with 60hp, 6 completed courses and one key in the backpack.
     * @param courses The completed courses
     * @param name The name of the avatar
     * @return An avatar with 60hp, 6 completed courses and one key in the backpack
     */
    public static Avatar createAvatar(ArrayList<Course> courses, String name) {
	Avatar a = new Avatar(name);
	for(int i = courses.size() - 6; i < courses.size(); i++){
	    Course crntCourse = courses.get(i);
	    String crntName = crntCourse.getName();
	    int hp = crntCourse.getCredits();
	    a.addCompleted(crntName, hp);
	}
	return a;
    }
    
    private static void connectRooms(ArrayList<Room> rooms, ArrayList<String[]> allRoomsInfo) throws NonExistingRoomException {
	for (int i = 0; i < rooms.size(); i++) {
	    String[] crntInfo = allRoomsInfo.get(i);
	    String crntName = crntInfo[0];
	    Room crntRoom = findRoom(rooms, crntName);
			
	    Room[] conRooms = new Room[4];

	    for (int j = 0; j < 4; j++) {
		String connectedName = crntInfo[j+1];
				
		Room connectedRoom = findRoom(rooms, connectedName); 
				
		if (connectedName.equals("X")) {
		    conRooms[j] = null; 
		}

		else if (connectedRoom == null) {
		    throw new NonExistingRoomException("'" + connectedName + "' on row " + (i-1) + " in 'rooms.txt' not found.");
		}

		else {
		    conRooms[j] = connectedRoom;
		}
	    }
	    crntRoom.setRooms(conRooms);
	}
    }

    private static Room findRoom(ArrayList<Room> rooms, String roomName) {
	for (int i = 0; i < rooms.size(); i++) {
	    Room crnt = rooms.get(i);
		
	    if ((crnt.getName()).equals(roomName)) return crnt;
	}
	return null;
    }

    private static Boolean[] convertToBoolean(String[] roomArray) {
	Boolean[] openDoors = new Boolean[4];

	for (int i = 5; i < 9; i++) {
			
	    String door = roomArray[i];
	    if (door.equals("X") || door.equals("False") || door.equals("false")) {
		openDoors[i-5] = false;
	    }
	    else {
		openDoors[i-5] = true;
	    }
		
	}
	return openDoors;
    }

}
