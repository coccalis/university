import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainFrame extends JFrame {

    private JLabel l1,l2,l3;

    private JButton ticket_btn, ticketHistoryBtn,about_btn,exit_btn;

    private JMenuBar menuBar;

    private JMenu ticketMenu, helpMenu;

    private JMenuItem ticketItem, ticketHistoryItem, aboutItem, exitItem;

    public MainFrame(){
    }

    public void mainWindow(){

        //--- start of prepare UI -----
        this.setTitle("VoyagAir");
        this.setSize(800,400);

        System.setProperty("bgrd","0XD0E2F4");// Στις γραμμές 23 και 24 χρησιμοποιούμε την εντολή System.setProperty για να χρησιμοποιήσουμε ένα χρώμα που δεν υπάρχει στα default χρώματα της java.
        this.getContentPane().setBackground(Color.getColor("bgrd"));

        this.setResizable(false); // Αφαιρούμε από τον χρήστη την δυνατότητα να κάνει resize το window
        this.setLocationRelativeTo(null);// Με την χρήση της εντολής this..setLocationRelativeTo(null); τοποθετούμε το παράθυρο στην μέση της οθόνης κάθε φορά που γίνεται εκκίνηση του παραθύρου.
        this.setLayout(null); // turn off flowlayout
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Βάζουμε ένα icon στο window για εμφανισιακούς λόγους.
        Image icon = Toolkit.getDefaultToolkit().getImage("VoyagAir.jpg");
        this.setIconImage(icon);// το προσθέτουμε στο frame.

        // Τοποθετούμε είκονα αεροπλάνου για καθαρά εμφανισιακούς λόγους.
        // Δημιουργούμε ένα image icon. Έπειτα δημιουργούμε και ένα label όπου θα εκχωρήσουμε σε αυτό το imageIcon που μόλις δημιουργήσαμε και να εμφανίσουμε την είκονα.
        ImageIcon airplane = new ImageIcon("airplane.png");
        JLabel airp = new JLabel();
        airp.setIcon(airplane);// το προσθέτουμε στο label.
        airp.setBounds(100,10,500,580); // Ορίζουμε τις διαστάσεις.
        this.add(airp);// το προσθέτουμε στο frame.

        //καλούμε τις μέθοδους που περιέχουν τα labels, τα buttons και το menu bar.
        prepareTexts();
        prepareButtons();
        this.setVisible(true);
        //--- start of prepare UI -----
    }

    public void prepareTexts(){

        //---start of labels -----
            //--start of l1------
            l1 = new JLabel("Welcome to"); // Δημιουργούμε το welcome label.
            l1.setForeground(Color.BLACK); // Θέτουμε το χρώμα του label.
            l1.setBounds(339,10,100,30); // Ορίζουμε τις διαστάσεις.
            l1.setFont(new Font("Serif",Font.PLAIN,20)); // Ορίζουμε το font style του label
            add(l1);
            //--end of l1-----

            //--start of l2----
            l2 = new JLabel("VoyagAir");
            l2.setForeground(Color.BLACK);// Θέτουμε το χρώμα του label.
            l2.setBounds(330,40,200,50);// Ορίζουμε τις διαστάσεις.
            l2.setFont(new Font("Serif",Font.PLAIN,30));// Ορίζουμε το font style του label
            add(l2);
            //--end of l2-----

            //--start of l3----
            l3 = new JLabel("Please choose one of the following options.");
            l3.setForeground(Color.BLACK);// Θέτουμε το χρώμα του label.
            l3.setBounds(275,100,300,50);// Ορίζουμε τις διαστάσεις.
            l3.setFont(new Font("Serif",Font.PLAIN,15));// Ορίζουμε το font style του label
            add(l3);
            //--end of l3----
        //--end of labels----
    }

    public void prepareButtons(){
        //--start of buttons---
        System.setProperty("color","0XE1EDF8");

            //--start of ticket_btn---
            ticket_btn = new JButton("New Ticket"); // Δημιουργούμε το κουμπί New Ticket.
            ticket_btn.setBackground(Color.getColor("color"));
            ticket_btn.setBounds(210,160,100,30);
            add(ticket_btn);

            // ActionListener για το κουμπί ticket_btn ώστε ο χρήστης όταν πατάει το κουμπί να τον οδηγεί στο window "Book New Ticket".
            // Για να επιτευχθεί αυτός ο σκοπός δημιουργούμε ένα νέο αντικείμενο από την κλάση CreateTicketFrame και καλείται η μέθοδος ticketFrame.
            ticket_btn.addActionListener(e -> {
                CreateTicketFrame ticket = new CreateTicketFrame();
                ticket.ticketFrame();
            });
            //--end of ticket_btn---

            //--start of hist_tic_btn---
            ticketHistoryBtn = new JButton("Booking Tickets History"); // Δημιουργούμε το κουμπί Booking Tickets History.
            ticketHistoryBtn.setBackground(Color.getColor("color"));
            ticketHistoryBtn.setBounds(315,160,170,30);
            add(ticketHistoryBtn);

            // Ακολουθούμε την ίδια διαδικάσια με το κουμπι ticket_btn με την διαφορά πως θα οδηγεί τον χρήστη το παράθυρο Ticket History από την κλάση TicketHistoryFrame.
             ticketHistoryBtn.addActionListener(e -> {
                 TicketHistoryFrame ticketHist = new TicketHistoryFrame();
                    ticketHist.ticketHistoryFrame();
             });


            //--start of hist_tic_btn---

            //--start of about_btn---
            about_btn = new JButton("About"); // Δημιουργούμε το κουμπί About.
            about_btn.setBackground(Color.getColor("color"));
            about_btn.setBounds(490,160,80,30);
            add(about_btn);

            // Ακολουθούμε την ίδια διαδικάσια με το κουμπι about_btn με την διαφορά πως θα οδηγεί τον χρήστη το παράθυρο About από την κλάση AboutFrame.
            about_btn.addActionListener(e -> {
                    AboutFrame about = new AboutFrame();
                     about.aboutFrame();
                });
            //--end of about_btn---

            //--start of exit_btn---
            exit_btn = new JButton("Exit");
            exit_btn.setBackground(Color.getColor("color"));
            exit_btn.setBounds(690,300,80,30);
            add(exit_btn);

            //Με το πάτημα του button exit_btn καλείται η μέθοδος exitApp για τον τερματισμό του προγράμματος.
            exit_btn.addActionListener(e -> exitApp());

            this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
            //--end of exit_btn---

        //--end of buttons---

        //---start of Jmenu---
            //Δημιουργούμε το menu Bar.
            menuBar = new JMenuBar();

                // Δημιουργούμε τις δυο κατηγορίες του menu bar.
                ticketMenu = new JMenu("Menu");
                helpMenu = new JMenu("Help");

                    // Δημιουργούμε τις επιλογές που θα έχουν οι δυο κατηγορίες του menu.
                    ticketItem = new JMenuItem("New Ticket");
                    ticketHistoryItem =  new JMenuItem("View Ticket History");
                    exitItem = new JMenuItem("Exit");
                    aboutItem = new JMenuItem("About");

                //προσθέτουμε τις επιλογές στην κατηγορία Menu.
                ticketMenu.add(ticketItem);
                ticketMenu.add(ticketHistoryItem);
                ticketMenu.addSeparator();
                ticketMenu.add(exitItem);

                //προσθέτουμε τις επιλογές στην κατηγορία help.
                helpMenu.add(aboutItem);

                //Τέλος προσθέτουμε τις κατηγορίες στο menu bar.
                menuBar.add(ticketMenu);
                menuBar.add(helpMenu);

                this.setJMenuBar(menuBar);

                //Χρησιμοποιούμε τις ίδες εντολές για τις ίδιες λειτουργίες που έχουν και τα κουμπία.
                ticketItem.addActionListener(e -> {
                    CreateTicketFrame ticket = new CreateTicketFrame();
                    ticket.ticketFrame();
                });

                ticketHistoryItem.addActionListener(e -> {
                    TicketHistoryFrame ticketHist = new TicketHistoryFrame();
                    ticketHist.ticketHistoryFrame();
                });

                exitItem.addActionListener(e -> exitApp());

                aboutItem.addActionListener(e -> {
                    AboutFrame about = new AboutFrame();
                    about.aboutFrame();
                });
        //---end of Jmenu---


    }

    private void exitApp() {
        // Χρησιμοποιούμε το JOptionPane ώστε να εμφανιστεί ένα pop up window στο οποίο θα επιβεβαιώνει ο χρήστης αν θέλει να τερματίστει το πρόγραμμα ή όχι.
        int i = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit the app?");
        if (i == JOptionPane.YES_OPTION) { //Αν ο χρήστης πατήσει ναι τότε θα τερματιστεί το πρόγραμμα.
            System.exit(0);
        }
    }
}
