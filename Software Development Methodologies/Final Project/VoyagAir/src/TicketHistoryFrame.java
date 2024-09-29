import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class TicketHistoryFrame extends JFrame implements ActionListener {

    private JButton refreshBtn, newticketBtn, exitBtn;
    private JTextArea listArea,statisticArea;

    //--Δημιουργούμε ένα string array  ώστε να ξεχωρίζει ο χρήστης οταν εκτυπωθεί το εισητηρίο τι του εμφανίζει.
    private String[] preview = {" Ticket ID" + "\t" + "Issue Date" + "\t" + "Name" + "\t"
            + "Itinerary" + "\t " + "Price" + "\t" + "Date" + "\t" + "Passengers" + "\t"
            + "Luggage" + "\t" + "Travel Class "+"\n" } ;


    private JLabel minTicketId,maxTicketId;
    private JLabel sumTLabel, sumPLabel, minPLabel, maxPLabel;

    private String fileName= "ticketlist.txt";
    private ArrayList<Ticket> ticketList;

    private int sumTickets;
    private double sumP =0;
    private Ticket minPrice = new Ticket(); // Αρχικοποιήση από τον constructor  Ticket() της κλάσης Ticket. Το κάνουμε αυτό γιατί όταν δεν υπάρχει κάποιο ticket οι μεταβλητές minPrice και maxPrice θα είναι null.
    private Ticket maxPrice = new Ticket();
    private String sumPrice;


    public  TicketHistoryFrame(){
        ticketList = new ArrayList<>();
    }

    //------- Starting point of preparing the ui for TicketHistoryFrame -------

    public void ticketHistoryFrame(){
        // Η διαδικασία που ακολοθούμε για την σχεδίαση του παραθύρου είναι η ίδια με όλα τα windows.
        this.setSize(1150,500);
        this.setTitle("Ticket History");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false); // Αφαιρούμε από τον χρήστη την δυνατότητα να κάνει resize το window

        System.setProperty("bgrd","0XD0E2F4");
        this.getContentPane().setBackground(Color.getColor("bgrd"));

        Image icon = Toolkit.getDefaultToolkit().getImage("VoyagAir.jpg");
        this.setIconImage(icon);

        prepareUiFrame();

        this.setVisible(true);
    }

    public void prepareUiFrame(){

        // Δημιουργούμε τα text Areas.
        //---start of text areas----
        System.setProperty("areaColor","0XD9E8F6");
            //---start of text listArea----
            listArea = new JTextArea();

                listArea.setBounds(10,30, 830,420);
                listArea.setEditable(false);
                listArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ticket History List"));
                listArea.setBackground(Color.getColor("areaColor"));


                // Εκχωρούμε στο list area το string preview
                listArea.append(Arrays.toString(preview));

                add(listArea);

                // Να κάνει load το αρχείο στο text area.
                loadFromFile();
            //---end of text listArea------

            //---start of statisticArea -----
                statisticArea = new JTextArea();
                statisticArea.setBounds(870,30, 250,230);
                statisticArea.setEditable(false);
                statisticArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Statistic's of Tickets"));
                statisticArea.setBackground(Color.getColor("areaColor"));
                add(statisticArea);

            //---end of statisticArea----

        //---end of text areas----

        //---start of labels----

            //---start of sum Tickets Label------------
            sumTLabel = new JLabel("Number of Tickets: "+sumTickets);
            sumTLabel.setForeground(Color.BLACK);
            sumTLabel.setFont(new Font("Serif",Font.PLAIN,15));
            sumTLabel.setBounds(10,20,200,20);
            statisticArea.add(sumTLabel);
            //---end of sum Tickets Label------------

            //---start of sum Price Label--------
            sumPrice = (String.format("%.02f",sumP)).replaceAll(",",".");;
            sumPLabel = new JLabel("Prices in Total: " + sumPrice +" €");
            sumPLabel.setForeground(Color.BLACK);
            sumPLabel.setFont(new Font("Serif",Font.PLAIN,15));
            sumPLabel.setBounds(10,50,200,20);
            statisticArea.add(sumPLabel);

            //---end of sum Price Label-------

            //---start of min Price Label -------
            minPLabel= new JLabel("Minimum Price: " +minPrice.getPrice()+" €");
            minPLabel.setForeground(Color.BLACK);
            minPLabel.setFont(new Font("Serif",Font.PLAIN,15));
            minPLabel.setBounds(10,90,300,20);
            statisticArea.add(minPLabel);
            //---end of min Price Label--------

            //---start of min Price Ticket ID -------
            minTicketId= new JLabel("Minimum Price Ticket ID: " +minPrice.getTicketID());
            minTicketId.setForeground(Color.BLACK);
            minTicketId.setFont(new Font("Serif",Font.PLAIN,15));
            minTicketId.setBounds(10,120,300,20);
            statisticArea.add(minTicketId);
            //---end of min Price Ticket ID--------



            //---start of max Price Label--------
            maxPLabel= new JLabel("Maximum Price: "+maxPrice.getPrice()+" €");
            maxPLabel.setForeground(Color.BLACK);
            maxPLabel.setFont(new Font("Serif",Font.PLAIN,15));
            maxPLabel.setBounds(10,160,300,20);
            statisticArea.add(maxPLabel);
            //---end of max Price Label-----------

            //---start of max Price Ticket ID -------
            maxTicketId= new JLabel("Maximum Price Ticket ID: " +maxPrice.getTicketID());
            maxTicketId.setForeground(Color.BLACK);
            maxTicketId.setFont(new Font("Serif",Font.PLAIN,15));
            maxTicketId.setBounds(10,190,300,20);
            statisticArea.add(maxTicketId);
            //---end of max Price Ticket ID--------

        //---end of labels------


        //---start of buttons----
        System.setProperty("color","0XE1EDF8");
            //---start of refresh----
            refreshBtn = new JButton("Refresh");
            refreshBtn.setBackground(Color.getColor("color"));
            refreshBtn.setBounds(840,419,80,30);
            add(refreshBtn);

            // Action Listener για το κουμπι refresh οπου θα κάνει reload το file.
            refreshBtn.addActionListener(this);
            //---end of refresh----

            //---start of new ticket----
            newticketBtn = new JButton("New Ticket");
            newticketBtn.setBackground(Color.getColor("color"));
            newticketBtn.setBounds(930,419,100,30);
            add(newticketBtn);

            newticketBtn.addActionListener(e -> {
                CreateTicketFrame ticket = new CreateTicketFrame();
                ticket.ticketFrame();
            });
            //---end of new ticket----

            //---start of exit-----
            exitBtn = new JButton("Exit");
            exitBtn.setBackground(Color.getColor("color"));
            exitBtn.setBounds(1040,419,80,30);
            add(exitBtn);

            exitBtn.addActionListener(e -> { this.dispose(); });

            //---start of exit-----

        //---end of buttons----

    }

    //------- Starting point of preparing the ui for TicketHistoryFrame -------



    //------- Starting point of functionality for the elements-------

    public void loadFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            String[] token;
            Ticket ticket;
            sumTickets = 0; //sum variable που υπολογίζει το πλήθος εισητηρίων και κάνει reset κάθε φορά που κάνει.

            //θα συνεχίζει να διαβάζει από το αρχείο μέχρι να τελειώσουν τα εισητήρια

                while (reader.ready()) {
                    line = reader.readLine(); // Ξεκινά την διαδικασία read.
                    sumTickets++;

                    token = line.split("\t"); // Χωρίζει τα στοιχεία με tab
                    if (token.length == 9) {
                        ticket = new Ticket(token[0], token[1], token[2], token[3], token[4], token[5], token[6], token[7], token[8]);
                        ticketList.add(ticket); //προσθέτουμε τα στοιχεία του ticket στο ticketList.
                    }

                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!ticketList.isEmpty()) {
            System.out.println("Before max and min");
            Collections.sort(ticketList); // Κάνουμε ταξινομηση στο ticketList απο το μικρότερο στο μεγαλύτερο. Χρησιμοποιουμε την compareTo που βρίσκεται στην κλάση Ticket.
            maxPrice = ticketList.get(ticketList.size() - 1); // Πάει στο τελευταίο σημείο του πίνακα και βρίσκει το μέγιστο άφου ο πίνακας είναι ταξινομημένος κατα αύξουσα σειρά.
            minPrice = ticketList.get(0); // Εφόσον κάναμε αύξουσα ταξινόμηση άρα η ελάχιστη τιμή θα είναι το πρώτο εισητήριο.
            System.out.println();
        }else {
            JOptionPane.showMessageDialog(
                    TicketHistoryFrame.this,
                    "Ticket List is empty.",
                    "Empty error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Χρησιμοποιούμε for each για να υπολογίσουμε το sum Price.
        for (Ticket temp : ticketList) {
            sumP += Double.parseDouble(temp.getPrice());
        }

        // Εμφανίζουμε το ticketList.

            for (Ticket obj : ticketList) {
                String data = obj.toString();
                listArea.append(data + "\n");
            }

    }

    public void loadStats(){
        sumTLabel.setText("Number of Tickets: " +sumTickets);

        sumPrice = (String.format("%.02f",sumP)).replaceAll(",",".");;
        sumPLabel.setText("Prices in Total: " + sumPrice +" €");

        minPLabel.setText("Minimum Price: " +minPrice.getPrice()+" €" );
        minTicketId.setText("Minimum Price Ticket ID: " +minPrice.getTicketID());

        maxPLabel.setText("Maximum Price: "+maxPrice.getPrice()+" €" );
        maxTicketId.setText("Maximum Price Ticket ID: " +maxPrice.getTicketID());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==refreshBtn){
            // Κάνουμε reset το list area και το ticket list και τα ξανα εμφανίζουμε
            listArea.setText("");
            ticketList.clear();
            listArea.append(Arrays.toString(preview));

            loadFromFile();
            loadStats();

        }
    }

    //------- End point of functionality for the elements-------


}
