import com.sun.jdi.PathSearchingVirtualMachine;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class creates the handler.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public final class ReservationHandler implements Runnable {
    private Socket clientSocket;
    private static Passenger p;
    private static String flightType;
    private static BoardingPass bp;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private DataInputStream dis;


    public ReservationHandler(Socket clientSocket)
            throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;

    }


    public void run() {
        try {
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Hello I'm at the beginning trying to read the flight type");

        try {
            Airline.getPassengerList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("START READ UTF");
            flightType = dis.readUTF();
            System.out.println(flightType + "has been read by the handler");
        } catch (EOFException e){
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to read the passenger");
        try {
            Airline.getPassengerList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            p = (Passenger) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("passenger is read by handler" + p.getfName() + " " + p.getlName() );
        try {
            Airline.getPassengerList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (flightType.equals("Delta")) {
            try {
                bp = processRequest(p, ReservationServer.getFlights().get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (flightType.equals("Southwest")) {
            try {
                bp = processRequest(p, ReservationServer.getFlights().get(1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (flightType.equals("Alaska")) {
            try {
                bp = processRequest(p, ReservationServer.getFlights().get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Airline.getPassengerList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BoardingPass processRequest(Passenger p, Airline a) throws IOException {
        Airline.getPassengerList();
        if (a.getNumOfReservations() != a.getTotalReservations()) {
            a.addPassengers(p);
            Airline.printPassengerList();
            return new BoardingPass(a, p);
        } else {
            System.out.println("This flight wasn't successfully booked!!! The flight you booked was full!");
            return null;
        }
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ReservationHandler) {
            return Objects.equals(this.clientSocket, ((ReservationHandler) object).clientSocket);
        } else {
            return false;
        }
    }
}