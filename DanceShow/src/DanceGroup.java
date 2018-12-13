
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a DanceGroup in this DanceShow program
 * It holds all the dancers which are in this group
 * it provides accessor and mutator methods for this
 * 
 * @author Vikram Kainth
 * @version 13/12/2018
 */
public class DanceGroup {
	private Set<Dancer> dancers;
	private String name;
	
	public DanceGroup() {
		//constructs a new HashSet of dancers
		dancers = new HashSet<Dancer>();
	}
	
	/**
	 * It will add a new dancer to this dance group
	 * @param dancer a specified dancer object 
	 */
	public void addDancer(Dancer dancer) {
		dancers.add(dancer);
	}
	
	/**
	 * Sets the name of this dance group
	 * @param name a String which represents the name
	 */
	public void setName(String name) {
		this.name= name;
	}
	
	/**
	 * Provides a set of all the dancers in this group 
	 * @return dancers a HashSet of the dancers in this group
	 */
	public Set<Dancer> getDancers(){
		return dancers;
	}
	
	/**
	 * @return string representation of this dance group  
	 */
	public String toString() {
		ArrayList<Dancer> temp = new ArrayList<Dancer>(dancers);
		Collections.sort(temp);
		String str = name + " : ";
		for (Dancer dancer : dancers) {
			str += "\n \t"+dancer.toString();
		}
		
		return str;
	}
}
