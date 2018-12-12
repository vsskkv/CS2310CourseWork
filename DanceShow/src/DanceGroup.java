
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DanceGroup {
	private Set<Dancer> dancers;
	private String name;
	
	public DanceGroup() {
		dancers = new HashSet<Dancer>();
	}
	public void addDancer(Dancer dancer) {
		dancers.add(dancer);
	}
	public void setName(String name) {
		this.name= name;
	}
	public Set<Dancer> getDancers(){
		return dancers;
	}
	
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
