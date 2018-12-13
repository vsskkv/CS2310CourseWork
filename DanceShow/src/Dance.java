
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dance implements java.lang.Comparable<Dance> {
	private Set<Dancer> performers;
	private String name;
	public Dance(String name) {
		performers = new HashSet<Dancer>();
		this.name = name;
	}
	public void addPerformer(Dancer dancer) {
		performers.add(dancer);
	}
	public String getName() {
		return name;
	}
	public void addGroup(Set<Dancer> group) {
		performers.addAll(group);
	}
	public Set<Dancer> getPerformers(){
		return performers;
	}
	public boolean isSubset(Dance o) {
		for(Dancer dancers: o.getPerformers()) {
			if(performers.contains(dancers)) {
				return true;
			}
		}
		return false;
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
	@Override
	public int compareTo(Dance o) {
		return this.getName().compareTo(o.getName());
	}
	
}
