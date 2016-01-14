import java.util.ArrayList;
import java.util.Scanner;

public class Avatar extends Creature {
    private Backpack backpack;
    private int hp;
    private ArrayList<String> ongoingCourses;
    private ArrayList<String> completedCourses;

    public Avatar(String name) {
	super(name);
	this.backpack = new Backpack();
	this.hp = 60;
	this.ongoingCourses = new ArrayList<String>();
	this.completedCourses = new ArrayList<String>();
	backpack.addToBackpack(new Key());		
    }

    // TODO: ta bort när spelet är färdigt
    public void cheat(){
	this.hp = 180;
	this.ongoingCourses = new ArrayList<String>();
	this.backpack = new Backpack();
	for(int i = 0; i < 10; i++) {
	    backpack.addToBackpack(new Key());
	}
    }

    public void changeCredits(int hp) {
	this.hp = this.hp + hp;
    }

    public String toString() {
	return (getName() + " (you)");
    }

    public String backpackToString() {
	return backpack.toString();
    }

    public String talk() {
	Scanner reader = new Scanner(System.in);
	String input = reader.nextLine();
	reader.close();

	return input;
	//read input from user
    }
    public Boolean completedCourse(String courseName) {
	for (int i = 0; i<completedCourses.size(); i++) {
	    String crnt = completedCourses.get(i);
	    if (crnt.equals(courseName)) {
		return true;
	    }
	}
	return false;
    }
    public Boolean ongoingCourse(String courseName) {
	for (int i = 0; i < ongoingCourses.size(); i++) {
	    String crnt = ongoingCourses.get(i);
	    if (crnt.equals(courseName)) {
		return true;
	    }
	}
	return false;
    }

    
    public void addOngoing(String course) {
	ongoingCourses.add(course);
    }
    public void addCompleted(String course, int hp) {
	ongoingCourses.add(course);
	changeCredits(hp);
    }
    public void removeOngoing(String course) {
	ongoingCourses.add(course);
    }
    public void removeCompleted(String course, int hp) {
	ongoingCourses.add(course);
	changeCredits(hp);
    }
    public Key hasKey() {
	return backpack.hasKey();
    }
    public Boolean hasBook(String bookName) {
	return(backpack.hasBook(bookName) != null);
    }
    public Boolean pickUp(Item i) {
	return backpack.addToBackpack(i);
    }
    public Boolean drop(Item i) {
	return backpack.dropFromBackpack(i);
    }
    public Boolean readyForExam() {
	return (hp >= 180 && ongoingCourses.isEmpty());
    }

    public Backpack getBackpack() {
	return this.backpack;
    }
    
    public int getHp() {
	return this.hp;
    }

}
