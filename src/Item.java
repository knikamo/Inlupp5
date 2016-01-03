abstract public class Item {
	private double volume;

	public Item(double v) {
		this.volume = v;
	}

	public String toString() {
		return "item";
	}

	public double getVolume() {
		return this.volume;
	}
}