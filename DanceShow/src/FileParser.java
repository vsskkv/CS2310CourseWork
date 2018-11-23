
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

	private File file;
	private Scanner sc;
	//private Queue<String> fileArray;

	public FileParser(File fp) throws FileNotFoundException {

		file = fp;
		sc = new Scanner(file);
	}

//	private void fillArray() {
//
//		while (sc.hasNext()) {
//			fileArray.add(sc.nextLine());
//			
//		}
//	}

	public String getNextLine() {
		return sc.nextLine();
	}

}