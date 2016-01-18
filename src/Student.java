import java.util.ArrayList;
public class Student extends Creature {
    private Course ongoingCourse;
    private Course completedCourse;
    private Book ongoingBook;
    private Book completedBook;
    private boolean hasAnswer;

    public Student(String name, Course ongoingCourse, Course completedCourse, Book completedBook) {
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

    public Book getCompletedBook() {
	return completedBook;
    }

    public Course getOngoingCourse() {
	return ongoingCourse;
    }

    public String toString() {
	return getName();
    }

    public String talk() {
	String studentInfo = "Hello, I'm " + getName();
	studentInfo += " and I study " + ongoingCourse + ".";
	studentInfo += " I've finished the course " + completedCourse + ".";
	return studentInfo;
    }
}
