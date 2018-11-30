

public class Dancer implements java.lang.Comparable<Dancer>{
	private String name;
	
	public Dancer(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "," + name;
	}


	@Override
	public int compareTo(Dancer o) {
		return this.name.compareTo(o.name);
	}
	
}
