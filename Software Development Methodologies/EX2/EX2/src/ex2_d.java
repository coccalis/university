
import java.io.*;

public class ex2_d {
    public static void main(String[] args) {
        long n = 51, f1 = 0, f2 = 1, sum; // Δηλώνουμε τις μεταβλητές long διότι μπορεί να αποθηκεύσει 64bit παραπάνω από int.
        // Σημείωση στην αρχή το είχα δηλώσει int αλλά σαν αποτέλεσμα μου έβγαινα κάποιοι αριθμοί αρνητικοί.

        for (int i = 1; i <= n; ++i) {
            String str = "F(" + (i - 1) + ")" + "= " + f1 + "\n"; //εκτύπωση της μορφης πχ F(0)=0
            WritetoFile(str); //κάλουμε την WritetoFile για να εκτυπώσει το αποτέλεσμα στο αρχείο.
            sum = f1 + f2; //υπολογίζουμε το άθροισμα.
            f1 = f2;
            f2 = sum;
        }
        ReadfromFile(); //κάλουμε την ReadfromFile για να εμφανίσει τα περιεχόμενα από το αρχείο.
    }


    static void WritetoFile(String str) {
        File file = new File("Fibonacci.txt"); //δημιουργούμε το αρχείο Fibonacci
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.write(str); //εκτύπωση μέσα στο αρχείο.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close(); //κλείνουμε το αρχείο.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void ReadfromFile() {
        int a = 0;
        try {

            BufferedReader buffer = new BufferedReader(new FileReader("Fibonacci.txt")); //χρησιμοποιούμε την κλάση BufferedReader για να διαβάσουμε τα περιεχόμενα του αρχείου.
            String s;
            a++;
            while ((s = buffer.readLine()) != null) { //θα συνεχίσει να διαβάζει μέχρι να είναι με ίσο με null.
                String[] str = s.split("=");
                System.out.println(str[a]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}