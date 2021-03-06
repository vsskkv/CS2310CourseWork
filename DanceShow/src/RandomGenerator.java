import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
/**
 * A RunningOrderGenerator of type dance which will provide a feasible 
 * running order of set of dances.
 * 
 * @author Melika Taghyoon
 * @version 13/12/2018
 */
public class RandomGenerator implements RunningOrderGenerator<Dance>{
	private HashMap<String,Dance> dances;
	private final int NO_DANCERS_GENERATED = 30;
	
	public RandomGenerator(HashMap<String,Dance> dances) {
		this.dances = dances;
	}
	
	/**
	 * Generates a collection of objects of generic type
	 * which comply with the given gap requirement
	 * @param gap an integer which represents these requirements
	 * @return the collection of the objects in the running order
	 */
	@Override
	public List<Dance> generateOrder(int gap) {
		List<Dance> generatedOrder = new ArrayList<Dance>();
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
		List<Dance> tempOrder =  new ArrayList<Dance>(generatedOrder);
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

	/**
	 * list all the dance objects which were not complying with the gap requirement
	 * 
	 * @param gap an integer which represents these requirements
	 * @param danceOrders the generated list of dances
	 * @return the set of the dances in the running order
	 */
	private Set<Dance> getProblematics(List<Dance> danceOrders, int gaps){
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
}
