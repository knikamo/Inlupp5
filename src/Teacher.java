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
	return "Hello, I'm a teacher";
    }

    // TODO: Ta hänsyn till kurser - vilken lista de ska vara i
    public void askQuestion(Avatar a) {
	Random r = new Random();
	int randomIndex = r.nextInt(100);
	int hp = course.getCredits();
	int achievedCredits = 0;
	Book b = getBook(); //b är null, något är fel när böcker skapas
	Boolean hasBook = a.hasBook(b.getName());
	String courseName = getCourseName();
	Boolean completedCourse = a.completedCourse(courseName);
	Boolean ongoingCourse = a.ongoingCourse(courseName);
        
      	if (completedCourse && randomIndex < 50) {
	    if (course.courseQuestion(getName(), hasBook) == false) {
		achievedCredits = (-hp);
		//flytta kurs från completed till ongoing
		a.removeCompleted(courseName, achievedCredits); 
		a.addOngoing(courseName);
	    }
	}
	else if (ongoingCourse && randomIndex < 75) {
	    if (course.courseQuestion(getName(), hasBook)) {
		achievedCredits = hp;
		//flytta kurs från ongoing till completed
		a.removeOngoing(courseName);
		a.addCompleted(courseName, achievedCredits);
	    }
	}
	else if (!(ongoingCourse || completedCourse)) {
	    System.out.print("You are not enrolled on " + getName() + "'s course '" + course.getName() + "'\n");
	}
	a.changeCredits(achievedCredits); //detta kanske kan ingå när man flyttar kurs?	
		
    }
}
