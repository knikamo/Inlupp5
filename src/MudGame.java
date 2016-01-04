import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MudGame extends World {
    public static void main(String[] args) throws InterruptedException {
	System.out.println("\n============================================\n-------- Welcome to our SUD-game! ----------\n============================================\n");
	World w = new World();
	System.out.println("What's your name?");
	
	Scanner scan = new Scanner(System.in);
	String input = "K";
	input = scan.nextLine();
	
	w.createWorld(input);
	w.runGame();

	/*
	  try {
	  testRR(); } 

	  catch (FileNotFoundException e) {
	  System.out.println("File not found....  " + e.getMessage());
	  }
	  catch (IOException e) {
	  System.out.println("Caught IOException...." + e.getMessage());
	  } 

	  Creature s = new Student("hej hej", "Karro");
	  Item i = new Key(0.3);
	  ArrayList<Item> items = new ArrayList<Item>();
	  ArrayList<Creature> creatures = new ArrayList<Creature>();
	  creatures.add(s);
	  items.add(i);

	  String[] strings = {"Norrrrr", "Östtttt", "Syddd", "Väääääst"};
	  Boolean[] openDoors = {true, false, true, false};

	  Room testRoom = new Room("Sovrum", strings, openDoors, items, creatures);
	  System.out.println(testRoom);
	*/
    }
}
