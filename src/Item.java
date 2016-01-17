/** This class represents an item.
 */
abstract public class Item {
    /** The volume of the item in litres */
    private double volume;  
    /** Creates a new item with volume v
     * @param v The volume of the item
     */
    public Item(double v) {
	this.volume = v;
    }

    public String toString() { //behövs denna verkligen?
	return "item";
    }
    
    /** Returns the volume of the item
     * @return the volume of the item
     */
    public double getVolume() {
	return this.volume;
    }
}
