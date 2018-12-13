import java.util.Collection;
/**
 * A RunningOrderGenerator of type dance which will provide a feasible 
 * running order of set of dances.
 * 
 * @author Melika Taghyoon
 * @version 13/12/2018
 */
public interface RunningOrderGenerator<T> {
	
	/**
	 * Generates a collection of objects of generic type
	 * which comply with the given gap requirement
	 * @param gap an integer which represents these requirements
	 * @return the collection of the objects in the running order
	 */
	Collection<T> generateOrder(int gap);
}
