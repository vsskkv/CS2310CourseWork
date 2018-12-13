import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
/**
 * An Output which will write the generated running order to a file
 * 
 * @author Melika Taghyoon
 * @version 13/12/2018
 */
public class Output {
	public Output() {
	}
	
	/**
	 * will output the given data to the file in correct format
	 * 
	 * @param data which is a list of dances to be outputted
	 * */
	public void outputToFile(List<Dance> data) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("Outputs/GeneratedRunningOrder.txt", "UTF-8");
		writer.println("Dance\tPerformers");
		for(Dance entry: data) {
			String dancers = "";
			for(Dancer dancer: entry.getPerformers() ) {
				dancers += dancer.toString() + ",";
			}
			writer.println(entry.getName()+"\t"+ dancers);
		}
		writer.close();
	}
	
}
