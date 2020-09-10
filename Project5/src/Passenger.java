import java.io.Serializable;

/**
 * This class creates a Passenger with a name and an age.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public class Passenger implements Serializable {
    String fName;
    String lName;
    String age;

    public Passenger (String fName, String lName, String age){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }
}
