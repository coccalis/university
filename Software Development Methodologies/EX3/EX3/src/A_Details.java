
import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class A_Details extends JFrame{

    private JButton add_but, sub_but, clear_but;
    private JTextField tf1,tf2,tf3;
    private JLabel l1,l2;

    public void buttons(){

        //----start of add button----------
        add_but = new JButton("+"); // δημιουργία του add button.
        add_but.setBounds(160,30, 50,30); //ορίζουμε την θέση που θα βρίσκετε και το μέγεθος
        System.setProperty("mycolor","0XB1B1B1");
        add_but.setBackground(Color.getColor("mycolor"));
        //------end of add button----------

        //-----start of sub button -------
        sub_but= new JButton("-");// δημιουργία του sub button.
        sub_but.setBounds(160,70, 50,30);
        System.setProperty("mycolor","0XB1B1B1");
        sub_but.setBackground(Color.getColor("mycolor"));
        //-----end of sub button-----------

        //-----start of clear button-----------
        clear_but = new JButton("CLEAR");// δημιουργία του clear button.
        clear_but.setBounds(290,220, 80,30);
        System.setProperty("mycolor","0XB1B1B1");
        clear_but.setBackground(Color.getColor("mycolor"));
        //-----end of clear button -----------

        //κάνουμε add τα buttons στο frame
        add(add_but);
        add(sub_but);
        add(clear_but);



    }

    public void TextField(){
       //---- tf1 ----
        tf1 = new JTextField();//δημιουγία του textfield
        tf1.setBounds(30,55,100,20);
        add(tf1);

        //---- tf2 ----
        tf2 = new JTextField();
        tf2.setBounds(240,55,100,20);
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
        l1.setBounds(90,130,100,20);
        add(l1);

        //----l2---------
        l2 = new JLabel(" Answer goes here");
        l2.setForeground(Color.BLACK);
        l2.setBounds(135,130,130,20);
        add(l2);
    }

    public void action(){

    }

}
