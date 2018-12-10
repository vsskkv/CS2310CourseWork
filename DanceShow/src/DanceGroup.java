
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DanceGroup {
	private Set<String> dancers;
	private String name;
	
	public DanceGroup() {
		dancers = new HashSet<String>();
	}
	public void addDancer(String dancer) {
		dancers.add(dancer);
	}
	public void setName(String name) {
		this.name= name;
	}
	public Set<String> getDancers(){
		return dancers;
	}
	
	public String toString() {
		ArrayList<String> temp = new ArrayList<String>(dancers);
		Collections.sort(temp);
		String str = name + " : ";
		for (String dancer : dancers) {
			str += "\n \t"+dancer;
		}
		
		return str;
	}
}
