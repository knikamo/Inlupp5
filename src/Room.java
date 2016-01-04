import java.util.ArrayList;
import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;
import java.lang.Math;

public class Room /*implements ListToString*/ {
    private String name;
    private Room[] rooms;
    private Boolean[] openDoors;
    private ArrayList<Item> items;
    private ArrayList<Creature> creatures;

    /*
      public Room(String name, Boolean[] od, ArrayList<Item> i, ArrayList<Creature> c) {
      this.name = name;
      this.openDoors = od;
      this.items = i;
      this.creatures = c;
      } */

    public Room(String name, Boolean[] od) {
	this.name = name;
	this.openDoors = od;
	this.items = new ArrayList<Item>();
	this.creatures = new ArrayList<Creature>();
    }

    public String getName() throws NullPointerException {
	return this.name;
    }

    public void setRooms(Room[] rooms) {
	this.rooms = rooms;
    }
    public void openDoor(int i) {
	openDoors[i] = true;
    }
    public void unlockDoor(String direction) {
	int i = directionToInt(direction);
	if (i > 4) return;

	Avatar a = avatarInRoom();
	Key k = a.hasKey();
	if (k == null) {
	    System.out.println("You don't have a key");
	    return;
	}

	String neighbourRoom = neighbourRoom(i);
	if (neighbourRoom.equals("Open")) {
	    System.out.println("Door is already unlocked");
	}
	else if (neighbourRoom.equals("Closed")) {
	    //Use Key
	    a.drop(k);
	    openDoors[i] = true;
	    rooms[i].openDoor((i+2) % 4); //inte i!!! 
	    System.out.println("Door to room '" + rooms[i].getName() + "' unlocked!");

	}
	else {
	    System.out.println("That's just a wall.");
	}
    }
    private int directionToInt(String direction) {
	int i;
	switch (direction) {
	case "north": i = 0; break;
	case "east": i = 1; break;
	case "south": i = 2; break;
	case "west": i = 3; break;
	default: System.out.println("'" + direction + "' is not a direction"); i = 5;
	}
	return i;
    }

    private String neighbourRoom(int direction) {
	if (rooms[direction] == null) return "Wall"; //Wall
	else if (openDoors[direction]) return "Open"; //Opened Door
	else return "Closed"; //Locked Door
    }

    public Room move(String direction) {
	int i = directionToInt(direction);
	if (i > 3) return null;

	Room nextRoom = rooms[i];
	if (openDoors[i] && nextRoom != null) {
			
	    Avatar a = avatarInRoom();
	    nextRoom.addToRoom(a);
	    //delete avatar from this room
	    Boolean removed = (this.creatures).remove(a);

	    if (removed) {
		System.out.println("You just went through the door! Woho!");
	    }
	    return nextRoom;				
	}
	else if (rooms[i] == null) {
	    System.out.println("That's just a wall, you can't go there.");
	    return null;
	}
	else {
	    System.out.println("The door is locked. You have to use a key to unlock it.");
	    return null;
	}
    }

    public void enterRoom() {
	Teacher t = teacherInRoom();
	Avatar a = avatarInRoom();

	// TODO: Bryt ut till en funktion
	if (t != null) {
	    Boolean hasBook = a.hasBook(t.getBook().getName());
	    String courseName = t.getCourseName();
	    Boolean completedCourse = a.completedCourse(courseName);
	    Boolean ongoingCourse = a.ongoingCourse(courseName);
	    int achievedCredits = t.askQuestion(completedCourse, ongoingCourse, hasBook);
	    a.changeCredits(achievedCredits);
	}
    }
    public void addToRoom(Object o) {
	if (o instanceof Item) {
	    Item i = (Item) o;
	    items.add(i);
	}
	else if (o instanceof Creature) {
	    Creature c = (Creature) o;
	    creatures.add(c);
	}
	else {
	    System.out.println("Not a valid object to put in room");
	}
    }

