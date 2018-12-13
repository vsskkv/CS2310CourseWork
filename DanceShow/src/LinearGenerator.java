import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinearGenerator implements RunningOrderGenerator<Dance>{
	private HashMap<String,Dance> dances;
	
	public LinearGenerator(HashMap<String,Dance> dances) {
		this.dances = dances;
	}

	@Override
	public List<Dance> generateOrder(int gap) {
		Set<Dance> tempDances = new HashSet<Dance>(dances.values());
		List<Dance> generatedOrders = new ArrayList<Dance>();
		Set<Dance> recycles = new HashSet<Dance>();
		//Set<Dance> impossibles = new HashSet<Dance>();
		
		for(Dance dance : tempDances) {
			if(allDancersReady(dance, gap)) {
				generatedOrders.add(dance);
			}else {
				recycles.add(dance);
			}
			
			Set<Dance> tempOrders = new HashSet<Dance>(tempDances);
			tempOrders.remove(dance);
			Set<Dancer> remaining = new HashSet<Dancer>();
			for(Dance dan: tempDances) {
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
