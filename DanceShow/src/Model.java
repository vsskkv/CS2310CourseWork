
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
	
	private HashMap<String, Dance> dances;
	private DataProcessor dataProcessor;
	private RunningOrderGenerator<Dance> generator;
	
	public Model() throws FileNotFoundException {
		dataProcessor = new DataProcessor();
		dances = (HashMap)dataProcessor.processDances();
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
			danceOrders = dataProcessor.processRunningOrder(filename);
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
		generator = new LinearGenerator(dances);
		List<Dance> result = null;
		boolean isfeasible = false;
		
		int counter = 100;
		do {
			result = (List<Dance>) generator.generateOrder(gaps);
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



}

