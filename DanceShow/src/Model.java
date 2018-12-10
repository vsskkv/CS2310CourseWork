import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Model {
	private HashMap<String, DanceGroup> danceGroups;
	private HashMap<String, Dance> dances;
	private FileParser parser;
	public Model() throws FileNotFoundException {
		danceGroups = new HashMap<String, DanceGroup>();
		dances = new HashMap<String, Dance>();
		processDanceGroups();
		processDances();
	}
	public void display() {
		//for (String dancegroup : danceGroups.keySet()) {
			//System.out.println(danceGroups.get(dancegroup).toString());
		//
		//}
		for (Dance dance : dances.values()) {
			System.out.println(dance.toString());
		}
	}
	private void processDances() throws FileNotFoundException {
		File dir = new File("src/csv/danceShowData_dances.csv");
		parser = new FileParser(dir);
		//parser.getNextLine(); 
		while(parser.hasNext()) {
			
			String danceline[] = parser.getNextLine().split("\t");

			Dance dance = new Dance(danceline[0]);
			String dancers[] = danceline[1].split(",");
			
			for (String name : dancers) {
				//System.out.print(identifyGroup(name));
				name = name.trim();
				if(identifyGroup(name)) {
					dance.addGroup(danceGroups.get(name).getDancers());
				}else {
					dance.addPerformer(name);
				}
			}
			dances.put(danceline[0], dance);
		}	
	}
	private boolean identifyGroup(String name) {
		return danceGroups.containsKey(name);
	}
	private void processDanceGroups() throws FileNotFoundException {
		File dir = new File("src/csv/danceShowData_danceGroups.csv");
		parser = new FileParser(dir);
		while(parser.hasNext()) {
			String groupLine[] = parser.getNextLine().split("\t");
			DanceGroup group = new DanceGroup();
			group.setName(groupLine[0]);
			String dancers[] = groupLine[1].split(",");
			for (String name : dancers) {
				group.addDancer(name.trim());
			}
			danceGroups.put(groupLine[0], group);
		}
	}
}
