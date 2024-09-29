import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class CreateTicketFrame extends JFrame {

    private JLabel nameLabel, destinationLabel, dateLabel, passengersLabel, luggageLabel, clasLabel, titleLabel, browsingLabel;

    private JTextField destination, date ,name, passengers, luggage;

    private JComboBox travelClass;

    private JButton clearBtn, completeBtn, saveBtn;

    public JTextArea area;

    public static ArrayList<Ticket> ticketList;

    private String fileName= "ticketlist.txt";



    public  CreateTicketFrame(){
        ticketList = new ArrayList<>();
    }

//------- Starting point of preparing the ui for CreateTicketFrame -------

    public void ticketFrame(){

        // Η διαδικασία που ακολουθούμε για να προετοιμάσουμε το window είναι η ίδια που είδαμε με του main frame.
        this.setSize(780,420);
        this.setTitle("Book New Ticket");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false); // Αφαιρούμε από τον χρήστη την δυνατότητα να κάνει resize το window


        System.setProperty("bgrd","0XD0E2F4");
        this.getContentPane().setBackground(Color.getColor("bgrd"));

        Image icon = Toolkit.getDefaultToolkit().getImage("VoyagAir.jpg");
        this.setIconImage(icon);

        prepareTexts();
        prepareButtons();

        this.setVisible(true);
    }

    public void prepareButtons(){
        //---start of Buttons----
        System.setProperty("color","0XE1EDF8");

        //---start of clear button----
        clearBtn = new JButton("Clear");
        clearBtn.setBackground(Color.getColor("color"));
        clearBtn.setBounds(15,320,80,30);
        add(clearBtn);

        clearBtn.addActionListener(e -> clearInput());
        //---end of clear button-----

        //---start of complete button----
        completeBtn = new JButton("Complete");
        completeBtn.setBackground(Color.getColor("color"));
        completeBtn.setBounds(100,320,100,30);
        add(completeBtn);

        completeBtn.addActionListener(e -> startComplete());
        //---end of complete button-----

        //---start of save button----
        saveBtn = new JButton("Save");
        saveBtn.setBackground(Color.getColor("color"));
        saveBtn.setBounds(205,320,80,30);
        saveBtn.setEnabled(false);
        add(saveBtn);

        saveBtn.addActionListener(e -> saveTicketList());
        //---end of save button-----

        //---end of Buttons----
    }

    public void prepareTexts(){

        //---start of labels----

            //---start of nameLabel-----
            nameLabel = new JLabel("Name: ");
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setBounds(10,10,50,30);
            nameLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(nameLabel);
            //---end of nameLabel-----

            //---start of destinationLabel----
            destinationLabel = new JLabel("Destination:");
            destinationLabel.setForeground(Color.BLACK);
            destinationLabel.setBounds(10,42,800,30);
            destinationLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(destinationLabel);
            //---end of destinationLabel----

            //---start of dateLabel----
            dateLabel = new JLabel("Date:");
            dateLabel.setForeground(Color.BLACK);
            dateLabel.setBounds(10,75,40,30);
            dateLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(dateLabel);
            //---end of dateLabel----

            //---start of passengersLabel----
            passengersLabel = new JLabel("Passengers:");
            passengersLabel.setForeground(Color.BLACK);
            passengersLabel.setBounds(10,110,80,30);
            passengersLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(passengersLabel);
            //---end of passengersLabel----

            //---start of luggageLabel----
            luggageLabel = new JLabel("Luggage:");
            luggageLabel.setForeground(Color.BLACK);
            luggageLabel.setBounds(10,140,80,30);
            luggageLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(luggageLabel);
            //---end of luggageLabel----

            //---start of clasLabel----
            clasLabel = new JLabel("Class:");
            clasLabel.setForeground(Color.BLACK);
            clasLabel.setBounds(10,170,80,30);
            clasLabel.setFont(new Font("Serif",Font.PLAIN,15));
            add(clasLabel);
            //---end of clasLabel----

            //---start of titleLabel----
            titleLabel = new JLabel("Final Result:");
            titleLabel.setForeground(Color.BLACK);
            titleLabel.setBounds(330,5,100,30);
            titleLabel.setFont(new Font("Serif",Font.BOLD,15));
            add(titleLabel);
            //---end of titleLabel----

            //---start of browsing label----
            browsingLabel = new JLabel("(Please try to fill all the pre-required areas.)");
            browsingLabel.setForeground(Color.BLACK);
            browsingLabel.setFont(new Font("Serif",Font.BOLD,14));
            browsingLabel.setBounds(15,280,300,30);
            add(browsingLabel);
            //---end of browsing label-----

        //---end of labels-----


        //---start of textfield----

            //---start of name---
            name = new JTextField();
            name.setBounds(60,18,150,20);
            add(name);
            //---end of name----

            //---start of destination----
            destination = new JTextField();
            destination.setBounds(85,48,150,20);
            add(destination);
            //---end of destination----

            //---start of date----
            date = new JTextField();
            date.setBounds(60,80,100,20);
            add(date);
            //---end of date----

            //---start of passengers----
            passengers = new JTextField();
            passengers.setBounds(85,115,40,20);
            add(passengers);
            //---end of passengers----

            //---start of luggage----
            luggage = new JTextField();
            luggage.setBounds(85,145,40,20);
            add(luggage);
            //---end of luggage----

        //---end of textfield----



        //---start of combobox----

            //---start of travelClass----
            String option[] = {"(Select a class to travel)","First Class","Business Class","Economy Class"};
            travelClass = new JComboBox(option);
            travelClass.setBounds(50,175,180,20);
            add(travelClass);
            //---end of travelClass----

        //---end of combobox----

        //---start of text area----

            area = new JTextArea();
            area.setBounds(330,30, 400,320);
            area.setEditable(false);
            area.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
            System.setProperty("areaColor","0XD9E8F6");
            area.setBackground(Color.getColor("areaColor"));

            add(area);

        //---end of text area------

    }

