
import java.io.*;
import java.util.Scanner;


public class ex2_c {
    //δηλώνουμε global μεταβλητές
    static int AlphaUpper=0, AlphaLower=0, GamaUpper=0, GamaLower=0, OmegaUpper=0, OmegaLower=0;
    static int One=0, Three=0, Five=0, Seven=0, Nine=0;

    public static void main(String[] args) {
        //FileInputStream inputStream=null;
        Scanner sc=new Scanner(System.in); //κλάση scanner


        System.out.println("Please give the full path of the file. ");
        String fl= sc.next(); //διαβάζουμε το full path του αρχείου

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fl)); //Χρησιμοποιούμε την κλάση BufferedReader για να διαβάσουμε τα περιεχόμενα του αρχείου.
            String str;
            int a;

            while( (a= buffer.read()) != -1){ //θα συνεχίσει να διαβάζει το αρχείο μέχρι να επιστρέψει ο buffer.read() -1
                Char_counter((char)a); //κάλουμε την Char_counter για να υπολογίσουμε τα γράμματα
                Number_counter((char)a);//κάλουμε την Number_counter για να υπολογίσουμε τους αριθμούς.
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        output(); // κάλουμε την output για να εκτυπώσει τα αποτελέσματα. Δεν είναι αναγκαία απλά κάνει πιο οργανωμένο το πρόγραμμα.

    }


    static void Char_counter(char a){

        switch(a){ //Χρησιμοποιούμε την switch καθώς είναι πιο γρήγορη από την if.
            case 'Α':
                AlphaUpper++;
                break;
            case 'α':
                AlphaLower++;
                break;
            case 'Γ':
                GamaUpper++;
                break;
            case 'γ':
                GamaLower++;
                break;
            case 'Ω':
                OmegaUpper++;
                break;
            case 'ω':
                OmegaLower++;
                break;
        }
    }

    static void Number_counter(char number){
        switch (number){ //Ομοίως
            case '1':
                One++;
                break;
            case '3':
                Three++;
                break;
            case '5':
                Five++;
                break;
            case '7':
                Seven++;
            case '9':
                Nine++;
                break;
        }

    }

    static void output(){
        System.out.println("Results: ");
        System.out.println("---------");
        System.out.println("Alpha Upper: " +AlphaUpper);
        System.out.println("Alpha Lower: " +AlphaLower);
        System.out.println("Gama Upper:  " +GamaUpper);
        System.out.println("Gama Lower: " +GamaLower);
        System.out.println("Omega Upper: " +OmegaUpper);
        System.out.println("Alpha Lower: " +OmegaLower);
        System.out.println("------------------------");
        System.out.println("One: " +One);
        System.out.println("Three: " +Three);
        System.out.println("Five: " +Five);
        System.out.println("Seven: " +Seven);
        System.out.println("Nine: " +Nine);
        System.out.println("-----------------------");
    }
}

