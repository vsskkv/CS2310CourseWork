
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * A class which represents a Dance.
 * It will hold the set of performers and it's name
 * and it will provide mutator and accessor methods for its clients
 * 
 * @author Vikram Kainth
 * @version 13/12/2018
 */
public class Dance implements java.lang.Comparable<Dance> {
	//a set representing all dancers in this dance
	private Set<Dancer> performers;
	private String name;
	
	public Dance(String name) {
		//instantiates the performers
		performers = new HashSet<Dancer>();
		this.name = name;
	}
	
	/**
	 * Adds the given performer to the set of performers
	 * @param a dancer which is the performer to be added  
	 */
	public void addPerformer(Dancer dancer) {
		performers.add(dancer);
	}
	
	/**
	 *@return the name of the dance 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds all the members in a set given to this set of performers.
	 * @param a set of dancers which are in a group  
	 */
	public void addGroup(Set<Dancer> group) {
		performers.addAll(group);
	}
	/**
	 * @return the name of all performers that are in this dance
	 */
	public Set<Dancer> getPerformers(){
		return performers;
	}

	/**
	 * Checks if the given dance's members are a subset
	 * @param o	a given dance to check against
	 * @return boolean which is the result of a contains check 
	 */
	public boolean isSubset(Dance o) {
		for(Dancer dancers: o.getPerformers()) {
			if(performers.contains(dancers)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return string representation of all performers in this dance
	 */
	public String toString() {
		List<Dancer> mapValues = new ArrayList<Dancer>(performers);
		Collections.sort(mapValues);
		
		String str = "Dancers in this Dance: \n| ";
		for(Dancer performer : mapValues) {
			str += performer + " | ";
		}
		return str;
	}
	
	/**
	 * Will compare the name of this dance to a given dance
	 * @param o a given dance to compare this dance to
	 * @return int for comparing the name of this dance and a given dance 
	 */
	@Override
	public int compareTo(Dance o) {
		return this.getName().compareTo(o.getName());
	}
	
}
