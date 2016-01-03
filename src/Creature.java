abstract class Creature {
	private World world;
	private String name;

	public Creature(String name)  {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public String talk() {
		return ("This creature can't talk");
		//System.out.print("This creature can't talk");
	}
}