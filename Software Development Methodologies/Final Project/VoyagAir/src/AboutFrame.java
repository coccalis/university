import javax.swing.*;
import java.awt.*;


public class AboutFrame extends JFrame {

    public void aboutFrame(){

         prepareUI();
         prepareButton();
         prepareText();

    }

    public void prepareUI(){
        // Η διαδικασία που ακολοθούμε για την σχεδίαση του παραθύρου είναι η ίδια με όλα τα windows.
        this.setSize(500,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(null); // turn off flowlayout


        System.setProperty("bgrd","0XD0E2F4");
        this.getContentPane().setBackground(Color.getColor("bgrd"));

        Image icon = Toolkit.getDefaultToolkit().getImage("VoyagAir.jpg");
        this.setIconImage(icon);

        this.setVisible(true);
    }

    public void prepareText(){
        //Χρησιμοποιούμε ένα label για το κείμενο με τις λεπτομέριες αλλά σε html format.
        JLabel l1 = new JLabel();

        l1.setText("<html><center> <h2 style='font-family:Serif;color:black;'>VoyagAirApp</h2> <p style='font-family:Serif;color:black;'>Name: Christos Kokkalis  </p> <p style='font-family:Serif;color:black;'>AM: 19390090  </p> " +
                "<p style='font-family:Serif;color:black;'> Date Finished: 13/06/2021  </p> <p style='font-family:Serif;color:black;'> Copyright © 2021-2030 by Chris Kokkalis </p> <p> <strong> All rights reserved. </strong></p></center> </html>");

        l1.setBounds(130,10,400,150);
        this.add(l1);
    }

    public void prepareButton(){
         // Δημιουργία του κουμπιου show image για να εμφανιστεί η εικόνα που μας ζητά η εκφώνηση.
         System.setProperty("color","0XE1EDF8");
         //---start of image button----
         JButton imageBtn = new JButton("Show Image");
         imageBtn.setBackground(Color.getColor("color"));
         imageBtn.setBounds(180,180,120,30);
         add(imageBtn);

         imageBtn.addActionListener(e -> showImage());
         //---end of image button----
    }

    public void showImage(){
        //Αρχικά δημιουργούμε το αντικείμενο screen από την κλάση ImageIcon.
          ImageIcon screen = new ImageIcon("Screenshot.png");

          //Παίρνουμε την είκονα και την αποθηκεύουμε στο screenImage
          Image  screenImage = screen.getImage();
          Image  modifiedScreenImage=screenImage.getScaledInstance(1080,600, Image.SCALE_SMOOTH); // Έπειτα επιλέγουμε τις διαστάσεις της εικόνας.
          screen = new ImageIcon(modifiedScreenImage);

          //Τέλος θα εμφανίσει ένα pop up window με την εικόνα.
          JOptionPane.showMessageDialog(AboutFrame.this,"","The IDE used for the project is \"IntelliJ IDEA\" ",JOptionPane.INFORMATION_MESSAGE,screen);

     }









}