//------- End point of preparing the ui for CreateTicketFrame -------

//------- Starting point of functionality for the elements-------

    // Η μέθοδος clearInput καλείται όταν ο χρήστης πατήσει το κουμπί "Clear" και κάνει reset τα textfields και το textarea.
    public void clearInput(){
        name.setText("");
        destination.setText("");
        date.setText("");
        passengers.setText("");
        luggage.setText("");
        travelClass.setSelectedIndex(0);
        area.setText("");

        // Αν ο χρήστης κάνει κάποιο λάθος και πατήσει το κουμπί clear τότε θα κάνουν reset τα κουμπία complete και save.
        completeBtn.setEnabled(true);
        saveBtn.setEnabled(false);
    }


    // Η μέθοδος startComplete καλείται όταν ο χρήστης πατήσει το κουμπί "Complete".
    // Στην μέθοδο startComplete κάνουμε τους απαραίτητους ελέγχους στα textfields για να βεβαιωθούμε πως ο χρήστης συμπλήρωσε σώστα τα στοιχεία και δεν άφησε κάποιο κένο ωστέ στην συνέχεια απο την startComplete
    // +να πάμε στην completeTicket όπου και θα αποθηκευτούν σε ένα string array τα στοιχεία του εισητηρίου και να εκτυπωθούν στο text area αλλα και να αποθηκευτούν στην συνεχεία στο txt αρχείο.
    public void startComplete(){

        // Αποθηκεύουμε σε string variables τα δεδομένα απο τα textfields.
        String clientName = name.getText();
        String itinerary = destination.getText();
        String dates = date.getText();
        String passen = passengers.getText();
        String lug = luggage.getText();
        String travelClas = (String) travelClass.getItemAt(travelClass.getSelectedIndex());

        // Ελέγχουμε αν ο χρήστης έχει συμπληρώσει όλα τα πεδία και δεν έχει αφήσει κάνενα ασυμπλήρωτο.
        if( (!clientName.isEmpty()) && (!itinerary.isEmpty())
                && (!dates.isEmpty()) && (!passen.isEmpty())
                && (!lug.isEmpty()) && (!travelClas.equals("(Select a class to travel)"))){

            // Ελέγχουμε την πιθανότητα που ο χρήστης εκχωρήσει τον αριθμό ' 0 'στο textfield που ζητάει τους passengers.
            if(passen.equals("0") || !(checkIsNumber(passen)) || !(checkIsNumber(lug)) )
            {
                //Σε περίπτωση που όντως ο χρήστης τον αριθμό '0' τότε θα του εμφανιστεί ένα pop up error window πως πρέπει να αλλάξει την επιλογή του σε κάτι έγγυρο.
                JOptionPane.showMessageDialog( CreateTicketFrame.this,
                        "Ooops! There's seems to be a problem with the input of passengers or luggage! Please try again.",
                        "Booking Ticket Error!",
                        JOptionPane.WARNING_MESSAGE);
                return;

            }else{

                // Αν έχει συμπληρώσει όλα τα πεδία τότε κάλουμε την completeTicket ώστε να προχωρήσουμε στο να εκτυπώσουμε το ολικό ticket και να το εκχωρήσουμε στο ticketList array.
                completeTicket(clientName,itinerary,dates,passen,lug,travelClas);
            }

        }else {

            // Σε περίπτωση όμως που έχει αφήσει έστω και ένα κενό θα  εμφανιστεί ένα pop up error window ώστε ο χρήστης στην συνέχεια να συμπληρώσει τα σημεία που άφησε κενά.
            JOptionPane.showMessageDialog( CreateTicketFrame.this,
                    "You need to fill all the blank spaces!",
                    "Booking Ticket Error!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

    }


    // Στην completeTicket κάνουμε generate τα 3 στοιχεία που δεν εκχώρησε ο χρήστης δηλαδή το issue date, price και το ticket id.
    // Έπειτα θα αποθηκέυσουμε τα στοιχεία του ticket στο ticketList και τα εμφανίζουμε στο textArea.
    public void completeTicket(String clientName, String itinerary, String date, String passen, String lug, String travelClass){

        //------start of issueDate--------
        // Έφοσον μας ζητάει η εκφώνηση να αποθηκεύσουμε και να εμφανίσουμε στον χρήστη την ημερομηνία έκδοσης χρησιμοποιούμε την κλάση SimpleDateFormat.
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date(); // Το currentDate object περιέχει την τωρινή ημερομηνία.
        String issueDate = formatter.format(currentDate); // αποθηκεύουμε την ημερομηνία στην μεταβλητή issueDate.
        //------end of issueDate---------


        //------start of price--------
        //Για την εκτύπωση της τιμής του εισητηρίου θα χρησιμοποιήσουμε την κλάση Random και θα δημιουργήσουμε το αντικείμενο rand.

        Random rand = new Random(); // Δημιουργία αντικειμένου rand.
        //  Ορίζουμε τα όρια που θέλουμε να λειτουργήσει η rand.
        Double leftlimit=Double.valueOf(0);
        Double rightlimit=Double.valueOf(0);

        // Για να γίνει πιο ρεαλιστική η εκτύπωση της τιμής του εισητηρίου, έλεγχουμε το travel class του χρήστη. Αν επιλέξει First Class τότε η τιμή rand θα εμφανίσει τιμή απο τα 500 εως τα 1000.
        // Αντίστοιχα και για τις άλλες travel class θα εμφανίσει την ανάλογη τιμή.
        if(travelClass.equals("First Class")){
            leftlimit = Double.valueOf(500);
            rightlimit = Double.valueOf(1000);
        }else if(travelClass.equals("Business Class")){
            leftlimit = Double.valueOf(300);
            rightlimit = Double.valueOf(700);
        }else if(travelClass.equals("Economy Class")){
            leftlimit = Double.valueOf(15);
            rightlimit= Double.valueOf(300);
        }

        Double temp = ( leftlimit + rand.nextDouble()* (rightlimit - leftlimit) ); // Αποθηκεύουμε σε μια προσωρινή το αποτέλεσμα της rand.
        //Αν ο χρήστης εκχωρήσει αριθμό επιβατών μεγαλύτερο ή ίσον του 2 ή παραπάνω αριθμό αποσκευών τότε να αυξηθεί το κόστος.
        if (Integer.parseInt(passen)>=2){
            for(int i=0; i< (Integer.parseInt(passen)); i++)
                temp = temp + 100;

        }
        if (Integer.parseInt(lug) >= 2){
            for(int i=0; i< (Integer.parseInt(lug)); i++)
                temp = temp + 50;

        }
        String p = String.format("%.02f",temp); // Κρατάμε από τον double αριθμό μόνο τους δυο πρώτους δεκαδικούς αριθμούς.
        String price = p.replaceAll(",","."); // Αντικαθιστούμε το κόμμα με τελεία γιατί διαφορετικά θα δημιουργηθούν στην συνέχεια προβλήματα στις κλάσεις Ticket και TicketHistoryFrame.

        //------end of price--------



        //------start of ticketID --------
        // Για την δημιουργία του ticket ID θα χρησιμοποιήσουμε την ίδια κλάση Random . Το ticket ID αποτελείται απο αριθμούς και χαρακτήρες. Είναι της μόρφης " 1ABCDE ".
        Random tickt = new Random();
        // Αρχικά ορίζουμε σε δύο μεταβλήτες όλους τους αριθμόυς και τους χαρακτήρες.
        String Characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NumericString = "0123456789";

        // Χρησιμοποιούμε την κλάση StringBuilder για να δημιουργήσουμε το ticketID μεγέθους 6 χαρακτήρων.
        StringBuilder sb = new StringBuilder(6);

        // Κάνουμε generate έναν τυχαίο αριθμό που θα μπει στην αρχή του string.
        int randomInt = tickt.nextInt(NumericString.length());
        sb.append(NumericString.charAt(randomInt)); //Προσθέτουμε τον αριθμό στο string.

        // Χρησιμοποιούμε μία επανάληψη for για να κάνουμε generate τους υπόλοιπους 5 χαρακτήρες και να τους προσθέσουμε στο string.
        for(int i=0;i<5;i++){
            int index = tickt.nextInt(Characters.length());
            sb.append(Characters.charAt(index));
        }
        String ticketID = sb.toString(); // Αποθηκεύουμε το τελικό αποτέλεσμα στην μεταβλητή ticketID
        //------end of ticketID--------

        // Δημιουργούμε το αντικείμενο ticket και εκχωρούμε σε αυτό τα δεδομένα του εισιτηρίου.
        Ticket ticket =  new Ticket(ticketID, issueDate, clientName, itinerary, price, date, passen, lug, travelClass);

        //το προσθέτουμε στον ticketList array
        ticketList.add(ticket);

        // Εκχωρούμε τα στοιχεία του ticket στο textarea
        area.append("");
        area.append(" Ticket ID: \t"+ ticketID +
                "\n\n Issue Date: \t"+ issueDate +
                "\n\n Name:  \t"+ clientName+
                "\n\n Itinerary: \t"+ itinerary+
                "\n\n Price: \t"+ price + " €" +
                "\n\n Date: \t"+ date +
                "\n\n Passengers: \t"+ passen +
                "\n\n Luggage: \t"+ lug +
                "\n\n Travel Class: \t" + travelClass);
        //area.append("\n");

        // Αφαιρούμε την δυνατότητα από τον χρήστη να μπορεί να ξανα πατήσει το κουμπί Complete και να εκτυπώσει παραπάνω απο μία φορά το ίδιο ticket στο textArea.
        completeBtn.setEnabled(false);
        // Επιτρέπουμε στον χρήστη να αποθηκεύσει το ticket στο αρχείο txt.
        saveBtn.setEnabled(true);
    }

    // Στην saveTicketList αποθηκεύουμε το ticket από την completeTicket στο αρχείο ticketlist.txt
    public void saveTicketList(){
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter writer = new BufferedWriter(fw);

            //Χρησιμοποιούμε την for each για την εγγραφή του εισητηρίου στο txt αρχείο.
            for(Ticket ticket:ticketList){
                writer.write(ticket.toString());
                writer.newLine(); //Αλλάζει γραμμή μέτα από κάθε εγγραφή
            }

            writer.close(); //κλείνουμε το αρχείο

            //Εμφανίζει ένα pop up message πως αποθηκεύτηκε επιτυχώς το εισητήριο.
            JOptionPane.showMessageDialog(
                    CreateTicketFrame.this,
                    "You have successfully purchased a ticket and it has been saved in " + fileName,
                     "Save Completed",
                    JOptionPane.INFORMATION_MESSAGE);

            //Μόλις γίνει η αποθήκευση του εισητηρίου ενεργοποιείται ξανά το κουμπί complete
            completeBtn.setEnabled(true);
            this.dispose(); // Μολις γίνει η αποθήκευση το παράθυρο κλείνει από μόνο του..

        } catch (IOException e) {
            JOptionPane.showMessageDialog(saveBtn,
                    "Can't access " + fileName,
                    "File access error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Ελέγχουμε αν ο χρήστης εκχώρησε αριθμό και όχι χαρακτήρες.
    public static boolean checkIsNumber(String numb){
        try {
            Integer.parseInt(numb);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
 //------- End point of functionality for the elements-------
}
