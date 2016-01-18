import java.util.ArrayList;
import java.util.Scanner;
/** Represent the player in the game */
public class Avatar extends Creature {
    /** The avatar's backpack */
    private Backpack backpack;
    /** The avatar's current hp/credits*/
    private int hp;
    /** List of avatar's ongoing courses */
    private ArrayList<String> ongoingCourses;
    /** List of avatar's completed courses */
    private ArrayList<String> completedCourses;

    /** Creates an new avatar with a backpack containing one key, 60hp, some completed courses 
     * @param name The name of the avatar */
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
    /** Adds "hp" to the avatar's current hp. 
     * @param hp Number of hp to add (negative number decreases the current hp). 
     */
    public void changeCredits(int hp) {
	this.hp = this.hp + hp;
    }
    /** Returns the avatar's name with "(you)" after.
     * @preturn a string representing the avatar
     */   
    public String toString() {
	return (getName() + " (you)");
    }
    /** Returns the avatar's backpack as an string
     * @preturn a string representing the avatar's backpack
     */
    public String backpackToString() {
	return backpack.toString();
    }
    /** Not implemented */
    public String talk() {
	Scanner reader = new Scanner(System.in);
	String input = reader.nextLine();
	reader.close();

	return input;
	//read input from user
    }
    /** Checks if a course is completed
     * @param courseName the name of the course
     * @return true if the avatar has completed the course, otherwise false
     */
    public Boolean completedCourse(String courseName) {
	for (int i = 0; i<completedCourses.size(); i++) {
	    String crnt = completedCourses.get(i);
	    if (crnt.equals(courseName)) {
		return true;
	    }
	}
	return false;
    }
    /** Checks if the avatar is enrolled to a course.
     * @param courseName the name of the course
     * @return true if the avatar is enrolled to the course, otherwise false
     */
    public Boolean ongoingCourse(String courseName) {
	for (int i = 0; i < ongoingCourses.size(); i++) {
	    String crnt = ongoingCourses.get(i);
	    if (crnt.equals(courseName)) {
		return true;
	    }
	}
	return false;
    }

    /** Adds a course to the avatar's ongoing courses.
     * @param course The name of the course
     */    
    public void addOngoing(String course) {
	System.out.println("Your course add ongoing: " + course);
	ongoingCourses.add(course);
    }
    /** Adds a course to the avatar's completed courses.
     * @param course The name of the course
     * @param hp The course's credits
     */
    public void addCompleted(String course, int hp) {
	System.out.println("Your course add compl: " + course);
	ongoingCourses.add(course);
	changeCredits(hp);
    }
    /** Removes a course from the avatar's ongoing courses.
     * @param course The name of the course
     */    
    public void removeOngoing(String course) {
	System.out.println("Your course remove ong: " + course);
	ongoingCourses.remove(course); //remove borde det va?
    }
    /** Removes a course from the avatar's completed courses.
     * @param course The name of the course
     * @param hp The course's credits
     */
    public void removeCompleted(String course, int hp) {
	System.out.println("Your course remove comp: " + course);
	ongoingCourses.remove(course); //remove??
	changeCredits(hp);
    }
    /** Checks if the avatar has a bookkey
     * @return a key if the avatar has a key, otherwise null
     */
    public Key hasKey() {
	return backpack.hasKey();
    }
    /** Checks if the avatar has a book
     *	@param bookName the name of the book 
     * @return true if the avatar has the book, otherwise false
     */
    public Boolean hasBook(String bookName) {
	return(backpack.hasBook(bookName) != null);
    }
     /** Adds an item to the backpack
     *	@param i The item to add
     *	@return true if the item was succesfully added, otherwise false.
     */
    public Boolean pickUp(Item i) {
	return backpack.addToBackpack(i);
    }
    /** Drops an item from the backpack
     *	@param i The item to drop
     *	@return true if the item was succesfully dropped, otherwise false.
     */
    public Boolean drop(Item i) {
	return backpack.dropFromBackpack(i);
    }

    /** Checks if the avatar is ready to get an exam.
     * @return true if the avatar is ready for exam (has 180 credtis or more and doesn't have any ongoing courses), otherwise false. 
     */
    public Boolean readyForExam() {
	return (hp >= 180 && ongoingCourses.isEmpty());
    }
    /** Är denna funktionen nödvändig? vi kan väl använda funktionerna pickup odh drop? */
    public Backpack getBackpack() {
	return this.backpack;
    }
    /** Returns the avatar's current credits.
     * @return the avatar's current credits.
     */
    public int getHp() {
	return this.hp;
    }

    public void printOngoing() {
	System.out.println("Your ongoing courses:");
	for (int i = 0; i < (this.ongoingCourses).size(); i++){
	    System.out.println(ongoingCourses.get(i));
	}
	System.out.println("-------------------------");
	
    }

    public void printCompleted() {
	System.out.println("Your completed courses:");
	for (int i = 0; i < (this.completedCourses).size(); i++){
	    System.out.println(completedCourses.get(i));
	}
	System.out.println("-------------------------");
	
    }

}
