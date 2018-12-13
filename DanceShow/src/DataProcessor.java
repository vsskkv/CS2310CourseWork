import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataProcessor {
	private FileParser parser;
	private Map<String, DanceGroup> danceGroups;
	private Map<String, Dance> dances;
	
	public DataProcessor() {
		danceGroups = new HashMap<String, DanceGroup>();
		dances = new HashMap<String, Dance>();
	}
	
	public Map<String, Dance> processDances() throws FileNotFoundException {
		processDanceGroups();
		File dir = new File("src/csv/danceShowData_dances.csv");
		parser = new FileParser(dir);
		parser.getNextLine(); 
		while(parser.hasNext()) {
			
			String danceline[] = parser.getNextLine().split("\t");

			Dance dance = new Dance(danceline[0].trim());
			String dancers[] = danceline[1].split(",");
			
			for (String name : dancers) {
				name = name.trim();
				if(identifyGroup(name)) {
					dance.addGroup(danceGroups.get(name).getDancers());
				}else {
					Dancer d = new Dancer(name);
					dance.addPerformer(d);
				}
			}
			dances.put(danceline[0].trim(), dance);
		}	
		return dances;
	}
	private boolean identifyGroup(String name) {
		return danceGroups.containsKey(name);
	}
	private void processDanceGroups() throws FileNotFoundException {
		File dir = new File("src/csv/danceShowData_danceGroups.csv");
		parser = new FileParser(dir);
		parser.getNextLine();
		while(parser.hasNext()) {
			String groupLine[] = parser.getNextLine().split("\t");
			DanceGroup group = new DanceGroup();
			group.setName(groupLine[0]);
			String dancers[] = groupLine[1].split(",");
			for (String name : dancers) {
				Dancer d = new Dancer(name.trim());
				group.addDancer(d);
			}
			danceGroups.put(groupLine[0], group);
		}
	}
	public Set<Dance> processRunningOrder(String filename) throws FileNotFoundException {
		String path = "src/csv/"+ filename;
		File dir = new File(path);
		parser = new FileParser(dir);
		List<String> results = new ArrayList<String>();
		parser.getNextLine();
		while(parser.hasNext()) {
			String[] temp = parser.getNextLine().split("\t");
			results.add(temp[0]);
		}
		
		Set<Dance> tempDancers = new HashSet<Dance>();
		for (String dance : results) {
			if(dances.containsKey(dance)) {
				tempDancers.add(dances.get(dance));
			}
		}
		return tempDancers;
		
	}
}
