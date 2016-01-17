/** Represents a course-book */
public class Book extends Item {
    /** The name of the book */
    private String name;
    /** The name of the author */ 
    private String author;
    /** The year the book was written */ 
    private int year;

    /** Creates a book 
     * @param name The name of the book 
     * @param author The name of the author
     * @param year The year the book was written
     * @param volume The volume of the book
     */
    public Book(String name, String author, int year, double volume) {
	super(volume);
	this.name = name;
	this.author = author;
	this.year = year;
    }
    /** Returns the name of a book
     * @return the name of the book     
     */
    public String getName() {
	return this.name;
    }
    /** Returns the name of the book with "(book)" after.
     * @return the name of the book with "(book)" after.
     */
    public String toString() {
	return "'" + this.name + "' (book)";
    }
}
