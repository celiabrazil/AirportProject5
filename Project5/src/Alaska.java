import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates an Alaska flight with passengers.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public class Alaska extends Airline implements Serializable {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static int numReservations = 0;
    private static boolean full = false;
    private static int totalReservations = 5;

    public Alaska(){
        super("Southwest");
        setReservations();
    }

    public static void setNumOfReservations(int numReservations) {
        Alaska.numReservations = numReservations;
    }

    public static void setTotalOfReservations(int totalReservations) {
        Alaska.totalReservations = totalReservations;
    }

    public void setReservations() {
        Passenger narain = new Passenger("A.", "Narain", "20");
        Passenger abhyankar = new Passenger("K.", "Abhyankar", "19");
        Passenger clayman = new Passenger("N.", "Clayman", "19");
        passengers.add(narain);
        passengers.add(abhyankar);
        passengers.add(clayman);
        numReservations = passengers.size();
        ReservationServer.addFlights(this);
    }


    public int getNumOfReservations() {
        return numReservations;
    }

    public int getTotalReservations() {
        return totalReservations;
    }

    public void addPassengers(Passenger p){
        passengers.add(p);
        Alaska.numReservations = passengers.size();
    }

    public ArrayList<Passenger> getPassengers() {
        return Alaska.passengers;
    }

    @Override
    public String getAirlineName() {
        return "Alaska";
    }

    @Override
    public boolean isFull() {
        return full;
    }

    public static void setFull(boolean full) {
        Alaska.full = full;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        Alaska.passengers = passengers;
    }
}
