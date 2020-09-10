import java.io.*;
import java.net.Socket;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the reservation client.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public final class ReservationClient implements Serializable {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static DataOutputStream dos;
    private static DataInputStream dis;
    private static Passenger p;


    private static boolean isValid(String portString) {
        return portString.chars()
                .mapToObj(character -> (char) character)
                .map(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String hostName;
        String portString;
        int port;
        Socket clientSocket;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        String request;
        String response;
        Scanner sc = new Scanner(System.in);
        HandleGUI hg = new HandleGUI();

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An exception has occurred! Goodbye!",
                    "ReservationClient", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        hostName = JOptionPane.showInputDialog(null,
                "What is the hostname you'd like to?:",
                "ReservationClient", JOptionPane.QUESTION_MESSAGE);
        if (hostName == null) {
            JOptionPane.showMessageDialog(null, "Goodbye!", "ReservationClient",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        portString = JOptionPane.showInputDialog(null,
                "What is the port you'd like to connect to?:",
                "ReservationClient", JOptionPane.QUESTION_MESSAGE);
        if (portString == null) {
            JOptionPane.showMessageDialog(null, "Goodbye!", "ReservationClient",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        while (!isValid(portString)) {
            JOptionPane.showMessageDialog(null, "Error: The specified port is invalid!",
                    "ReservationClient", JOptionPane.ERROR_MESSAGE);

            portString = JOptionPane.showInputDialog(null,
                    "Enter the port of the server to connect to:",
                    "ReservationClient", JOptionPane.QUESTION_MESSAGE);

            if (portString == null) {
                JOptionPane.showMessageDialog(null, "Goodbye!", "ReservationClient",
                        JOptionPane.INFORMATION_MESSAGE);

                return;
            }
        }

        port = Integer.parseInt(portString);
        clientSocket = new Socket(hostName, port);


        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ois = new ObjectInputStream(clientSocket.getInputStream());

        dos = new DataOutputStream(clientSocket.getOutputStream());
        dis = new DataInputStream(clientSocket.getInputStream());

        // replace with gui method for basic page... !!!!!! @Celia

        Airline.getPassengerList();
        hg.setWelcomeFrame();

        System.out.println("finished welcome");

        while (true) {
            System.out.println("START UTF");
            System.out.println(hg.getAirline());
            System.out.println(hg.isBookTicket());
            if (hg.getAirline() != null && hg.isBookTicket()) {
                response = hg.getAirline();
                System.out.println(response);
                dos.writeUTF(response);
                System.out.println("client has sent flight type" + hg.getAirline());
                dos.flush();
                break;
            }
        }

        System.out.println("finished passing welcome sign");


        if (hg.isBookTicket()) {
            hg.setInfoFrame();
        }

        System.out.println("passenger info frame spawned");

        while (true) {
            try {
                p = hg.getPassenger();
                if (p.getfName() != null && p.getlName() != null && p.getAge() != null) {
                    System.out.println(p.getfName() + "is being passed from the client");
                    oos.writeObject(p);
                    oos.flush();
                    break;
                }
            } catch (NullPointerException e) {

            }
        }

        oos.close();
        ois.close();
        dos.close();
        dis.close();


//        booked = (String) ois.readObject();
//
//
//        System.out.println("You successfully booked a ticket.");

    }

}


// to do: add text box of all passengers
// handle all file/io stuff carefully in Airline - reading and writing all passengers. - not really clear in
// instructions please clarify