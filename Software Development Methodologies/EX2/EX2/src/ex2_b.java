
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ex2_b {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(); // δημιουργούμε τον πίνακα list
        Scanner sc=new Scanner(System.in); //κλάση scanner


        System.out.println("Please give the full path of the file. If the file you want to copy is in the same folder as the program just type the name. ");
        String fl = sc.next();

        if (fl.isEmpty()) {
            System.out.println("You entered nothing in the path. Try again. ");
        }

        System.out.println("---------------------------------");
        System.out.println("Please give the full path of the second file.  ");
        String fl2 = sc.next();

        if (fl2.isEmpty()){
            System.out.println("You entered nothing in the path. Try again. ");
        }


        try {
            FileInputStream stream = new FileInputStream(fl); //διαβάζουμε τα περιεχόμενα του αρχείου που θέλουμε.
            int number;
            while( (number= stream.read()) != -1 ){ //θα συνεχίσει να διαβάζει μέχρι η strea.read() να επιστρέψει -1 καθώς τότε θα έχει φτάσει στο τέλος.
                list.add(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream outputStream=new FileOutputStream(fl2); //ανοίγουμε το δεύτερο αρχείο που θέλουμε
            for(int a:list){
                outputStream.write(a); //εκτύπωση στο δεύτερο αρχείο
            }
            System.out.println("Copied the contents of one file to another successfully. ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
