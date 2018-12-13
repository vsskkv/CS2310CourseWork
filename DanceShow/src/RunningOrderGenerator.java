import java.util.Collection;

public interface RunningOrderGenerator<T> {
	Collection<T> generateOrder(int gap);
}
