import java.util.ArrayList;
import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;
import java.lang.Math;
/** Represents a room i the MUD-game */
public class Room /*implements ListToString*/ {
    /** The name of the room */
    private String name;
    /** The connecting rooms */
    private Room[] rooms;
    /** Information about the connecting doors */
    private Boolean[] openDoors;
    /** A list of items in the room */
    private ArrayList<Item> items;
    /** A list of creatures in the room */
    private ArrayList<Creature> creatures;

    /** Creates a new room with a name and information about the doors 
     * @param name The name of the room
     * @param od An array with information (open/closed) about the doors */
    public Room(String name, Boolean[] od) {
	this.name = name;
	this.openDoors = od;
	this.items = new ArrayList<Item>();
	this.creatures = new ArrayList<Creature>();
    }
    /** Returns the name of the room
     * @return the name of the room 
     */
    public String getName() throws NullPointerException {
	return this.name;
    }
    /** Denna funktion kan tas bort om vi tar bort först raden i world.java i funktionen pickUp */
    public ArrayList<Item> getItems() {
	return this.items;
    }
    /** Checks if a room has a key. 
     * @return a key if the room has a key, otherwise null */
    public Key hasKey() {
	for (int i = 0; i < items.size(); i++) {
	    Item item = items.get(i);
	    if (item instanceof Key) {
		Key k = (Key) item;
		return k;
	    }
	}
	return null;
    }

    /** Checks if a room has a specific book. 
     * @param bookName The name of the book.
     * @returnthe book if the room has it, otherwise null */
    public Book hasBook(String bookName) {
	for (int i = 0; i < items.size(); i++) {
	    Item item = items.get(i);
	    if (item instanceof Book) {
		Book b = (Book) item;
		String name = b.getName();
		if(name.equals(bookName)) {
		    return b;
		}
	    }
	}
	return null;
    }


    /** Removes an item from the room.
     * @param removeItem The item to be removed 
     * @return true if the item was succesfully removed from the room, otherwise false.
     */
    public Boolean removeItem (Item removeItem) {
	Boolean hasRemoved = (this.items).remove(removeItem);
	return hasRemoved;
    }
    /** Sets the neigbour rooms.
     * @param rooms An array of the four neighbour rooms.
     */
    public void setRooms(Room[] rooms) {
	this.rooms = rooms;
    }

    /** Opens a door to the room in one direction
     * @param i The direction (represented as an int) of the door to open.
     */
    public void openDoor(int i) {
	openDoors[i] = true;
    }
     /** Unlocks a door to one of the neigbour rooms.
     * @param direction The direction (represented as an string) of the door to unlock.
     */
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
	switch (direction.toLowerCase()) {
	case "north": i = 0; break;
	case "n": i = 0; break;
	case "east": i = 1; break;
	case "e": i = 1; break;
	case "south": i = 2; break;
	case "s": i = 2; break;
	case "west": i = 3; break;
	case "w": i = 3; break;
	default: System.out.println("'" + direction + "' is not a direction"); i = 5;
	}
	return i;
    }

    private String neighbourRoom(int direction) {
	if (rooms[direction] == null) return "Wall"; //Wall
	else if (openDoors[direction]) return "Open"; //Opened Door
	else return "Closed"; //Locked Door
    }
    /** Moves from the room to a neighbour room.
     * @param direction The direction to move.
     * @return if the door in the direction is unlocked: the room you just moved to, else null
     */
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
    /** Enter a room */
    public void enterRoom() {
	Sfinx sfinx = sfinxInRoom();
	Avatar a  = avatarInRoom();
	
	if(sfinx != null) {
	    sfinx.graduate(a);
	    return;
	} 
	Teacher t = teacherInRoom();

	if (t != null && a != null) {
	    t.askQuestion(a);
	}
    }
    /** Add an object to the room 
     * @param o The object you want to add to the room 
     */
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
    /** Checks if there is an avatar in the room
     * @return the avatar if it is in the room, else null.
     */
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
    /** Checks if there is a teacher in the room
     * @return the teacher if it is in the room, else null.
     */
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
    /** Checks if there is a sfinx in the room
     * @return the sfinx if it is in the room, else null.
     */
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
    /** Checks if there is an student in the room
     * @param name The name of the student to look for.
     * @return the student if it is in the room, else null.
     */	
    public Student studentInRoom(String name) {
	for (int i = 0; i < creatures.size(); i ++) {
	    Creature crnt = creatures.get(i);
	    if (crnt instanceof Student) {
		Student s = (Student) crnt;
		if (name.equalsIgnoreCase(s.getName())) return s;
	    }
	}
	return null;
    }
    /** Returns the room as a string
     * @return the room as a string
     */
    public String toString() {
	String studentString = "";
	String teacherString = "";
	String itemString = "";
	try {
	    itemString = arrListToString(items); } 
	catch (IndexOutOfBoundsException e) {
	}
	try {
	    studentString = arrListToString(creatures, true); } 
	catch (IndexOutOfBoundsException e) {
	}
	try {
	    teacherString = arrListToString(creatures, false); } 
	catch (IndexOutOfBoundsException e) {
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
    /** Checks if a room has one door.
     * @return true if the room has one door, else false.
     */
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

	    stud = student && ((current instanceof Student) || (current instanceof Avatar));
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
