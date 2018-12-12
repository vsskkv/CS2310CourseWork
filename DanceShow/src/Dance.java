
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dance {
	private Set<Dancer> performers;
	private String name;
	private int gap;
	public Dance(String name) {
		performers = new HashSet<Dancer>();
		this.name = name;
	}
	public void addPerformer(Dancer dancer) {
		performers.add(dancer);
	}
	public void addGroup(Set<Dancer> group) {
		performers.addAll(group);
	}
	public Set<Dancer> getPerformers(){
		return performers;
	}
	public String toString() {
		List<Dancer> mapValues = new ArrayList<Dancer>(performers);
		Collections.sort(mapValues);
		String str = "| ";
		for(Dancer performer : mapValues) {
			str += performer + " | ";
		}
		return str;
	}
	
}
