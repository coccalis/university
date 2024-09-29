
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A_Details extends JFrame implements ActionListener {
    JButton add_but, sub_but, clear_but, mult_but, div_but;
    JTextField tf1, tf2, tf3;
    private JLabel l1, l2;


    public void buttons() {
        System.setProperty("mycolor", "0XB1B1B1");

        //----start of add button----------
        add_but = new JButton("Add"); // δημιουργία του add button.
        add_but.setBounds(30, 80, 60, 30); //ορίζουμε την θέση που θα βρίσκετε και το μέγεθος
        add_but.setBackground(Color.getColor("mycolor"));
        //------end of add button----------

        //-----start of sub button -------
        sub_but = new JButton("Subtract");// δημιουργία του sub button.
        sub_but.setBounds(95, 80, 85, 30);
        sub_but.setBackground(Color.getColor("mycolor"));
        //-----end of sub button-----------

        //----- start of mult button ------
        mult_but = new JButton("Multiply");
        mult_but.setBounds(185, 80, 80, 30);
        mult_but.setBackground(Color.getColor("mycolor"));
        //----- end of mult button --------

        //------ start of div button -------
        div_but = new JButton("Divide");
        div_but.setBounds(270, 80, 80, 30);
        div_but.setBackground(Color.getColor("mycolor"));
        //------ end of div button --------


        //-----start of clear button-----------
        clear_but = new JButton("Clear");// δημιουργία του clear button.
        clear_but.setBounds(290, 220, 80, 30);
        clear_but.setBackground(Color.getColor("mycolor"));
        //-----end of clear button -----------

        //κάνουμε add τα buttons στο frame
        add(add_but);
        add(sub_but);
        add(mult_but);
        add(div_but);
        add(clear_but);
    }

    public void Texts() {
        //---- tf1 ----
        tf1 = new JTextField(15);//δημιουγία του textfield
        tf1.setBounds(40, 45, 140, 20);
        add(tf1);

        //---- tf2 ----
        tf2 = new JTextField(15);
        tf2.setBounds(185, 45, 140, 20);
        add(tf2);

        // Μια εναλακτική αντι για label θα μπορούσαμε να βάλουμε ενα textfield που να εμφανίζει το αποτέλεσμα.
        //---- tf3 ----
//        tf3 = new JTextField();
//        tf3.setBounds(133,130,105,20);
//        tf3.setEditable(false); //απενεργοποιούμε την επιλογή να μπόρει να γράψει ο χρήστης έκει που θα εμφανίσει το αποτέλεσμα
//        add(tf3);

        //-----l1--------
        l1 = new JLabel("Result: ");
        l1.setForeground(Color.BLACK);
        l1.setBounds(90, 130, 100, 20);
        add(l1);

        //----l2---------
        l2 = new JLabel(" Answer goes here");
        l2.setForeground(Color.BLACK);
        l2.setBounds(135, 130, 130, 20);
        add(l2);
    }

    public void action() {
        //κάνουμε την σύνδεση των κουμπιων με actionlistener
        add_but.addActionListener(this);
        sub_but.addActionListener(this);
        mult_but.addActionListener(this);
        div_but.addActionListener(this);
        clear_but.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int num1 = Integer.parseInt(tf1.getText()); //Μετάτροπη το input του χρήστη από String σε Integer.
        int num2 = Integer.parseInt(tf2.getText());
        int value = 0;

        if (e.getSource() == clear_but) { //αν ο χρήστης πατήσει το κουμπί clear τότε τα text field και το label θα καθαρίσουν. Ελέγχουμε αν ο χρήστης πατήσει κάποιο από τα κουμπία με την e.getSource().
            tf1.setText("");
            tf2.setText("");
            l2.setText("Answer goes here");
        } else {

            if (e.getSource() == add_but) {
                    value = num1 + num2;
                l2.setText(value + ""); //ξάνα μετατρέπουμε το value από integer σε string.
            } else if (e.getSource() == sub_but) {

                value = num1 - num2;
                l2.setText(value + ""); //ξάνα μετατρέπουμε το value από integer σε string.
            } else if (e.getSource() == mult_but) {

                value = num1 * num2;
                l2.setText(value + ""); //ξάνα μετατρέπουμε το value από integer σε string.
            } else if (e.getSource() == div_but) {

                if (num2 == 0) { //Έλεγχος για την διαίρεση.
                    l2.setText("Cant divide by zero");
                } else {
                    value = num1 / num2;
                    l2.setText(value + ""); //ξάνα μετατρέπουμε το value από integer σε string.
                }
            }

        }

    }


}
