import java.util.SortedSet;
import java.util.TreeSet;

public class DanceGroup {
	private SortedSet<Dancer> dancers;
	
	public DanceGroup() {
		dancers = new TreeSet<Dancer>();
	}
	public void addDancer(Dancer dancer) {
		dancers.add(dancer);
	}
	
	public SortedSet<Dancer> getDancers(){
		return dancers;
	}
}
