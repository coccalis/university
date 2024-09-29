
import javax.swing.*;
import java.awt.*;

public class ex3_a {

    public static void main(String[] args) {
        A_Details frame = new A_Details(); //Δημιούργουμε το frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //για να κλείσει το παράθυρο με το Χ και να τερματιστεί το πρόγραμμα.

        //---start of ui details-------
        frame.setSize(400,300); // ορίζουμε το μέγεθος του παραθύρου
        frame.setTitle("Simple Calculator"); //βάζουμε τίτλο στην μπάρα
        System.setProperty("bgrd","0X656565"); // Οι εντολές στις γραμμές 13-14 χρεισιμοποιούνται για να ορίσουμε ένα συγκεκριμένο χρώμα
        frame.getContentPane().setBackground(Color.getColor("bgrd"));
        frame.setLocationRelativeTo(null); //για να είναι το παράθυρο στο κέντρο
        frame.setLayout(null); // turn off flowlayout
        frame.setResizable(false);// απενεργοποιούμε την επιλογή το παράθυρο να γίνει resizable
        // ----end of ui details ---------

        //----- start of elements buttons, textfields etc. -------

        frame.buttons();
        frame.TextField();

        //----- end of elements buttons,textfields etc. -------

        frame.setVisible(true); //για να εμφανιστεί το παράθυρο
    }

}
