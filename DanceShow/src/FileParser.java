
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This is a file parser which when given a file it will read it line by line
 * 
 * @author Vikram Kainth
 * @version 01/12/2018
 */
public class FileParser {
	private File file;
	private Scanner sc;

	/**
	 * @param fp a file object to open
	 */
	public FileParser(File fp) throws FileNotFoundException {
		file = fp;
		//creates a new scanner
		sc = new Scanner(file);
	}
	/**
	 * will close the file to prevent error
	 * */
	public void closeFile() {
		sc.close();
	}
	/**
	 * Will determine if the file has any more lines
	 * @return boolean representing if there are any more lines left in the file
	 */
	public boolean hasNext() {
		return sc.hasNext();
	}
	
	/**
	 * Will provide the next line of the file to the client if it has any
	 * @return String the next line of the file
	 */
	public String getNextLine() {
		if(sc.hasNext()) {
			return sc.nextLine();
		}
		else {
			return null;
		}
	}

}
