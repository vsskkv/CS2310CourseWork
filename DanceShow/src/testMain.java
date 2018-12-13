import java.io.FileNotFoundException;
/**
 * A main class to run the DanceShow programme
 * 
 * @author Melika Taghyoon
 * @version 1/12/2018
 */
public class testMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Model mode = new Model();
		TUI userInterface = new TUI(mode);
	}
}
