import java.util.Scanner;

public class Course {
	private final String name;
	private final Book book; 
	private final int hp;
	private final String question;
	private final String[] answers;
	private final String correctAlt;

	public Course(String n, Book b, int hp, String q, String[] a, String correctAlt) {
		this.name = n;
		this.book = b;
		this.hp = hp;
		this.question = q;
		this.answers = a;
		this.correctAlt = correctAlt;
	}

	public int getCredits() {
		return this.hp;
	}

	public String getName() {
		return this.name;
	}
	public Book getBook() {
		return this.book;
	}
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
			if ("a" != correctAlt) wrongOption += "'a' ";
			else if ("b" != correctAlt) wrongOption += "'b' ";
			else if ("c" != correctAlt) wrongOption += "'c' ";
			wrongOption += "is NOT the correct answer.";
			System.out.println(wrongOption);
		}
		Scanner reader = new Scanner(System.in);
	
		String input = reader.nextLine();

		while (input != "a" && input != "b" && input != "c") {
			System.out.println("Not a valid answer, try again with 'a', 'b' or 'c' ");
			input = reader.nextLine();
		}
		
		reader.close();
		if (correctAlt.equals(input)) {
			System.out.println("Correct answer!");
			return true; 
		}
		else return false;
	}
}
