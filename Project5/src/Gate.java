import java.io.Serializable;

/**
 * This class creates a Gate object.
 *
 * @author Celia Brazil and Simran
 * @version 11/17/2019
 */

public class Gate implements Serializable {
    private static boolean [][] arr = new boolean[3][18];
    private int terminal;
    private int gateNum;
    private String terminalLetter;

    Gate(){
        do {
            terminal = (int)(Math.random())+2;
            gateNum = (int)(Math.random()*17)+1;
        } while (arr[terminal - 1][gateNum - 1]);
        arr[terminal][gateNum] = true;
    }

    @Override
    public String toString() {
        if (terminal == 0){
            terminalLetter = "A";
        } else if (terminal == 1){
            terminalLetter = "B";
        } else if (terminal == 2){
            terminalLetter = "C";
        }
        return String.format("%s%d", terminalLetter, gateNum);
    }
}
