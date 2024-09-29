
import java.util.ArrayList;
import java.util.Scanner;

public class ex2_a {
    public static void main(String[] args) {
        // Δημιουργούμε τους πίνακες am, avrg, studinfo για να αποθηκεύσουμε τα ΑΜ, μέσους όρους και πληροφορίες για τους μαθητές.
        ArrayList<String> am = new ArrayList<>();
        ArrayList<Float> avrg = new ArrayList<>();
        ArrayList<String> Studinfo = new ArrayList<>();

        Scanner sc=new Scanner(System.in); // κλάση scanner.
        int stud_count = 0; // counter για το πόσους μαθητές θα εκχωρήσει.

        while(true){
            float sum_grd = 0; //μεταβλητή που υπολογίζει το ολικό άθροισμα των βαθμών
            int sum_sub = 0; //μεταβλητή που υπολιγίζει το πλήθος τον μαθημάτων που θα εκχωρήσει ο χρήστης

            System.out.println("Please enter the students information. ");
            String info = sc.nextLine(); //για να μπορεί να διαβάσει τα στοιχεία σε μια γραμμή.
            Studinfo.add(info);

            String[] arr = info.split(" ", 0); //χωρίζουμε τα στοιχεία με κένο .
            am.add(arr[0]); //αποθηκεύουμε το αμ για να το εκτυπώσουμε στο τέλος.

            if ( info.equals("000000") ){ //αν είναι το αμ = "000000" τότε να τερματιστεί η while.
                break;
            }

            int i=2; // οι βαθμοί των μαθημάτων βρίσκονται στην άρτια θέση του πίνακα εξού και το i αρχικοποιείται με το 2.
            int j=1; //το μαθήματα που είναι string θα βρίσκονται στις περιττές θέσεις του πίνακα εξού και το j αρχικοποιείται με το 1.
            while(true){ //δεύτερη while για να υπολογίσουμε τον μέσο όρο των βαθμών.
                String sub = String.valueOf(arr[j]); // για να μετατρέψουμε το όνομα του μαθήματος σε string
                if (sub.equals("end") || sub.equals("END")){ //έλεγχος αν το μάθημα είναι ίσο με την λέξη end ώστε να τερματιστεί η εκχώρηση και να προχωρήσει στον επόμενο μαθητή.
                    break;
                }
                j+=2;
                float grd = Float.valueOf(arr[i]).floatValue(); //για να μετατρέψουμε τον βαθμό σε float
                if( (grd >= 5) && (grd <= 10) ){ //έλεγχος αν ο βαθμός ειναι προσβάσιμος ώστε να υπολογιστεί στον μέσο όρο αλλίως θα εμφανίστει ένα μήνυμα στον χρήστη πως ο βαθμός του δεν είναι προσβάσιμος
                    sum_grd = sum_grd + grd;
                    sum_sub++;
                }else{
                    System.out.println("Your grade for the subject " + sub + " is below 5 so it wont count in the average. \n");
                }
                i += 2;

            }
            if(sum_sub == 0){ //Αν η μεταβλητή sum_sub είναι μηδέν τότε ο μέσος όρος θα είναι μήδεν. Είναι ένας απαραίτητος έλεγχος γιατι διαφορετικά θα έβγαζε ένα απρόβλεπτο απότελεσμα για μέσο όρο.
                avrg.add((float) 0);
            }else{
                avrg.add(sum_grd/sum_sub); //προσθέτουμε στον πίνακα avrg
            }
            stud_count++;
        }

        //Χρησιμοποιούμε την for για να εκτυπώσουμε τους μέσους όρους των μαθητών.
        for(int i=0; i<stud_count; i++){
            System.out.println("Average of Student" +am.get(i) + ":" +avrg.get(i));
            System.out.println("------------------------------------------------");
        }

        System.out.println("The Averages of the students have been printed with a success!");

    }

}
