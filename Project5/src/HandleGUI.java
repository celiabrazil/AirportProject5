
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Objects;


//make everything bigger

/**
 * This class contains all of the GUI methods that are called in other classes.
 *
 * @author Celia Brazil and Simran Kadadi
 * @version 1.0, 11/20/19
 */
public class HandleGUI {
    private static final String TITLE = "Purdue University Flight Reservation System";

    private String airlineN;

    private static BorderLayout border = new BorderLayout();
    private static GridLayout grid = new GridLayout();

    private final JFrame welcomeFrame = new JFrame(TITLE);
    private final JFrame bookFrame = new JFrame(TITLE);
    private JFrame chooseFrame = new JFrame(TITLE);
    private JFrame deltaFrame = new JFrame(TITLE);
    private JFrame southwestFrame = new JFrame(TITLE);
    private JFrame alaskaFrame = new JFrame(TITLE);
    private JFrame sureFrame = new JFrame(TITLE);
    private JFrame infoFrame = new JFrame(TITLE);
    private JFrame finalFrame = new JFrame(TITLE);

    private static Font f = new Font("serif", Font.PLAIN, 12);
    private static Font biggerFont = f.deriveFont(75f);

    /**
     * true if the user successfully welcomed
     */
    boolean welcomed = false;
    /**
     * true if the user wants to book a flight
     */
    boolean bookTicket = false;
    /**
     * true if the user wants to book a delta flight
     */
    boolean deltaFlight = false;
    /**
     * true if the user wants to book a southwest flight
     */
    boolean southwestFlight = false;
    /**
     * true if the user wants to book a alaska flight
     */
    boolean alaskaFlight = false;

    Passenger p = null;
    String lName;
    String fName;
    String age;

    public String getAirline() {
        return this.airlineN;
    }

    public boolean isBookTicket() {
        return this.bookTicket;
    }

    public String getAge() {
        return this.age;
    }

    public String getlName() {
        return this.lName;
    }

    public String getfName() {
        return this.fName;
    }

    /**
     * This method sets the booleans that determine which airline the user is wanting.
     *
     * @param airline The airline the user picked
     */
    public void setAirlineBooleans(String airline) {
        this.airlineN = airline;
        if (airline.equals("Delta")) {
            deltaFlight = true;
        } else if (airline.equals("Southwest")) {
            southwestFlight = true;
        } else {
            alaskaFlight = true;
        }
    }


