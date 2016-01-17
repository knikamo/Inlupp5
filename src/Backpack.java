import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
/** Represents a backpack */
public class Backpack implements ListToString {
    /** The maximum volume the backpack can hold*/
    private double maxVolume;
    /** The total volume of all items in the backpack */
    private double currentVolume;
    /** All items in the backpack */
    private ArrayList<Item> items;

    
    /** Creates a new empty backpack with the maximum volume 10 litres*/
    public Backpack () { 
	maxVolume = 10.0;
	currentVolume = 0.0;
	items = new ArrayList<Item>();
    } 

    /** Adds an item to the backpack 
     * @param addItem the item to put in the backpack
     * @return true if the item was added succesfully, otherwise false. */
    public Boolean addToBackpack (Item addItem) {
	if(addItem == null) {
	    return false;
	}

	double itemVolume = addItem.getVolume();
	Boolean added = false;

	double checkSpace = currentVolume+itemVolume;
	if(checkSpace<=maxVolume) {
		currentVolume += itemVolume;
		(this.items).add(addItem);
		added = true;
	    }
	else {
	    System.out.println("Backpack is not big enough for that item. Current empty space in backpack: " + currentVolume);
	    added = false;
	}
	return added;

    }

     /** Removes an item to the backpack 
     * @param dropItem the item to drop from backpack
     * @return true if the item was removed succesfully, otherwise false. */
    public Boolean dropFromBackpack (Item dropItem) {
	double itemVolume = dropItem.getVolume();
	Boolean hasDropped = (this.items).remove(dropItem);
	if (hasDropped) {
		currentVolume -= itemVolume;
	    }
	return hasDropped;
    }
    /** Checks it there's a key in the backpack
     * @return A key if the backpack contains a key, otherwise null.
     */
    public Key hasKey() {
	for (int i = 0; i < items.size(); i++) {
	    Item item = items.get(i);
	    if (item instanceof Key) {
		Key k = (Key) item;
		return k;
	    }
	}
	return null;
    }
    /** Checks it there's a specifik book in the backpack
     * @param bookName the name of the book to look for in the backpack
     * @return The book if the backpack contains a key, otherwise null.
     */
    public Book hasBook(String bookName) {
	for (int i = 0; i < items.size(); i++) {
	    Item item = items.get(i);
	    if (item instanceof Book) {
		Book b = (Book) item;
		String name = b.getName();
		if(name.equals(bookName)) {
		    return b;
		}
	    }
	}
	return null;
    }
    /** Creates a string with all the items in the backpack
	@return a string with all the items in the backpack
    */
    public String toString() {
	String s = "Backpack: ";
	try {
	    s += arrListToString(items); } catch (IndexOutOfBoundsException e) {
	    s += "-----";
	}
	s += "\n Volume: " + currentVolume + " litres   (Empty space: " + (maxVolume-currentVolume) + " litres)";
	return s;

    }
    /** Creates a string of an array list 
     * @param arrList the array list
     * @return the content of the array list as an string separated with commas.
     */
    public String arrListToString(ArrayList arrList) throws IndexOutOfBoundsException {
	String s;
	Object current = arrList.get(0);
		
	s = current.toString();	

	for (int i = 1; i < arrList.size(); i++) {
	    s += ", ";
	    current = arrList.get(i);
	    s += current.toString(); 
	}
	return s;
    }	 
}
	
	
