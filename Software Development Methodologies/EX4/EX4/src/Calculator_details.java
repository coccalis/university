
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Calculator_details extends JFrame {
    private JButton b0,b01,b02,b03,b04,b05,b06,b07,b08,b09,b_add,b_sub,b_div,b_mul,b_eq,b_cl,b_com;
    JTextField tf1;

    public void buttons(){

        //------ start of buttons-----------
        //Δημιουργούμε τα κουμπία για το κομπιουτεράκι.
        b0 = new JButton("0");
        b01 = new JButton("1");
        b02 = new JButton("2");
        b03 = new JButton("3");
        b04 = new JButton("4");
        b05 = new JButton("5");
        b06 = new JButton("6");
        b07 = new JButton("7");
        b08 = new JButton("8");
        b09 = new JButton("9");
        b_add = new JButton("+");
        b_sub = new JButton("-");
        b_div = new JButton("/");
        b_mul = new JButton("*");
        b_eq = new JButton("=");
        b_com = new JButton(".");

        //----- start of color of buttons -------
        //Βάζουμε χρώμα στα κουμπία.
        System.setProperty("mycolor","0XB1B1B1");
        b0.setBackground(Color.getColor("mycolor"));b01.setBackground(Color.getColor("mycolor"));b02.setBackground(Color.getColor("mycolor"));
        b03.setBackground(Color.getColor("mycolor"));b04.setBackground(Color.getColor("mycolor"));b05.setBackground(Color.getColor("mycolor"));
        b06.setBackground(Color.getColor("mycolor"));b07.setBackground(Color.getColor("mycolor"));b08.setBackground(Color.getColor("mycolor"));
        b09.setBackground(Color.getColor("mycolor"));b_add.setBackground(Color.getColor("mycolor"));b_sub.setBackground(Color.getColor("mycolor"));
        b_div.setBackground(Color.getColor("mycolor"));b_mul.setBackground(Color.getColor("mycolor"));b_eq.setBackground(Color.getColor("mycolor"));
        b_com.setBackground(Color.getColor("mycolor"));
        //------ end of color of buttons----------

        //------ start of font style--------------
        // αλλάζουμε το μέγεθος της γραμματοσειράς των κουμπίων και  κάνουμε bold τους χαρακτήρες
        b0.setFont(new Font("Courier",Font.BOLD,20)); b01.setFont(new Font("Courier",Font.BOLD,20)); b02.setFont(new Font("Courier",Font.BOLD,20));
        b03.setFont(new Font("Courier",Font.BOLD,20));b04.setFont(new Font("Courier",Font.BOLD,20));b05.setFont(new Font("Courier",Font.BOLD,20));
        b06.setFont(new Font("Courier",Font.BOLD,20));b07.setFont(new Font("Courier",Font.BOLD,20));b08.setFont(new Font("Courier",Font.BOLD,20));
        b09.setFont(new Font("Courier",Font.BOLD,20));b_sub.setFont(new Font("Courier",Font.BOLD,20));b_add.setFont(new Font("Courier",Font.BOLD,20));
        b_mul.setFont(new Font("Courier",Font.BOLD,20));b_div.setFont(new Font("Courier",Font.BOLD,20));b_eq.setFont(new Font("Courier",Font.BOLD,20));
        b_com.setFont(new Font("Courier",Font.BOLD,20));
        //------- end of font style ------------

        //-------- stop of buttons----------


        //--------start of Panel -------------
        JPanel panel = new JPanel();//Δημιουργούμε το panel όπου θα τοποθετηθούν τα κουμπία
        panel.setLayout( new GridLayout(4,4)); //Θα χρησιμοποιήσουμε το Gridlayout και θέλουμε τα κουμπία να βρίσκονται σε 4 γραμμες και 4 στήλες
        panel.setBounds(0,50,400,350);

        //προσθέτουμε τα κουμπία στο panel.
        panel.add(b07);panel.add(b08);panel.add(b09);panel.add(b_add);
        panel.add(b04);panel.add(b05);panel.add(b06);panel.add(b_sub);
        panel.add(b01); panel.add(b02);panel.add(b03);panel.add(b_mul);
        panel.add(b0);panel.add(b_com);panel.add(b_eq); panel.add(b_div);

        panel.setBackground(Color.LIGHT_GRAY);
        this.add(panel); //προσθέτουμε το panel στο παράθυρο.
        //-------end of panel -----------------


    }
    public void textfield(){
        //------start of textfield ------------
        tf1 = new JTextField();
        tf1.setBounds(0,0,400,50);
        tf1.setEditable(false);
        tf1.setHorizontalAlignment(JTextField.RIGHT); //βάζουμε αυτή την εντολή ώστε οι πράξεις που θέλει να κάνει ο χρήστης να εμφανίζονται στα αριστερά όπως όλα τα κομπιουτεράκια
        this.add(tf1); //προσθέτουμε το textfield στο παράθυρο.
        //------end of textfield-------------------

    }

    public void cal_listener(){
        ActionListener actionListener = new Cal_listener(tf1);

        //---------number buttons--------------
        b0.addActionListener(actionListener);b01.addActionListener(actionListener);b02.addActionListener(actionListener);
        b03.addActionListener(actionListener);b04.addActionListener(actionListener);b05.addActionListener(actionListener);
        b06.addActionListener(actionListener);b07.addActionListener(actionListener);b08.addActionListener(actionListener);
        b09.addActionListener(actionListener);
        //--------operators buttons---------
        b_add.addActionListener(actionListener);b_sub.addActionListener(actionListener);b_mul.addActionListener(actionListener);
        b_div.addActionListener(actionListener);b_eq.addActionListener(actionListener);b_com.addActionListener(actionListener);

        // Πατώντας δυο φόρες το '=' κάνει clear το textfield απο προηγούμενες πράξεις
        b_eq.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    tf1.setText("");

                }
            }
        });




    }
}
