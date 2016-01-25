import java.util.Random;
import java.util.ArrayList;
/** Represents a Sfinx in the MUD-game */
public class Sfinx extends Creature {
    ArrayList<String> quotes;
    /** Creates a new sfinx with a name and a list of quotes. 
     * @param name the sfinx's name
     */
    public Sfinx (String name) {
	super(name);
	this.quotes = new ArrayList<String>();
	this.quotes.add("Carpe Diem");
	this.quotes.add("Carpe Diem2");

    }
    /** Makes the sfinx say a random quote.
     * @return A string with the sinfx's qoute.
     */
    public String talk() {
	Random random = new Random();
	int numberOfQuotes = (this.quotes).size();
	int randomIndex = random.nextInt(numberOfQuotes);
	return ("Sfinx says: " + (this.quotes).get(randomIndex));
    }
    /** Sfinx tries to graduate the avatar. If the avatar is ready for exam the sfinx gives the avatar a diploma, else the sfinx will talk.
     * @param avatar The avatar */
    public void graduate(Avatar avatar) {
	if (avatar.readyForExam()) {
	    System.out.println("DIPLOMA!");
	    avatar.printCompleted();
	    System.out.println("YOU GRADUATED");
	    System.exit(0);
	}
	else System.out.println(this.talk());
    }

    public String toString() {
	return getName();
    }
}
