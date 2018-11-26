import java.util.HashMap;
import java.util.Scanner;

public class ChristmasLights {  
    public static void main(String[] args) {
    	HashMap<Integer, ModeControl> modes = new HashMap<Integer, ModeControl>();
    	modes.put(0, new ModeOn());
    	modes.put(1, new ModeBlinking());
    	modes.put(2, new ModeOff());
    	
    	int modeCounter = 0;
    	
        while (true) {
            System.out.print(
               "Press ENTER to change the mode of functioning... "
                            );
            Scanner in = new Scanner(System.in);
            in.nextLine();
            modes.get(modeCounter).handlePress();
            modeCounter++;
            modeCounter = modeCounter%modes.size();
        }
    }
}