import java.util.Random;
import java.util.ArrayList;

public class Sfinx extends Creature {
    ArrayList<String> quotes;

    public Sfinx (String name) {
	super(name);
	this.quotes = new ArrayList<String>();
	this.quotes.add("Carpe Diem");
	this.quotes.add("Carpe Diem2");

    }

    public String talk() {
	Random random = new Random();
	int numberOfQuotes = (this.quotes).size();
	int randomIndex = random.nextInt(numberOfQuotes);
	return ((this.quotes).get(randomIndex));
    }

    public void graduate(Avatar avatar) {
	if (avatar.readyForExam()) {
	    System.out.println("Diploma!");
	    //TODO: print courses
	    System.exit(1);
	}
	else System.out.println(this.talk());
    }

    public String toString() {
	return getName();
    }
}
