import java.io.Serializable;

/**
 *
 * The Boarding Pass is created for a specific passenger, airline, and gate.
 * When printed, it should contain the airline’s name, passenger’s first and last names,
 * the passenger’s age, and the gate.
 *
 * @author Celia Brazil and Simran
 * @version 11/17/2019
 */
public class BoardingPass implements Serializable {
    private Airline airline;
    private String airlineName;
    private String fName;
    private String lName;
    private String age;
    private Gate gate;

    public BoardingPass(Airline a, Passenger p) {
        this.airline = a;
        this.airlineName = a.getAirlineName();
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.age = p.getAge();
        this.gate = a.getG();
    }

    public String toString() {
        return "<html>-----------------------------------------------------------------------<br>" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + airlineName +
                "<br>PASSENGER FIRST NAME: " + fName + "<br>PASSENGER LAST NAME: " + lName +
                "<br>PASSENGER AGE: " + age + "<br>You can now begin boarding at gate " + gate.toString() +
                "<br>----------------------------------------------------------------------</html>";
    }

}
