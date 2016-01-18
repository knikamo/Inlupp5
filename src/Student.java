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
	this.ongoingBook = null;
	this.hasAnswer = false;
    }

    public void setCompletedBook(Book b){
	this.completedBook = b;
    }
    public void setOngoingBook(Book b){
	this.ongoingBook = b;
    }

    public Book getCompletedBook() {
	completedBook = null;
	return completedBook;
    }

    public Course getOngoingCourse() {
	return ongoingCourse;
    }

    public String toString() {
	return getName();
    }

    public String talk() {
	String studentInfo = getName() " says: Hello, I'm " + getName();
	studentInfo += " and I study " + ongoingCourse.getName() + ".";
	studentInfo += " I've finished the course " + completedCourse.getName() + ".\n";
	if (ongoingBook == null && completedBook != null) {
	    studentInfo += "If you give me the book " + ongoingCourse.getBook().getName() + ", I'll give you my copy of " + completedBook.getName() + ".";
	} 
	
	return studentInfo;
    }
}
