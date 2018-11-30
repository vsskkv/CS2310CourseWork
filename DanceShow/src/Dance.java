import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Dance {
	private ArrayList<Dancer> performers;
	
	public Dance() {
		performers = new ArrayList<Dancer>();
	}
	public void addPerformer(Dancer dancer) {
		performers.add(dancer);
	}
	
	public ArrayList<Dancer> getPerformers(){
		return performers;
	}
	
}
