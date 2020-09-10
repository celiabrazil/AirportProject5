import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates a Delta airline object.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public class Delta extends Airline implements Serializable {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static int numOfReservations = 0;
    private static int totalReservations = 5;
    private static boolean full = false;


    public Delta(){
        super ("Delta");
        setReservations();
        ReservationServer.getFlights().add(this);
    }

    public static void setNumOfReservations(int numOfReservations) {
        Delta.numOfReservations = numOfReservations;
    }

    public static void setTotalOfReservations(int totalReservations) {
        Delta.totalReservations = totalReservations;
    }

    public void setReservations() {
        Passenger westmeyer = new Passenger("C.", "Westmeyer", "19");
        Passenger abhyankar = new Passenger("K.", "Abhyankar", "19");
        passengers.add(westmeyer);
        passengers.add(abhyankar);
        numOfReservations = passengers.size();
    }


    public int getNumOfReservations() {
        return this.numOfReservations;
    }

    public int getTotalReservations() {
        return totalReservations;
    }

    public String getAirlineName() {
        return "Delta";
    }

    public void addPassengers(Passenger p){
        passengers.add(p);
        numOfReservations = passengers.size();
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public boolean isFull() {
        return this.full;
    }

    public static void setFull(boolean full) {
        Delta.full = full;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
