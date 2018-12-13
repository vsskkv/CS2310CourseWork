
/**
 * Represents a Dancer in the DanceShow
 * It stores the name, rest time and the gap requirement of the dancer
 * It provides accessor and mutator methods for these data
 * 
 * @author Vikram Kainth
 * @version 13/12/2018
 */
public class Dancer implements java.lang.Comparable<Dancer>{
	
	private String name;
	//required gap specified by user
	private int gap;
	//the number of times that this dancer has not performed
	private int restTime;
	
	public Dancer(String name) {
		this.name = name;
		restTime = 1;
	}
	
	/**
	 * Sets the value of the gap
	 * @param givenGap an integer which is representing the required gap
	 */
	public void setGap(int givenGap) {
		gap = givenGap;
	}

	/**
	 * When the dancer has just performed a dance, this method will set the rest time to 0
	 */
	public void setPerformed() {
		restTime = 0;
	}
	
	/**
	 * When the dancer has not participated in any dances their rest time will increase by 1
	 */
	public void notPerformed() {
		restTime++;
	}
	
	/**
	 * @return the current rest time of the dancer 
	 */
	public int getRestTime() {
		return restTime;
	}
	
	/**
	 * if the dancer has rested for more or same number of rounds which was required(gap)
	 * this will return true, otherwise the dancer is not ready yet
	 * @return boolean representing the current state of the dancer
	 */
	public boolean isReady() {
		return restTime>= gap ;
		
	}
	
	/**
	 * @return the name of this dancer
	 */
	public String toString() {
		return name;
	}
	/**
	 * Will compare the given dancer's name with this dancer's name
	 * @param o a dancer given
	 */
	@Override
	public int compareTo(Dancer o) {
		return this.name.compareTo(o.name);
	}
	
}
