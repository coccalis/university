
import javax.swing.*;
import java.awt.*;

public class ex4_c {
    public static void main(String[] args) {

        Ordering_Details frame = new Ordering_Details();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //για να κλείσει το παράθυρο με το Χ.

        //----- start of ui details-------
        frame.setSize(420,550);
        frame.setTitle("Ordering app");
        System.setProperty("bgrd","0XC1C1C1");
        frame.getContentPane().setBackground(Color.getColor("bgrd"));
        frame.setLocationRelativeTo(null); //για να εμφανιστεί το παράθυρο στο κέντρο.
        frame.setResizable(false);
        frame.setLayout(null);
        //----- end of ui details ----------

        //----- start of elements buttons, textfields etc. -------

        frame.panels();
        frame.action();
        //----- end of elements buttons,textfields etc. -------


        frame.setVisible(true); //για να εμφανιστεί το παράθυρο


    }
}
