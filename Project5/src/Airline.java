import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.io.*;
import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;

/**
 * This class creates an Airline object that handles the gate number and printing the passengers.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public abstract class Airline implements Serializable {
    private static Gate g;
    private static String airlineName;
    private static File reservations;
    private static boolean full = false;


    public Airline(String airlineName) {
        this.airlineName = airlineName;
        this.g = new Gate();
        this.reservations = new File("Reservations.txt");
    }


    public static void printPassengerList() throws IOException {
        File f = new File("Reservations.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        System.out.println("HERE ARE THE NUM OF FLIGHTS" + ReservationServer.getFlights().size());
        for (Airline a : ReservationServer.getFlights()) {
            pw.write(a.getAirlineName());
            String occupancy = String.format("\n%d/%d\n", a.getNumOfReservations(), a.getTotalReservations());
            pw.write(occupancy);
            pw.flush();
            ArrayList<Passenger> flightPassengers = a.getPassengers();
            for (Passenger p : flightPassengers) {
                String passengerName = String.format("%s. %s, %s\n", p.getfName().substring(0, 1), p.getlName(), p.getAge());
                String space = String.format("        ---------------------%s\n", a.getAirlineName().toUpperCase());
                pw.write(passengerName);
                pw.write(space);
            }
            pw.write("\n");
        }

        pw.flush();
    }

    protected ArrayList<Passenger> getPassengers() {
        return null;
    }

    protected abstract int getNumOfReservations();


    public static void getPassengerList() throws IOException {
        ArrayList<Passenger> deltaPassengers = new ArrayList<>();
        ArrayList<Passenger> southwestPassengers = new ArrayList<>();
        ArrayList<Passenger> alaskaPassengers = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        int counter = 0;

        try (BufferedReader bfr = new BufferedReader(new FileReader(new File("Reservations.txt")))) {
            while (true) {
                String s = bfr.readLine();

                if (s == null) {
                    break;
                }
                if (s.contains("---------------------DELTA")) {
                    String passenger = lines.get(counter - 1);
                    String firstName = passenger.substring(0, passenger.indexOf("."));
                    String lastName = passenger.substring(passenger.indexOf(".") + 1, passenger.indexOf(","));
                    String age = passenger.substring(passenger.indexOf(",") + 1);
                    Passenger p = new Passenger(firstName, lastName, age);
                    System.out.println("Delta" + p.getfName());
                    deltaPassengers.add(p);
                }
                if (s.contains("---------------------SOUTHWEST")) {
                    String passenger = lines.get(counter - 1);
                    String firstName = passenger.substring(0, passenger.indexOf("."));
                    String lastName = passenger.substring(passenger.indexOf(".") + 1, passenger.indexOf(","));
                    String age = passenger.substring(passenger.indexOf(",") + 1);
                    Passenger p = new Passenger(firstName, lastName, age);
                    southwestPassengers.add(p);
                }
                if (s.contains("---------------------ALASKA")) {
                    String passenger = lines.get(counter - 1);
                    String firstName = passenger.substring(0, passenger.indexOf("."));
                    String lastName = passenger.substring(passenger.indexOf(".") + 1, passenger.indexOf(","));
                    String age = passenger.substring(passenger.indexOf(",") + 1);
                    Passenger p = new Passenger(firstName, lastName, age);
                    alaskaPassengers.add(p);
                }


                if (s.contains("/") && lines.get(counter-1).equals("Delta")) {
                    String numReservations = s.substring(0,s.indexOf("/"));
                    String totalReservations = s.substring(s.indexOf("/")+1);
                    Delta.setNumOfReservations(Integer.parseInt(numReservations));
                    Delta.setTotalOfReservations(Integer.parseInt(totalReservations));
                }
                if (s.contains("/") && lines.get(counter-1).equals("Southwest")) {
                    String numReservations = s.substring(0,s.indexOf("/"));
                    String totalReservations = s.substring(s.indexOf("/")+1);
                    Delta.setNumOfReservations(Integer.parseInt(numReservations));
                    Southwest.setTotalOfReservations(Integer.parseInt(totalReservations));
                }
                if (s.contains("/") && lines.get(counter-1).equals("Alaska") ) {
                    String numReservations = s.substring(0,s.indexOf("/"));
                    String totalReservations = s.substring(s.indexOf("/")+1);
                    Delta.setNumOfReservations(Integer.parseInt(numReservations));
                    Alaska.setTotalOfReservations(Integer.parseInt(totalReservations));
                }
                lines.add(s);
                counter++;
            }

            ReservationServer.getFlights().get(0).setPassengers(deltaPassengers);
            ReservationServer.getFlights().get(1).setPassengers(southwestPassengers);
            ReservationServer.getFlights().get(2).setPassengers(alaskaPassengers);

            System.out.println("INTERESTING FIND");
            System.out.println(ReservationServer.getFlights().get(0).getPassengers().size());
            System.out.println("END INTERESTING FIND");


            if (ReservationServer.getFlights().get(2).getPassengers().size() >= ReservationServer.getFlights().get(2).getTotalReservations()){
                Alaska.setFull(true);
            } else {
                Alaska.setFull(false);
            }

            if (ReservationServer.getFlights().get(0).getPassengers().size() >= ReservationServer.getFlights().get(0).getTotalReservations()){
                System.out.println("Delta is full");
                Delta.setFull(true);
            } else {
                Delta.setFull(false);
            }

            if (ReservationServer.getFlights().get(1).getPassengers().size() >= ReservationServer.getFlights().get(1).getTotalReservations()){
                Southwest.setFull(true);
            } else {
                Delta.setFull(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected abstract void setPassengers(ArrayList<Passenger> deltaPassengers);


    public Gate getG() {
        return this.g;
    }


    public abstract int getTotalReservations();

    public void addPassengers(Passenger p) {
    }

    public abstract String getAirlineName();

    public abstract boolean isFull();

}
