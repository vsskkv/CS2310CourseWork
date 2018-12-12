
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Model implements Controller{
	private HashMap<String, DanceGroup> danceGroups;
	private HashMap<String, Dance> dances;
	private FileParser parser;
	public Model() throws FileNotFoundException {
		danceGroups = new HashMap<String, DanceGroup>();
		dances = new HashMap<String, Dance>();
		processDanceGroups();
		processDances();
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
				name = name.trim();
				if(identifyGroup(name)) {
					dance.addGroup(danceGroups.get(name).getDancers());
				}else {
					Dancer d = new Dancer(name);
					dance.addPerformer(d);
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
				Dancer d = new Dancer(name.trim());
				group.addDancer(d);
			}
			danceGroups.put(groupLine[0], group);
		}
	}
	private Set<Dance> processRunningOrder(String filename) throws FileNotFoundException {
		String path = "src/csv/"+ filename;
		File dir = new File(path);
		parser = new FileParser(dir);
		List<String> results = new ArrayList<String>();
		
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
	
	@Override
	public String listAllDancersIn(String dance) {
		return "All dances in "+ dance + " \n" + dances.get(dance).toString();
	}
	
	@Override
	public String listAllDancesAndPerformers() {
		List<String> mapKeys = new ArrayList<String>(dances.keySet());
		Collections.sort(mapKeys);
		String str = "";
		for (String dance : mapKeys) {
			
			str += dance + ": \n";
			str += dances.get(dance).toString()+"\n";
			str += "\n";
		}
		return str;
	}
	
	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		Set<Dance> danceOrders = null;
		try {
			danceOrders = processRunningOrder(filename);
		} catch (FileNotFoundException e) {
			System.out.println("not found");
			e.printStackTrace();
		}
		
		dance
		return danceOrders.toString();
	}
	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}
}
