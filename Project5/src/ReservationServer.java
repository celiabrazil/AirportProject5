import java.io.Serializable;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class creates a server.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public class ReservationServer {

    private ServerSocket serverSocket;
    private static ArrayList<Airline> flights = new ArrayList<>();
    private static Delta delta = new Delta();
    private static Southwest southwest = new Southwest();
    private static Alaska alaska = new Alaska();





    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
    }

    public void serveClients() throws InterruptedException, IOException {
        InetAddress address;
        int port = this.serverSocket.getLocalPort();
        Socket clientSocket;
        int clientCount = 0;
        ReservationHandler requestHandler;
        Thread requestHandlerThread;
//
//        addFlights(delta);
//        addFlights(southwest);
//        addFlights(alaska);

        Airline.printPassengerList();

        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }

        System.out.printf("<Now serving clients at %s on port %d...>%n", address.getCanonicalHostName(), port);

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            System.out.printf("<Client %d connected...>%n", clientCount);
            clientCount++;

            requestHandlerThread = new Thread(new ReservationHandler(clientSocket));
            requestHandlerThread.start();
        }


    }

    public static ArrayList<Airline> getFlights() {
        return flights;
    }

    public static void setFlights(ArrayList<Airline> flights) {
        ReservationServer.flights = flights;
    }

    public static void addFlights(Airline a){
        flights.add(a);
    }



//    The server can handle multiple clients simultaneously.
//    It will track and record ticket sales by writing to reservations.txt.
//    A sample reservations file can be found in the Starter Code folder.
//    Your program will need to be able to create and write to a file in a similar format.
//    Example: Client 1 and Client 2 are both connected to the server and booking a Southwest flight.
//    If Client 1 books the last Southwest ticket, Client 2 should no longer be able to book it.



}