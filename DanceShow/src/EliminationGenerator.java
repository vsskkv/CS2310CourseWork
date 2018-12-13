import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * This running order generator does function correctly but it's developed to an extend
 * 
 * @author Melika Taghyoon
 * @version 04/12/2018
 */
public class EliminationGenerator implements RunningOrderGenerator<Dance> {
	private HashMap<String,Dance> dances;
	
	public EliminationGenerator(HashMap<String,Dance> dances) {
		this.dances = dances;
	}
	private List<Dance> allDances;
	@Override
	public List<Dance> generateOrder(int gap) {
		List<Dance> generated = new ArrayList<Dance>();
		allDances = new ArrayList<Dance>(dances.values());
		//Iterator<Dance> iter = allDances.iterator();

		for (Iterator<Dance> iterator = allDances.iterator(); iterator.hasNext();) {
			Dance dance = iterator.next();
			if(generated.size()<= 0) {
				generated.add(dance);
			}
			else if(allDancersReady(dance, gap)) {
				generated.add(dance);
				for(Dancer dancer: dance.getPerformers()) {
					dancer.setPerformed();
				}
				iterator.remove();

				Set<Dancer> dancers = new HashSet<Dancer>();
				for(Dance tempo: allDances) {
					dancers.addAll(tempo.getPerformers());
				}
				dancers.removeAll(dance.getPerformers());
				for(Dancer dancertemp: dancers) {
					dancertemp.notPerformed();
				}
			}
		}
		
		return generated;
	}
	private void setAllDancersRest(Dance dance, Iterator<Dance> iter) {
		Set<Dancer> dancers = new HashSet<Dancer>();
		for(Dance tempo: allDances) {
			dancers.addAll(tempo.getPerformers());
		}
		dancers.removeAll(dance.getPerformers());
		for(Dancer dancertemp: dancers) {
			dancertemp.notPerformed();
		}
		
	}
	private boolean allDancersReady(Dance dance, int givenGap) {
		for(Dancer dancer: dance.getPerformers()) {
			dancer.setGap(givenGap);
			if(!dancer.isReady()) {
				return false;
			}
		}
		return true;
	}
}
