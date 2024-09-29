
import javax.swing.*;
import java.awt.*;

public class ex4_b {
    public static void main(String[] args) {

        Calculator_details frame = new Calculator_details();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //για να κλείσει το παράθυρο με το Χ

        //---- start of ui details -------
        frame.setSize(415,440);
        frame.setTitle("Calculator");
        System.setProperty("bgrd","0X656565");
        frame.getContentPane().setBackground(Color.getColor("bgrd"));
        frame.setLocationRelativeTo(null); //για να εμφανίστει στο κέντρο της οθόνης το παράθυρο
        frame.setResizable(false);
        frame.setLayout(null);
        // ---- end of ui details ---------

        //----- start of elements buttons, textfields etc. -------
        frame.buttons();
        frame.textfield();
        frame.cal_listener();
        //----- end of elements buttons,textfields etc. -------


        frame.setVisible(true); //για να εμφανιστεί το παράθυρο
    }
}
