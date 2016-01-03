public class Book extends Item {
	private String name;
	private String author;
	private int year;

	public Book(String name, String author, int year, double volume) {
		super(volume);
		this.name = name;
		this.author = author;
		this.year = year;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return "'" + this.name + "' (book)";
	}
}