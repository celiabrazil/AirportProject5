import java.io.IOException;

/**
 * This class creates a listener.
 *
 * @author Simran Kadadi and Celia Brazil
 * @version 1.0 11/22/19
 */

public final class ResponseListener {

    public static void main(String[] args) throws InterruptedException, IOException {
        ReservationServer server;
        try {
            server = new ReservationServer();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.serveClients();
    }
}