import java.util.Random;
import java.util.ArrayList;

public class Sfinx extends Creature {
    ArrayList<String> quotes;

    public Sfinx (String name) {
	super(name);
	this.quotes = new ArrayList<String>();
	this.quotes.add("Carpe Diem");
	this.quotes.add("Live slow, die whenever");
	this.quotes.add("Go hard or go home");
	this.quotes.add("The good things in life does not come easy");
	this.quotes.add("If you want something, you have to work for it");
	this.quotes.add("Step out of your comfort zon");
	this.quotes.add("Live long and prosper");
	this.quotes.add("The bigger they are, the harder they fall");
	this.quotes.add("If you can dream it you can do it");
	this.quotes.add("If you can't beat them, join them");
	this.quotes.add("If you want peace prepare for war");
	this.quotes.add("Talk shit, get hit");
	this.quotes.add("Everybody poops");
	this.quotes.add("The good ones die young");
	this.quotes.add("Live everyday as if it was your last");
	this.quotes.add("Don't abuse god's grace");
	this.quotes.add("One does not simply walk into Mordor");
	this.quotes.add("Just do it");
	this.quotes.add("Live life to the fullest");
	this.quotes.add("A jealous woman does better research than the FBI");
	this.quotes.add("Be you, because life is too short");
	this.quotes.add("No one looks stupid when they are having fun");
	this.quotes.add("Girls just wanna have fun");
	this.quotes.add("You can't FACE the problem if the problem is your FACE");

    }

    public String talk() {
	Random random = new Random();
	int numberOfQuotes = (this.quotes).size();
	int randomIndex = random.nextInt(numberOfQuotes);
	return ("Sfinx says: " + (this.quotes).get(randomIndex));
    }

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
