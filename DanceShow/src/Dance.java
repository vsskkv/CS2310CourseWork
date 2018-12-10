
import java.util.HashSet;
import java.util.Set;

public class Dance {
	private Set<String> performers;
	private String name;
	public Dance(String name) {
		performers = new HashSet<String>();
		this.name = name;
	}
	public void addPerformer(String dancer) {
		performers.add(dancer);
	}
	public void addGroup(Set<String> group) {
		performers.addAll(group);
	}
	public Set<String> getPerformers(){
		return performers;
	}
	public String toString() {
		String str = name + ": \n";
		str += performers.toString();
		return str;
	}
	
}
