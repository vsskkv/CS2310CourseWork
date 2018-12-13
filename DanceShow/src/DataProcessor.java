import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * A data processor which will read all the data from the specified files
 * and will create correct data structures put them into collections
 * and return those collections
 * 
 * @author Vikram Kainth, Melika Taghyoon
 * @version 07/12/2018
 */
public class DataProcessor {
	private FileParser parser;
	private Map<String, DanceGroup> danceGroups;
	private Map<String, Dance> dances;
	
	public DataProcessor() {
		//construct empty hashmaps for the data to be added to
		danceGroups = new HashMap<String, DanceGroup>();
		dances = new HashMap<String, Dance>();
	}
	
	/**
	 * Reads the dance groups file and creates dance groups
	 * also will add the dancers for those dance groups to them 
	 * @throws FileNotFoundException 
	 */
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
	
	/**
	 * Creates all the dances from the file, will add all the performers to it
	 * Uses the processed dancegroups to do so 
	 * @see processDanceGroups()
	 * @return a HashMap of the dances 
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Will find out if a given name is a group name or not
	 * @param name a String
	 * @return boolean representing if 
	 * 
	 */
	private boolean identifyGroup(String name) {
		return danceGroups.containsKey(name);
	}
	
	/**
	 * Produces a set of dances which were read from the file
	 * @param filename a String representing the file name 
	 * @return set of dances 
	 */
	public Set<Dance> processRunningOrder(String filename) throws FileNotFoundException {
		String path = "src/csv/"+ filename;
		File dir = new File(path);
		try{
			parser = new FileParser(dir);
		}catch(FileNotFoundException e) {
			parser.closeFile();
			throw e;
		}
		
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
