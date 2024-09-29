import javax.swing.*;
import java.awt.*;

public class Ordering_Details extends JFrame {
    private JPanel p1,p2,p3;
    private JLabel l1;
    public void panels(){
        //----- start of first panel------
        p1 = new JPanel(new GridBagLayout()); //Δημιουργούμε το panel
        p1.setBounds(10,10,385,100); //ορίζουμε την θέση και το μέγεθος του panel 1
        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size")); //του βάζουμε border στο panel και τίτλο Size

        //-------start of RadioButton ---------------------
        // Δημιουργία των radiobutton
        JRadioButton small = new JRadioButton("Small");
        JRadioButton medium = new JRadioButton("Medium");
        JRadioButton large = new JRadioButton("Large");

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
        JCheckBox pepperoni = new JCheckBox("Pepperoni");
        JCheckBox anchovies = new JCheckBox("Anchovies");

        p2.add(pepperoni);p2.add(anchovies);
        add(p2);
        //----end of second panel-------------

        //----start of third panel------------
        p3 = new JPanel();
        p3.setBounds(30,230,345,100);
        p3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Delivery"));

        // τοποθετούμε σε ένα string πίνακα τις επιλογές
        String delivery[] = {"(Select a delivery option)","Eat-in","Take-out","Home-Delivery"};
        JComboBox cbox = new JComboBox(delivery); //εκχωρούμε το string delivery στο ComboBox
        cbox.setBounds(20,250,90,20);
        p3.add(cbox);

        add(p3);
        //----end of third panel-----------

        //----- start of l1 --------------
        //Δημιουργούμε το label
        l1 = new JLabel("Message goes here");
        l1.setForeground(Color.BLACK);
        l1.setBounds(140,350,130,20);
        add(l1);
        //----- end of l1-----------------

    }

}
