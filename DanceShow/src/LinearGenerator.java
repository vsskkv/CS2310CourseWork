import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This running order generator does function correctly but it's developed to an extend
 * 
 * @author Melika Taghyoon
 * @version 04/12/2018
 */
public class LinearGenerator implements RunningOrderGenerator<Dance>{
	private HashMap<String,Dance> dances;
	
	public LinearGenerator(HashMap<String,Dance> dances) {
		this.dances = dances;
	}

	@Override
	public List<Dance> generateOrder(int gap) {
		List<Dance> tempDances = new ArrayList<Dance>(dances.values());
		List<Dance> generatedOrders = new ArrayList<Dance>();
		Set<Dance> recycles = new HashSet<Dance>();
		//Set<Dance> impossibles = new HashSet<Dance>();
		Set<Dancer> allDancers = new HashSet<Dancer>();
		for(Dance dan: tempDances) {
			allDancers.addAll(dan.getPerformers());
		}

		for(Dance dance : tempDances) {
			
			if(allDancersReady(dance, gap)) {
				generatedOrders.add(dance);
				for(Dancer dan: dance.getPerformers()) {
					dan.setPerformed();
				}
				allDancers.removeAll(dance.getPerformers());
				for(Dancer dancer: allDancers) {
					dancer.notPerformed();
				}

			}else {
				recycles.add(dance);
			}
			
		}
		/*
		 * Set<Dancer> remaining = new HashSet<Dancer>();
			for(Dance dan: tempDances) {
				remaining.addAll(dan.getPerformers());
			}
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
		
			if(!dancer.isReady()) {
				return false;
			}
		}
		
		return true;
	}
}
