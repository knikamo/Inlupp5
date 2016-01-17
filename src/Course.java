import java.util.Scanner;
/** Represents a course*/
public class Course {
    private final String name;
    private final Book book; 
    private final int hp;
    private final String question;
    private final String[] answers;
    private final String correctAlt;
    /** Creates a new course. 
     * @param n The name of the course
     * @param b The book for the course
     * @param hp The course's credits
     * @param q A question to the course
     * @param a An array of answers to the question
     * @param correctAlt Specifies which of the answers that is correct 
     */
    public Course(String n, Book b, int hp, String q, String[] a, String correctAlt) {
	this.name = n;
	this.book = b;
	this.hp = hp;
	this.question = q;
	this.answers = a;
	this.correctAlt = correctAlt;
    }
    /** Returns the course credits. 
     * @return the couse's credits */
    public int getCredits() {
	return this.hp;
    }
    /** Returns the name of the course 
     *	@return the name of the course */
    public String getName() {
	return this.name;
    }
    /** Returns the course's book 
     *	@return the course's book */
    public Book getBook() {
	return this.book;
    }
    /** Ask a question to the avatar 
     * @param teacher The name of the teacher
     * @param hasBook A boolean that tells wether the avatar has the course's book or not.
     * @return true if the avatar's answer was correct, otherwise false.
     */
    public Boolean courseQuestion(String teacher, Boolean hasBook) {
	String q = teacher + " asks: " + question;
	System.out.println(q);
	String alt = "Alternatives:";
	alt += "\n a) " + answers[0];
	alt += "\n b) " + answers[1];
	alt += "\n c) " + answers[2];
	System.out.println(alt);
		
	if (hasBook) {
	    String wrongOption = "You have this course book and you know ";
	    if (!(correctAlt.equals("a"))) wrongOption += "'a' ";
	    else if (!(correctAlt.equals("b"))) wrongOption += "'b' ";
	    else if (!(correctAlt.equals("c"))) wrongOption += "'c' ";
	    wrongOption += "is NOT the correct answer.";
	    System.out.println(wrongOption);
	}
	Scanner reader = new Scanner(System.in);
	
	String input = reader.nextLine();

	while (!(input.equals("a") || input.equals("b") || input.equals("c"))) {
	    System.out.println("Input: "+input);
	    System.out.println("Not a valid answer, try again with 'a', 'b' or 'c' ");
	    reader.reset();
	    input = reader.nextLine();
	}
		
	if (correctAlt.equals(input)) {
	    System.out.println("Correct answer!");
	    return true; 
	}
	else {
	    System.out.println("Wrong answer!");
	    return false;
	}
    }
}
