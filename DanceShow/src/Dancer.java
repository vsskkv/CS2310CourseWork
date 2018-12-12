

public class Dancer implements java.lang.Comparable<Dancer>{
	private String name;
	private int gap;
	private int restTime;
	public Dancer(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public void setGap(int givenGap) {
		gap = givenGap;
	}

	public void setPerformed() {
		restTime = 0;
	}
	public void notPerformed() {
		restTime++;
	}
	public boolean isReady() {
		return (restTime%gap) >= 0 ;
		
	}
	@Override
	public int compareTo(Dancer o) {
		return this.name.compareTo(o.name);
	}
	
}
