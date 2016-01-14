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

    // TODO: Ta hänsyn till kurser
    public int askQuestion(Boolean completedCourse, Boolean ongoingCourse, Boolean hasBook) {
	Random r = new Random();
	int randomIndex = r.nextInt(100);
	int hp = course.getCredits();
	int avatarCredits = 0;

	if (completedCourse && randomIndex < 50) {
	    if (course.courseQuestion(getName(), hasBook) == false) {
		avatarCredits = (-hp);
	    }
	}
	else if (ongoingCourse && randomIndex < 75) {
	    if (course.courseQuestion(getName(), hasBook)) {
		avatarCredits = hp;
	    }
	}
	else if (!(ongoingCourse || completedCourse)) {
	    System.out.print("You are not enrolled on " + getName() + "'s course '" + course.getName() + "'\n");
	}
		
	return avatarCredits; 	
    }
}
