/*

ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.io.IOException;

public class THClient extends UnicastRemoteObject implements THListenerInterface{
    static Remote remoteObject;
    static THInterface theaterRemote; //καλούμε τις συναρτήσεις
    public THClient() throws RemoteException{
        
    }

    public static void main(String[] args) {
        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":3000/TheaterService";   //σύνδεση με τον σέρβερ
            remoteObject = Naming.lookup(url);
            theaterRemote = (THInterface) remoteObject; //πραγματοποιήση ενεργειών 
            int resvNum;
            
            if ( args.length == 0) {
                System.out.println(
                """
                	List of commands:
                        1.java THClient list <hostname> (shows all the seats)\s
                        2.java THClient book <hostname> <type> <number> <name> (to book your seat)\s
                        3.java THClient guests <hostname> (show all the reservations)\s
                        4.java THClient cancel <hostname> <type> <number> <name> (cancel a reservation)\s
                """
                );
            } else {
                switch(args[0]){
                    case "list" -> System.out.println(theaterRemote.availableSeats()); //δείξε την λίστα με τις διαθέσιμες θέσεις.

                    case "book" -> {
                        // Δημιουργία μια συνάρτησης για τον έλεγχο διαθεσιμότητας των θέσεων. Έαν υπάρχουν διαθέσιμες θέσεις τότε μπορεί να κάνει κράτηση.
                        
                        resvNum =Integer.parseInt(args[2]);
                        String output = theaterRemote.book(args[1], resvNum, args[3]);
                        
                        if(seatsAvailable(output)){
                            
                            resvNum = Integer.parseInt(output);
                           
                            if (resvNum > 0) { // δεν υπάρχουν αρκέτες διαθέσιμες θέσεις για τον πελάτη.
                                System.out.println("Available seats :" +output + "\n Do you wish to book for " + output + " seats? \n");
                                System.out.println("Enter \n 1 for Yes \n 2 for No.");
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                String choice = br.readLine();

                                switch(Objects.requireNonNull(choice)){ //χρησιμοποιούμε την requireNonNull για ελέγξουμε αν ο χρήστης εισάγει κάποια επιλογή
                                    case "1" -> System.out.println(theaterRemote.book(args[1], Integer.parseInt(output), args[3])); // πραγματοποιήση της κράτησης
                                    
                                    case "2" -> { // Για να ειδοποιήσει τον χρήστη όταν αδιάσουν οι θέσεις.
                                        System.out.println("Do you want to be notified when a seat is available?\n");
                                        System.out.println("Enter \n 1 for Yes \n 2 for No");
                                        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                                        String choice2 = br2.readLine();

                                        switch(Objects.requireNonNull(choice2)){
                                            case "1" -> {
                                                try {
                                                    THClient thClient = new THClient();
                                                    System.out.println(theaterRemote.addGuestListener(args[1], thClient)); // πρόσθεσε τον χρήση στην λίστα αναμονής
                                                } catch (RemoteException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            case "2" -> System.out.println("\nGood day!");

                                        }
                                    }
                                }
                            } else {
                                System.out.println("Do you want to be notified when a seat is available?\n");
                                System.out.println("Enter \n 1 for Yes \n 2 for No");
                                BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
                                String choice3 = br3.readLine();

                                switch(Objects.requireNonNull(choice3)){
                                    case "1" -> {
                                        try {
                                            THClient thClient = new THClient();
                                            System.out.println(theaterRemote.addGuestListener(args[1], thClient));
                                        } catch (RemoteException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    case "2" -> System.out.println("\nGood day!");
                                }
                            }
                        }else{
                            // Η κράτηση έγινε με επιτυχία
                            System.out.println(output);
                        }
                    }

                    case "guests" -> System.out.println(theaterRemote.guests());

                    case "cancel" -> {
                        resvNum = Integer.parseInt(args[2]);
                        System.out.println(theaterRemote.cancelReservation(args[1], resvNum, args[3])); // για να ειδοποιησεί τους χρήστες όταν έγινε ακύρωση κράτησης.
                    }

                    default -> {
                        System.out.println(
                        """
                        	List of commands:
                                1.java THClient list <hostname> (shows all the seats)\s
                                2.java THClient book <hostname> <type> <number> <name> (to book your seat)\s
                                3.java THClient guests <hostname> (show all the reservations)\s
                                4.java THClient cancel <hostname> <type> <number> <name> (cancel a reservation)\s
                        """
                        );
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyCustomerListener(Seat seat){ // ειδοποιήσησ του χρήστη όταν οι θέσεις είναι διαθέσιμες.
        System.out.println("The " + seat.getType() + " has now: " + seat.getAvailableSeats() + " seats available\n");
    }

    public static boolean seatsAvailable(String output){ //πάρε τα δεδομένα του χρήστη.
        try {
            Integer.parseInt(output);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
