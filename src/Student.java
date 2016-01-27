import java.util.ArrayList;
/** Represents a student in the MUD-game. */
public class Student extends Creature {
    private Course ongoingCourse;
    private Course completedCourse;
    private Book ongoingBook;
    private Book completedBook;
    private boolean hasAnswer;
    /** Creates a new student.
     * @param name The student's name.
     * @param ongoingCourse The student's ongoing course.
     * @param completedCourse The student's completed course.
     * @param completedBook The student's book (for the completed course).
     */
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
	Book tmpBook = completedBook;
	completedBook = null;
	return tmpBook;
    }

    public Course getOngoingCourse() {
	return ongoingCourse;
    }

    public String toString() {
	return getName();
    }
   
    public String talk() {
	String studentInfo = getName() + " says: Hello, I'm " + getName();
	studentInfo += " and I study " + ongoingCourse.getName() + ".";
	studentInfo += " I've finished the course " + completedCourse.getName() + ".\n";
	if (ongoingBook == null && completedBook != null) {
	    studentInfo += "If you give me the book " + ongoingCourse.getBook().getName() + ", I'll give you my copy of " + completedBook.getName() + ".";
	} 
	
	return studentInfo;
    }
}
