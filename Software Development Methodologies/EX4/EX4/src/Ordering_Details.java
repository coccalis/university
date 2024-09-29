
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ordering_Details extends JFrame implements ActionListener {
    private JPanel p1,p2,p3;
    private JLabel l1,l2,l3;
    private JButton btn;
    private JRadioButton small,medium,large;
    private JCheckBox pepperoni,anchovies;
    private JComboBox cbox;
    public void panels(){
        //----- start of first panel------
        p1 = new JPanel(new GridBagLayout()); //Δημιουργούμε το panel
        p1.setBounds(10,10,385,100); //ορίζουμε την θέση και το μέγεθος του panel 1
        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size")); //του βάζουμε border στο panel και τίτλο Size

        //-------start of RadioButton ---------------------
        // Δημιουργία των radiobutton
        small = new JRadioButton("Small");
        medium = new JRadioButton("Medium");
        large = new JRadioButton("Large");

        //τα βάζουμε σε ένα buttongroup ώστε να μπόρουμε να επιλέξουμε ένα από αυτά όχι όλα
        ButtonGroup group = new ButtonGroup();
        group.add(small);
        group.add(medium);
        group.add(large);
        //----------- end of RadioButton ----------------

        p1.add(small);p1.add(medium);p1.add(large);
        add(p1);
        //-----end of first panel----------

        //----start of second panel--------
        p2 = new JPanel();
        p2.setBounds(20,120,365,100);
        p2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Toppings"));

        //δημιουργούμε τα checkboxes
        pepperoni = new JCheckBox("Pepperoni");
        anchovies = new JCheckBox("Anchovies");

        p2.add(pepperoni);p2.add(anchovies);
        add(p2);
        //----end of second panel-------------

        //----start of third panel------------
        p3 = new JPanel();
        p3.setBounds(30,230,345,100);
        p3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Delivery"));

        // τοποθετούμε σε ένα string πίνακα τις επιλογές
        String delivery[] = {"(Select a delivery option)","Eat-in","Take-out","Home-Delivery"};
        cbox = new JComboBox(delivery); //εκχωρούμε το string delivery στο ComboBox
        cbox.setBounds(20,250,90,20);
        p3.add(cbox);

        add(p3);
        //----end of third panel-----------

        //----- start of labels --------------
        //Δημιουργούμε το label
        //--l1
        l1 = new JLabel(" ");
        l1.setForeground(Color.BLACK);
        l1.setBounds(30,390,300,20);
        add(l1);
        //---l2
        l2 = new JLabel(" ");
        l2.setForeground(Color.BLACK);
        l2.setBounds(26,420,300,20);
        add(l2);
        //---l3
        l3 = new JLabel(" ");
        l3.setForeground(Color.BLACK);
        l3.setBounds(30,450,300,20);
        add(l3);
        //----- end of labels-----------------

        //------start of button-------------
        btn = new JButton("Complete Order");
        System.setProperty("mycolor","0XB1B1B1");
        btn.setBackground(Color.getColor("mycolor")); btn.setFont(new Font("Courier",Font.BOLD,14));
        btn.setBounds(115,340,160,40);

        add(btn);
        //-----end of button----------
    }
    public void action(){
        //--button---
        btn.addActionListener(this);

        //--Check Box---
        pepperoni.addActionListener(this);
        anchovies.addActionListener(this);
        //----combo box---
        cbox.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Όταν ο χρήστης πατήσει το κουμπί complete order τότε θα του εμφανιστούν οι επιλογές που έκανε σε label.
        if(e.getSource()==btn) {
            String radiomsg=" ";

            // Ελέγχουμε ποίο Radiobutton πάτησε.
            if(small.isSelected()){
                radiomsg = " Small ";
            }else if(medium.isSelected()){
                radiomsg=  " Medium ";
            }else if(large.isSelected()){
                radiomsg = " Large ";
            }else{
                //αν δεν επιλέξει κάποιο τότε θα του εμφανίσει κατάλληλο μήνυμα.
                radiomsg=" You need to select a size ";
            }

            String checkmsg="You need to select at least one topping";

                //Αν δεν επιλέξει κανένα τότε θα εμφανίσει κατάλληλο μηνύμα.
               if (pepperoni.isSelected()) {
                   checkmsg = " Pepperoni ";
               }
               if (anchovies.isSelected()) {
                   checkmsg += " Anchovies ";
               }

               // Επειδή έχουμε βάλει string πίνακα απλά αντλούμε τις πληροφορίες με την getItemAt κλπ.
               String del = "-Delivery Option: " + cbox.getItemAt(cbox.getSelectedIndex());

               l1.setText("-Size: " +radiomsg);
               l2.setText(" -Toppings: " + checkmsg);
               l3.setText(del);
           }

    }
}
