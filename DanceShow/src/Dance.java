import java.util.SortedSet;
import java.util.TreeSet;

public class Dance {
	private SortedSet<Dancer> performers;
	
	public Dance() {
		performers = new TreeSet<Dancer>();
	}
	public void addPerformer(Dancer dancer) {
		performers.add(dancer);
	}
	
	public SortedSet<Dancer> getPerformers(){
		return performers;
	}
}