    /**
     * @param comp           The component from which to obtain the contentPane of. Normally, this will be a
     *                       JFrame invoked with a getContentPane() method.
     * @param keyCode        The KeyEvent integer value that represents the key this action should be tied to.
     * @param id             The specific name of this Action
     * @param actionListener The Action to trigger when this input is detected.
     */
    private static void setKeyBindings(Container comp, int keyCode, String id, ActionListener actionListener) {
        JPanel jp = (JPanel) comp;
        InputMap inMap = jp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap aMap = jp.getActionMap();
        KeyStroke ks = KeyStroke.getKeyStroke(keyCode, 0, true);
        inMap.put(ks, id);
        AbstractAction abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        };
        aMap.put(id, abstractAction);

    }

    private static void resetKeyBindings(KeyStroke ks, Container comp) {
        JPanel cp = ((JPanel) comp);
        ActionMap aMap = cp.getActionMap();
        InputMap inMap = cp.getInputMap();
        inMap.put(ks, null);
        aMap.put(ks, null);
    }


    /**
     * sets the GUI for the first welcome frame
     */
    public void setWelcomeFrame() {
        welcomeFrame.setSize(700, 500);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null);


        JLabel welcomeLabel = new JLabel("Welcome to the Purdue University Airline Reservation Management " +
                "System!");
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 18));
        ImageIcon purduePicture = new ImageIcon("purdueP.jpg");


        Image img = purduePicture.getImage();
        Image resizedImage = img.getScaledInstance(700, 500, java.awt.Image.SCALE_SMOOTH);
        purduePicture = new ImageIcon(resizedImage);

        JLabel purdueP = new JLabel(purduePicture, JLabel.CENTER);
        purdueP.setPreferredSize(new Dimension(700, 500));

        JButton bookAFlightButton = new JButton("Book a Flight");
        bookAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //welcomed = true;
                welcomeFrame.dispose();
                setBookFrame();
            }
        });

        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.dispose();
                return;
            }
        });

        JPanel main = new JPanel();
        JPanel panelWelcome = new JPanel();
        JPanel buttonPanel = new JPanel();

        main.setLayout(border);

        panelWelcome.add(purdueP);

        buttonPanel.add(exitButton);
        buttonPanel.add(bookAFlightButton);

        main.add(welcomeLabel, BorderLayout.NORTH);
        main.add(panelWelcome, BorderLayout.CENTER);
        main.add(buttonPanel, BorderLayout.SOUTH);

        welcomeFrame.add(main);
        welcomeFrame.pack();
        welcomeFrame.setVisible(true);
    }

    /**
     * sets the GUI for the second frame to make sure tha the user wants to book a flight
     */
    public void setBookFrame() {
        bookFrame.setSize(700, 500);
        bookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookFrame.setLocationRelativeTo(null);


        JPanel bookPanel = new JPanel();
        JLabel bookLabel = new JLabel("Do you want to book a flight today?");

        JButton yesBookButton = new JButton("Yes, I want to book a flight.");
        yesBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookFrame.dispose();
                setChooseFrame();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookFrame.dispose();
                return;
            }
        });

        BorderLayout border = new BorderLayout(10, 10);
        bookFrame.setLayout(border);
        bookPanel.add(bookLabel, BorderLayout.PAGE_START);
        bookPanel.add(exitButton, BorderLayout.PAGE_END);
        bookPanel.add(yesBookButton, BorderLayout.EAST);

        bookFrame.setVisible(true);
        bookFrame.add(bookPanel);
    }

    /**
     * sets the GUI for the third frame when the user picks the desired airline
     */
    public void setChooseFrame() {
        chooseFrame.setSize(700, 600);
        chooseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chooseFrame.setLocationRelativeTo(null);
        chooseFrame.setLayout(border);
        chooseFrame.setVisible(true);

        JPanel choosePanel = new JPanel();
        JLabel chooseText = new JLabel("Choose a flight from the drop down menu.");

        JComboBox<String> comboBox = new JComboBox();
        for (Airline a : ReservationServer.getFlights()) {
            if (a.isFull() == false) {
                System.out.println(a.getAirlineName());
                comboBox.addItem(a.getAirlineName());
            }
        }

        JLabel airlineText = new JLabel();
        Dimension d = new Dimension(650, 200);
        airlineText.setPreferredSize(d);
        String deltaText = "<html>Delta Airlines is proud to be on of the five premier Airlines at Purdue University.\n" +
                "We are extremely offer exceptional services, with free limited WiFi for all customers.\n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.\n" +
                "We are also happy to offer power outlets in each seat for passenger use.\n" +
                "We hope you choose to fly Delta as your next Airline.</html>";
        String southwestText = "<html>Southwest Airlines is proud to offer flights to Purdue University.\n" +
                "We are happy to offer free in flight wifi, as well as our amazing snacks.\n" +
                "In addition, we offer flights for much cheaper than other airlines and offer two " +
                "free checked bags." +
                "We hope you choose Southwest for your next flight.</html>";
        String alaskaText = "<html>Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from " +
                "Purdue University." +
                "We primarily fly westward, and often have stops in Alaska and California.\n" +
                "We have first class amenities, even in coach class.\n" +
                "We provide fun snacks, such as pretzels and goldfish.\n" +
                "We also have comfortable seats and free WiFi.\n" +
                "We hope you choose Alaska Airlines for your next itinerary!</html>";
        airlineText.setText(deltaText);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Objects.requireNonNull(airlineN = (String) comboBox.getSelectedItem());
                switch (airlineN) {
                    case "Delta":
                        airlineText.setText(deltaText);
                        break;
                    case "Southwest":
                        airlineText.setText(southwestText);
                        break;
                    case "Alaska":
                        airlineText.setText(alaskaText);
                        break;
                }
            }
        });

        JButton chooseFlightButton = new JButton("Choose this flight");
        chooseFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFrame.dispose();
                airlineN = (String) comboBox.getSelectedItem();
                assert airlineN != null;
                setAirlineBooleans(airlineN);
                setSureFrame();
            }
        });

        ActionListener keyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed");
                if (Objects.requireNonNull(comboBox.getSelectedItem()).equals("Delta")) {
                    Delta d = (Delta) ReservationServer.getFlights().get(0);
                    setDeltaFrame(d);
                } else if (comboBox.getSelectedItem().equals("Southwest")) {
                    Southwest s = (Southwest) ReservationServer.getFlights().get(1);
                    setSouthwestFrame(s);
                } else {
                    Alaska a = (Alaska) ReservationServer.getFlights().get(2);
                    setAlaskaFrame(a);
                }
            }
        };

        setKeyBindings(chooseFrame.getContentPane(), KeyEvent.VK_BACK_SLASH, "openAirlineFrame", keyListener);
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        resetKeyBindings(escape, chooseFrame.getContentPane());


        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFrame.dispose();
                return;
            }
        });

        choosePanel.add(chooseText);
        choosePanel.add(comboBox);
        choosePanel.add(airlineText);
        choosePanel.add(exitButton);
        choosePanel.add(chooseFlightButton);
        chooseFrame.add(choosePanel);
    }

    /**
     * sets the frame that shows the flight information of the delta flight
     *
     * @param d the Delta Object
     */
    public void setDeltaFrame(Delta d) {
        deltaFrame.setVisible(true);
        deltaFrame.setSize(500, 500);
        deltaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deltaFrame.setLayout(border);

        JPanel deltaPanel = new JPanel(new BorderLayout());
        Delta.setNumOfReservations(d.getPassengers().size());
        JLabel numReservations = new JLabel("Delta Airlines. " + d.getNumOfReservations() + " : " + d.getTotalReservations());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        //scrollPane.add
        ArrayList<Passenger> passengers = ReservationServer.getFlights().get(0).getPassengers();

        JPanel passengersPanel = new JPanel(new GridLayout(d.getTotalReservations(), 1));

        for (Passenger p : passengers) {
            JLabel reservations = new JLabel("");
            StringBuilder sb = new StringBuilder();
            sb.append(p.getfName()).append(". ").append(p.getlName()).append(", ").append(p.getAge()).append("\n");
            reservations.setText(sb.toString());
            passengersPanel.add(reservations);
        }

        scrollPane.getViewport().add(passengersPanel);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaFrame.dispose();
            }
        });


        deltaPanel.add(numReservations, BorderLayout.NORTH);
        deltaPanel.add(scrollPane, BorderLayout.CENTER);
        deltaPanel.add(exitButton, BorderLayout.PAGE_END);
        this.deltaFrame.add(deltaPanel);
    }

    /**
     * sets the frame that shows the flight information of the southwest flight
     *
     * @param d the Southwest Object
     */
    public void setSouthwestFrame(Southwest d) {
        southwestFrame.setVisible(true);
        southwestFrame.setSize(500, 500);
        southwestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        southwestFrame.setLayout(border);

        JPanel southwestPanel = new JPanel(new BorderLayout());
        Southwest.setNumOfReservations(d.getPassengers().size());
        JLabel numReservations = new JLabel("Southwest Airlines. " + d.getNumOfReservations() + " : " + d.getTotalReservations());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        //scrollPane.add
        ArrayList<Passenger> passengers = ReservationServer.getFlights().get(1).getPassengers();

        JPanel passengersPanel = new JPanel(new GridLayout(d.getTotalReservations(), 1));

        for (Passenger p : passengers) {
            JLabel reservations = new JLabel("");
            StringBuilder sb = new StringBuilder();
            sb.append(p.getfName()).append(". ").append(p.getlName()).append(", ").append(p.getAge()).append("\n");
            reservations.setText(sb.toString());
            passengersPanel.add(reservations);
        }

        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                southwestFrame.dispose();
            }
        });

        scrollPane.getViewport().add(passengersPanel);


        southwestPanel.add(numReservations, BorderLayout.NORTH);
        southwestPanel.add(scrollPane, BorderLayout.CENTER);
        southwestPanel.add(exitButton, BorderLayout.PAGE_END);
        this.southwestFrame.add(southwestPanel);
    }

    /**
     * sets the frame that shows the flight information of the alaska flight
     *
     * @param d the Alaska Object
     */
    public void setAlaskaFrame(Alaska d) {
        alaskaFrame.setVisible(true);
        alaskaFrame.setSize(500, 500);
        alaskaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alaskaFrame.setLayout(border);

        JPanel alaskaPanel = new JPanel(new BorderLayout());
        Southwest.setNumOfReservations(d.getPassengers().size());
        JLabel numReservations = new JLabel("Alaska Airlines. " + d.getNumOfReservations() + " : " + d.getTotalReservations());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        //scrollPane.add
        ArrayList<Passenger> passengers = ReservationServer.getFlights().get(2).getPassengers();
        JPanel passengersPanel = new JPanel(new GridLayout(d.getTotalReservations(), 1));

        for (Passenger p : passengers) {
            JLabel reservations = new JLabel("");
            StringBuilder sb = new StringBuilder();
            sb.append(p.getfName()).append(". ").append(p.getlName()).append(", ").append(p.getAge()).append("\n");
            reservations.setText(sb.toString());
            passengersPanel.add(reservations);
        }

        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alaskaFrame.dispose();
            }
        });

        scrollPane.getViewport().add(passengersPanel);

        alaskaPanel.add(numReservations, BorderLayout.NORTH);
        alaskaPanel.add(scrollPane, BorderLayout.CENTER);
        alaskaPanel.add(exitButton, BorderLayout.PAGE_END);
        this.alaskaFrame.add(alaskaPanel);
    }

    /**
     * sets the fourth frame when the user confirms their desired flight
     */
    public void setSureFrame() {
        sureFrame.setVisible(true);
        sureFrame.setSize(700, 600);
        sureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sureFrame.setLocationRelativeTo(null);
        sureFrame.setLayout(border);

        JPanel surePanel = new JPanel();
        JLabel sureQuestion = new JLabel("Are you sure that you want to book a flight on " + airlineN + "?");

        JButton noButton = new JButton("No I want a different flight.");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChooseFrame();
            }
        });
        JButton yesButton = new JButton("Yes, I want this flight.");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I'm trying to book the ticket");
                setBookTicket(true);
                System.out.println(isBookTicket() + "is what happned.");
                System.out.println("I have attempted to change bookStatus");
                sureFrame.dispose();
                //setInfoFrame();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sureFrame.dispose();
                return;
            }
        });

        surePanel.add(sureQuestion);
        surePanel.add(exitButton);
        surePanel.add(noButton);
        surePanel.add(yesButton);
        sureFrame.add(surePanel);
    }

    /**
     * sets the fifth frame that the user will input their information into
     */
    public void setInfoFrame() {

        infoFrame.setLayout(new BorderLayout());
        infoFrame.setVisible(true);
        infoFrame.setSize(700, 600);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel(new GridLayout(6, 1));
        JPanel buttons = new JPanel();

        JLabel pleaseLabel = new JLabel("<html><b><font size=5 face = Times>Please input your information below." +
                "</center></font size></b></html>");

        JLabel firstNameLabel = new JLabel("What is your first name?");
        JTextArea firstName = new JTextArea(5, 5);
        JScrollPane fScrollPane = new JScrollPane(firstName);

        JLabel lastNameLabel = new JLabel("What is your last name?");
        JTextArea lastName = new JTextArea(5, 30);
        JScrollPane lScrollPane = new JScrollPane(lastName);

        JLabel ageLabel = new JLabel("What is your age?");
        JTextArea age = new JTextArea(5, 30);
        JScrollPane ageScrollPane = new JScrollPane(age);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setfName(firstName.getText());
                setlName(lastName.getText());
                setAge(age.getText());
                p = new Passenger(firstName.getText(), lastName.getText(), age.getText());

                infoFrame.dispose();
                int answer = JOptionPane.showConfirmDialog(null,
                        "Are all of the details correct?\n" +
                                "The passenger's name is " + p.getfName() + " " + p.getlName() + " and their age is " +
                                p.getAge() + ".\n" +
                                "If all of the information shown is correct, select the Yes button" +
                                " below, otherwise, select the No button.", "Confirm Info", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    if (deltaFlight) {
                        setFinalFrame(ReservationServer.getFlights().get(0));
                    } else if (southwestFlight) {
                        setFinalFrame(ReservationServer.getFlights().get(1));
                    } else {
                        setFinalFrame(ReservationServer.getFlights().get(2));
                    }
                } else {
                    //needs to close?
                    //setInfoFrame();
                }
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose();
                return;
            }
        });

        buttons.add(exitButton);
        buttons.add(nextButton);

        infoPanel.add(firstNameLabel);
        infoPanel.add(fScrollPane);
        infoPanel.add(lastNameLabel);
        infoPanel.add(lScrollPane);
        infoPanel.add(ageLabel);
        infoPanel.add(ageScrollPane);
        infoFrame.add(pleaseLabel, BorderLayout.NORTH);
        infoFrame.add(infoPanel, BorderLayout.CENTER);
        infoFrame.add(buttons, BorderLayout.SOUTH);
    }

    /**
     * sets the last frame with the updated flight information
     *
     * @param airline the airline the user has chosen
     */
    public void setFinalFrame(Airline airline) {
        finalFrame.setVisible(true);
        finalFrame.setSize(700, 600);
        finalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finalFrame.setLocationRelativeTo(null);

        JPanel finalPanel = new JPanel();
        JLabel titleLabel = new JLabel("Flight data displaying for " + airline.getAirlineName() + " Airlines." +
                "\n Enjoy your flight!\n Flight is now boarding a Gate " + airline.getG());

        ArrayList<Passenger> passengers;
        JPanel passengersPanel;
        if (this.deltaFlight) {
            passengers = ReservationServer.getFlights().get(0).getPassengers();
            passengersPanel = new JPanel(new GridLayout(airline.getTotalReservations(), 1));
        } else if (this.southwestFlight) {
            passengers = ReservationServer.getFlights().get(1).getPassengers();
            passengersPanel = new JPanel(new GridLayout(airline.getTotalReservations(), 1));
        } else {
            passengers = ReservationServer.getFlights().get(2).getPassengers();
            passengersPanel = new JPanel(new GridLayout(airline.getTotalReservations(), 1));
        }

        for (Passenger p : passengers) {
            JLabel reservations = new JLabel("");
            StringBuilder sb = new StringBuilder();
            sb.append(p.getfName()).append(". ").append(p.getlName()).append(", ").append(p.getAge()).append("\n");
            reservations.setText(sb.toString());
            passengersPanel.add(reservations);
        }

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the " +
                                "Purdue University Airline Management System!", "Thank you!",
                        JOptionPane.INFORMATION_MESSAGE);
                finalFrame.dispose();
                return;
            }
        });

        JButton refresh = new JButton("Refresh Flight Status");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Airline.getPassengerList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                setFinalFrame(airline);
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().add(passengersPanel, BorderLayout.CENTER);

        BoardingPass bp = new BoardingPass(airline, p);
        JPanel scroll = new JPanel();
        scroll.setPreferredSize(new Dimension(250, 250));
        scroll.add(scrollPane);
        BoxLayout boxLayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
        //System.out.println(bp.toString());
        JLabel boardingPass = new JLabel(bp.toString());
        JPanel buttons = new JPanel();
        buttons.add(exit);
        buttons.add(refresh);

        finalPanel.add(titleLabel);
        finalPanel.add(scroll);
        finalPanel.add(boardingPass);
        finalPanel.add(buttons);
        finalPanel.setLayout(boxLayout);
        finalFrame.add(finalPanel);
    }


    //public static void main(String[] args) {
    //setWelcomeFrame();
    //}


    public void setBookTicket(boolean bookTicket) {
        this.bookTicket = bookTicket;
    }

    public Passenger getPassenger() {
        return p;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAge(String age) {
        this.age = age;
    }
}