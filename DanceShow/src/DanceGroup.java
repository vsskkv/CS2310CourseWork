
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DanceGroup {
	private ArrayList<Dancer> dancers;
	private String name;
	
	public DanceGroup() {
		dancers = new ArrayList<Dancer>();
	}
	public void addDancer(Dancer dancer) {
		dancers.add(dancer);
	}
	public void setName(String name) {
		this.name= name;
	}
	public ArrayList<Dancer> getDancers(){
		return dancers;
	}
	
	public String toString() {
		Collections.sort(dancers);
		String str = name + " : ";
		for (Dancer dancer : dancers) {
			str += dancer.toString();
		}
		
		return str;
	}
}
