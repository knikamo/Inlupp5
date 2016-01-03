import java.util.ArrayList;
import java.util.Random;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.management.RuntimeErrorException;
import java.lang.Exception;

abstract class BuildWorld {
	
	public static ArrayList<String> readTextFile(String fileName) throws FileNotFoundException, IOException {
	BufferedReader br = new BufferedReader(new FileReader(fileName));
	ArrayList<String> allLines = new ArrayList<String>();
	String line;
	
	while ((line = br.readLine()) != null) {
		allLines.add(line);
		
	}
	return allLines;

}
	private static ArrayList<String[]> textfileToSringArr(String filename) throws EmptyFileException {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			lines = readTextFile(filename);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found....  " + e.getMessage());
			System.exit(1);
			//throw new RuntimeErrorException(e, "File not found!!!");
		}
		catch (IOException e) {
			System.out.println("Caught IOException...." + e.getMessage());
			System.exit(1);
			//throw new RuntimeErrorException(e, "IO EXCEPTION");
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
 			
 			String ongoing = courses.get(i).getName();
 			String completed = courses.get(i+1).getName();
 			Book book;
 			if (i % 2 == 0) book = courses.get(i+1).getBook(); //varannan student får en bok
 			else book = null;
 			Student s = new Student(name, ongoing, completed, book);
 			students.add(s);
 		}
 		return students;
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