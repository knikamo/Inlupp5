import java.util.ArrayList;
public class Student extends Creature {
	private String ongoingCourse;
	private String completedCourse;
	private Book ongoingBook;
	private Book completedBook;
	private boolean hasAnswer;

	public Student(String name, String ongoingCourse, String completedCourse, Book completedBook) {
		super(name);
		this.ongoingCourse = ongoingCourse;
		this.completedCourse = completedCourse;
		this.completedBook = completedBook;
		this.hasAnswer = false;
	}

	public void setCompletedBook(Book b){
		this.completedBook = b;
	}
	public void setOngoingBook(Book b){
		this.ongoingBook = b;
	}
	public String toString() {
		return getName();
	}

	public String talk() {
		String studentInfo = "Hello, I'm " + getName();
		studentInfo += " and I study " + ongoingCourse + ".";
		return studentInfo;
		//System.out.println("Hello, I'm a student");
	}
}