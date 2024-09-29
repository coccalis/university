
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class Cal_listener implements ActionListener {

    String n0,n1,n2;
    JTextField tf1;
    public Cal_listener(JTextField tf1) {
        this.tf1 = tf1;
        n0=n1=n2="";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String c = e.getActionCommand();

            // ελέγχουμε αν είναι αριθμός ή κόμμα το κουμπί που θα πατηθεί
           if( (c.charAt(0) >= '0' && c.charAt(0) <= '9') || c.charAt(0)=='.'){

               //if(conv_Num(c)) {
                   if (!n1.equals("")) {
                       n2 = n2 + c;
                   } else {
                       n0 = n0 + c;
                   }
                   tf1.setText(n0 + n1 + n2);
               //}
             //ελέγχουμε αν πάτησε ο χρήστης το κουμπί '='
           }else if(c.charAt(0)== '='){
                //καλούμε την μέθοδο result όπου θα εμφανίσει το αποτέλεσμα σε string.
               String sum = result(n1,n0,n2);
               tf1.setText(n0 + n1 + n2 + "=" + sum);
               n0 = sum;
               n1=n2="";

           }else{

               if (n1.equals("") || n2.equals("")){
                   n1=c;
               }else {
                   String sum=result(n1,n0,n2);
                   n0 = sum;
                   n1=c;
                   n2="";

               }
           }
           tf1.setText(n0+n1+n2);
    }

    public String result(String op, String n1, String n2){
        double sum;
        String res;
        //μετατρέπουμε για κάθε πράξη τα n1 και n2 απο string σε double
        //Έπειτα ξανα μετατρέπουμε το sum απο double σε string καθώς υπήρχε ένα error και μου αποθήκευε το αποτέλεσμα της προηγούμενης πράξης
        switch (op){
            case "+":
                sum = (Double.parseDouble(n1) + Double.parseDouble(n2));
                res = Double.toString(sum);
                break;
            case "-":
                sum = (Double.parseDouble(n1) - Double.parseDouble(n2));
                res = Double.toString(sum);
                break;
            case "*":
                sum = (Double.parseDouble(n1) * Double.parseDouble(n2));
                res = Double.toString(sum);
                break;
            case "/":
                if(n2.equals("0")) {
                    res = "Can not be divided by zero";
                }  else {
                    sum = (Double.parseDouble(n1) / Double.parseDouble(n2));
                    res = Double.toString(sum);
                }
                break;
            default:

                res = "";
                break;
        }
        return res;
    }

    public boolean conv_Num(String c){
        int num = Integer.parseInt(c);
        return true;
    }
}
