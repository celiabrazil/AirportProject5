import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates an Southwest flight with passengers.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */


public class Southwest extends Airline implements Serializable {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static int numOfReservations = 0;
    private static boolean full = false;
    private static int totalReservations = 5;

    public Southwest(){
        super("Southwest");
        setReservations();
        ReservationServer.getFlights().add(this);
    }

    public static void setNumOfReservations(int val) {
        Southwest.numOfReservations = val;
    }

    public static void setTotalOfReservations(int totalReservations) {
        Southwest.totalReservations = totalReservations;
    }

    public void setReservations() {
        Passenger westmeyer = new Passenger("C.", "Westmeyer", "19");
        passengers.add(westmeyer);
        numOfReservations = passengers.size();
    }

    public int getNumOfReservations() {
        return numOfReservations;
    }

    public int getTotalReservations() {
        return Southwest.totalReservations;
    }

    public void addPassengers(Passenger p) {
        passengers.add(p);
        numOfReservations = passengers.size();
    }

    public ArrayList<Passenger> getPassengers() {
        return Southwest.passengers;
    }

    public String getAirlineName() {
        return "Southwest";
    }

    @Override
    public boolean isFull() {
        return this.full;
    }

    public static void setFull(boolean full) {
        Southwest.full = full;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        Southwest.passengers = passengers;
    }
}