    public Avatar avatarInRoom() {
	for (int i = 0; i < creatures.size(); i ++) {
	    Creature crnt = creatures.get(i);
	    if (crnt instanceof Avatar) {
		Avatar a = (Avatar) crnt;
		return a;
	    }
	}
	return null;
    }

    public Teacher teacherInRoom() {
	for (int i = 0; i < creatures.size(); i ++) {
	    Creature crnt = creatures.get(i);
	    if (crnt instanceof Teacher) {
		Teacher t = (Teacher) crnt;
		return t;
	    }
	}
	return null;
    }

    public Sfinx sfinxInRoom() {
	for (int i = 0; i < creatures.size(); i ++) {
	    Creature crnt = creatures.get(i);
	    if (crnt instanceof Sfinx) {
		Sfinx s = (Sfinx) crnt;
		return s;
	    }
	}
	return null;
    }
	
    public Student studentInRoom() {
	for (int i = 0; i < creatures.size(); i ++) {
	    Creature crnt = creatures.get(i);
	    if (crnt instanceof Student) {
		Student s = (Student) crnt;
		return s;
	    }
	}
	return null;
    }

    public String toString() {
	String studentString = "";
	String teacherString = "";
	String itemString = "";
	try {
	    itemString = arrListToString(items); } 
	catch (IndexOutOfBoundsException e) {
	    itemString = "-----";
	}
	try {
	    studentString = arrListToString(creatures, true); } 
	catch (IndexOutOfBoundsException e) {
	    studentString = "-----";
	}
	try {
	    teacherString = arrListToString(creatures, false); } 
	catch (IndexOutOfBoundsException e) {
	    teacherString = "-----";
	}

	String s = "============================================\n";
	s += "Room: " + name + "\n";
	s += "Items: " + itemString + "\n";
		
	s += "Students: " + studentString + "\n";
	if (sfinxInRoom() == null) {
	    s += "Teachers: " + teacherString + "\n"; }
	else {
	    s += "Creatures: Sfinx\n"; 
	}
	s += "Doors from this room: \n" + roomsToString();
	s += "============================================";
	return s;
    }
    private String openToString(int index) {
	if (openDoors[index]) return "(open)";
	else return "(locked)";
    }
    private String roomsToString() {
	String s;
	String[] neighbourNames = new String[4];
		
	for (int i = 0; i < 4; i++) {
	    try {
		neighbourNames[i] = rooms[i].getName(); 
		neighbourNames[i] += " " + openToString(i);
	    }
	    catch (NullPointerException e) {
		neighbourNames[i] = "-----";
	    }
	}

	s = " * N: " + neighbourNames[0] + "\n";
	s += " * E: " + neighbourNames[1] + "\n";
	s += " * S: " + neighbourNames[2] + "\n";
	s += " * W: " + neighbourNames[3] + "\n";

	return s;
    }

    public boolean hasOneDoor() {
	int numberOfDoors = 0;
	for (int i = 0; i < 4; i++){
	    if(rooms[i] != null) numberOfDoors++;
	}
	return (numberOfDoors == 1);
    }

    public String arrListToString(ArrayList arrList, boolean student) throws IndexOutOfBoundsException {
	String s = "";
	Object current = arrList.get(0);
	Boolean stud = student && ((current instanceof Student) || (current instanceof Avatar));
	Boolean teach = (!(student)) && (current instanceof Teacher);

	if (stud || teach)
	    s = current.toString();	

	for (int i = 1; i < arrList.size(); i++) {
	    current = arrList.get(i);

	    stud = student && ((current instanceof Student) || (current instanceof Avatar)); //(!(current instanceof Teacher));
	    teach = (!(student)) && (current instanceof Teacher);
	    if (stud || teach) {
		if (!(s.equals(""))) s += ", ";
			
		s += current.toString(); }
	}
	return s;
    }

    public String arrListToString(ArrayList arrList) throws IndexOutOfBoundsException {
	String s;

	Object current = arrList.get(0);
	s = current.toString();	

	for (int i = 1; i < arrList.size(); i++) {
	    s += ", ";
	    current = arrList.get(i);
	    s += current.toString(); 
	}
	return s;
    }

}
