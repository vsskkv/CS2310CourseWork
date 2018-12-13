
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * A controller for the dance show programme generator system.
 * This controller includes the implementations of the 4 features a controller 
 * intends to have 
 * 
 * @author Melika Taghyoon
 * @version 13/12/2018
 */

public class Model implements Controller{
	
	private HashMap<String, Dance> dances;
	private DataProcessor dataProcessor;
	private RunningOrderGenerator<Dance> generator;
	
	public Model() throws FileNotFoundException {
		dataProcessor = new DataProcessor();
		dances = (HashMap)dataProcessor.processDances();
	}
	
	/**
	 * Lists the names of all performers in a specified dance.
	 * @param dance	a specified dance in the dance show
	 * @return the name of all performers that are in the specified dance. 
	 */
	@Override
	public String listAllDancersIn(String dance) {
		if(dances.containsKey(dance.trim())) 
		{
			return "All dances in "+ dance + " \n" + dances.get(dance).toString();
		}
		
		return "The given dance name could not be found";
	}
	
	/**
	 * Lists all dance numbers and the name of the respective performers in alphabetical order.
	 * @return	a String representation of dance numbers 
	 * 			and the name of the respective performers in alphabetical order
	 */
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
	/**
	 * Checks feasibility of a given running order.
	 * @param filename	the name of a tab-separated CSV file containing a proposed running order
	 * @param gaps the required number of gaps between dances for each dancer
	 * @return	a String representation of potential issues
	 */
	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		Set<Dance> danceOrders = null;
		try {
			danceOrders = dataProcessor.processRunningOrder(filename);
		} catch (FileNotFoundException e) {
			return "The given file could not be found";
		}
		String result = checkFeasible(danceOrders, gaps);
		if(result.equals("")) {
			return "This running order is feasible";
		}
		return "This running order is not feasible \n" + result;
	}
	
	/**
	 * A helper method to check whether a running order complies with the 
	 * gaps required or not
	 * @param gaps the required number of gaps between dances for each dancer
	 * @param  danceOrders a collection of dances to do checks against
	 * @return	a String including all the conflicts with a running order
	 */
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
	/**
	 * Generates a running order of the dances for the dance show.
	 * @param gaps the required number of gaps between dances for each dancer
	 * @return	a String representation of the generate running order
	 */
	@Override
	public String generateRunningOrder(int gaps) {
		generator = new RandomGenerator(dances);
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
			Output output = new Output();
			try {
				output.outputToFile(result);
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Failed outputting to file due to file not being found");
				e.printStackTrace();
			}
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

