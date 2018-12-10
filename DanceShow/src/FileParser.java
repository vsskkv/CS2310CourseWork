
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
	public boolean hasNext() {
		return sc.hasNext();
	}
	public String getNextLine() {
		if(sc.hasNext()) {
			return sc.nextLine();
		}
		else {
			return null;
		}
	}

}
