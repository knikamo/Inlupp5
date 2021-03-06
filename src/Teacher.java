import java.util.Random;

public class Teacher extends Creature {
    private Course course;
	
    public Teacher (String name, Course c) {
	super(name);
	this.course = c;
    }

    public String getCourseName() {
	return course.getName();
    }
    public Book getBook() {
	return course.getBook();
    }
    public String toString() {
	return getName();
    }

    public String talk() {
	return "Hello, I'm a teacher and teach the course " + course.getName();
    }

    // TODO: Ta hänsyn till kurser - vilken lista de ska vara i
    public void askQuestion(Avatar a) {
	Random r = new Random();
	int randomIndex = r.nextInt(100);
	int hp = course.getCredits();
	int achievedCredits = 0;
	Book b = getBook(); //b är null, något är fel när böcker skapas
	Boolean hasBook = (a.hasBook(b.getName()) != null);
	if (hasBook) System.out.println("You have the book woho!");
	String courseName = getCourseName();
	Boolean completedCourse = a.completedCourse(courseName);
	Boolean ongoingCourse = a.ongoingCourse(courseName);
        
      	if (completedCourse && randomIndex < 50) {
	    System.out.println("This is one of your completed courses.");
	    if (course.courseQuestion(getName(), hasBook) == false) {
		System.out.println("hej hej");
		achievedCredits = (0-hp);
		//flytta kurs från completed till ongoing
		a.removeCompleted(courseName, achievedCredits); 
		a.addOngoing(courseName);
	    }
	}
	else if (ongoingCourse && randomIndex < 75) {
	    System.out.println("This is one of your ongoing courses.");
	    if (course.courseQuestion(getName(), hasBook)) {
		achievedCredits = hp;
		System.out.println("heeeeeeeej");
		//flytta kurs från ongoing till completed
		a.removeOngoing(courseName);
		a.addCompleted(courseName, achievedCredits);
	    }
	}
	else if (!(ongoingCourse || completedCourse)) {
	    System.out.print("You are not enrolled on " + getName() + "'s course '" + course.getName() + "'\n");
	}
	//a.changeCredits(achievedCredits); //detta kanske kan ingå när man flyttar kurs?	
		
    }
}
