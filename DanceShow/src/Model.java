
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Model implements Controller{
	private final int NO_DANCERS_GENERATED = 30;
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
	private Set<Dance> processRunningOrder(String filename) throws FileNotFoundException {
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
	
	@Override
	public String listAllDancersIn(String dance) {
		if(dances.containsKey(dance.trim())) 
		{
			return "All dances in "+ dance + " \n" + dances.get(dance).toString();
		}
		
		return "Dance not found";
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
		String result = checkFeasible(danceOrders, gaps);
		if(result.equals("")) {
			return "This running order is feasible";
		}
		return "This running order is not feasible \n" + result;
	}
	private String checkFeasible(Collection<Dance> danceOrders, int gaps){
		String issues = "";
		for(Dance dance: danceOrders) {
			for(Dancer dancer: dance.getPerformers()) {
				dancer.setGap(gaps);
				if(dancer.isReady()) {
					dancer.setPerformed();
				}
				else {
					issues += "The dancer " + dancer.toString() + " is not yet ready to perform the dance "+ dance.getName() + "\n";
				}
			}
			Set<Dance> tempOrders = new HashSet<Dance>(danceOrders);
			tempOrders.remove(dance);
			Set<Dancer> remaining = new HashSet<Dancer>();
			for(Dance dan: tempOrders) {
				remaining.addAll(dan.getPerformers());
			}
			for(Dancer remain: remaining) {
				remain.notPerformed();
			}
		}
		
		return issues;
	}
	@Override
	public String generateRunningOrder(int gaps) {
		List<Dance> result = null;
		boolean isfeasible = false;
		
		int counter = 100;
		do {
			result = generateOrder2(gaps);
			String check = checkFeasible(result, gaps);
			if(check.equals("")) {
				isfeasible = true;
			}
			counter--;
		}
		while(!isfeasible && counter>= 0 );
		String str = "";
		if(isfeasible && result != null) {
			for(Dance dance: result) {
				str += dance.getName() + "\n \t" + dance.getPerformers().toString() + "\n\n";
			}
		}
		else {
			str += "No running order could be generated";
		}
		
		
		return str;
	}
	
	private Set<Dance> generateOrder(int gap) {
		Set<Dance> generatedOrder = new HashSet<Dance>();
		Random generator = new Random();
		
		Dance previ = null;
		Object[] values = dances.values().toArray();
		for (int i = 0; i < NO_DANCERS_GENERATED; i++) {
			Dance randomValue = (Dance) values[generator.nextInt(values.length)];
			if(previ == null || !previ.isSubset(randomValue)) {
				generatedOrder.add(randomValue);
				previ = randomValue;
			}
			
		}
		Set<Dancer> allDancers = new HashSet<Dancer>();
		Set<Dance> tempOrder =  new HashSet<Dance>(generatedOrder);
		for (Dance dance : generatedOrder) {
			if(allDancers.addAll(dance.getPerformers())) {
				//remove that dance from generated order
				tempOrder.remove(dance);
			}
			
		}
		generatedOrder = tempOrder;
		Set<Dance> check = getProblematics(generatedOrder, gap);
		if(check.size()>0) {
			for(Dance dance:check) {
				generatedOrder.remove(dance);
			}
		}
		return generatedOrder;
	}
	private Set<Dance> getProblematics(Set<Dance> danceOrders, int gaps){
		Set<Dance> problems= new HashSet<>();
		for(Dance dance: danceOrders) {
			for(Dancer dancer: dance.getPerformers()) {
				dancer.setGap(gaps);
				if(dancer.isReady()) {
					dancer.setPerformed();
				}
				else {
					problems.add(dance);
				}
			}
			Set<Dance> tempOrders = new HashSet<Dance>(danceOrders);
			tempOrders.remove(dance);
			Set<Dancer> remaining = new HashSet<Dancer>();
			for(Dance dan: tempOrders) {
				remaining.addAll(dan.getPerformers());
			}
			for(Dancer remain: remaining) {
				remain.notPerformed();
			}
		}
		
		return problems;
	}

	private List<Dance> generateOrder2(int gap){
		Set<Dance> tempDances = new HashSet<Dance>(dances.values());
		List<Dance> generatedOrders = new ArrayList<Dance>();
		Set<Dance> recycles = new HashSet<Dance>();
		Set<Dance> impossibles = new HashSet<Dance>();
		
		for(Dance dance : tempDances) {
			if(allDancersReady(dance, gap)) {
				generatedOrders.add(dance);
			}else {
				recycles.add(dance);
			}
			
			Set<Dance> tempOrders = new HashSet<Dance>(tempDances);
			tempOrders.remove(dance);
			Set<Dancer> remaining = new HashSet<Dancer>();
			for(Dance dan: tempOrders) {
				remaining.addAll(dan.getPerformers());
			}
			for(Dancer remain: remaining) {
				remain.notPerformed();
			}
		}
		/*
		for(Dance recycle: recycles) {
			for(Dance dance : generatedOrders) {
				if(allDancersReady)}
			{
			}
		}
		*/
		return generatedOrders;
	}
	private boolean allDancersReady(Dance dance, int gap) {
		for(Dancer dancer: dance.getPerformers()) {
			dancer.setGap(gap);
			if(dancer.isReady()) {
				dancer.setPerformed();
			}
			else {
				return false;
			}
		}
		return true;
	}
}

