import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Model {
	private ArrayList<DanceGroup> danceGroups;
	
	private FileParser parser;
	public Model() throws FileNotFoundException {
		danceGroups = new ArrayList<DanceGroup>();
		processDanceGroups();
		
	}
	public void display() {
		for (DanceGroup dancegroup : danceGroups) {
			System.out.println(dancegroup.toString());
		}
	}
	private void processDanceGroups() throws FileNotFoundException {
		File dir = new File("src/csv/danceShowData_danceGroups.csv");
		parser = new FileParser(dir);
		
		while(parser.getNextLine() != null) {
			String groupLine[] = parser.getNextLine().split("\t");
			DanceGroup group = new DanceGroup();
			group.setName(groupLine[0]);
			String dancers[] = groupLine[1].split(",");
			for (String name : dancers) {
				Dancer dancer = new Dancer(name);
				group.addDancer(dancer);
			}
			danceGroups.add(group);
		}
	}
}
