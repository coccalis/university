/*

ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.rmi.server.UnicastRemoteObject;


public class TheaterService extends UnicastRemoteObject implements THInterface {
    Seat PA;
    Seat PB;
    Seat PC;
    Seat KE;
    Seat PTH;

    ArrayList<Seat> seats = new ArrayList<>();
    HashMap<String, Seat> thCustomers = new HashMap<>();

    ArrayList<THListenerInterface> toNotifyPA = new ArrayList<>();
    ArrayList<THListenerInterface> toNotifyPB = new ArrayList<>();
    ArrayList<THListenerInterface> toNotifyPC = new ArrayList<>();
    ArrayList<THListenerInterface> toNotifyKE = new ArrayList<>();
    ArrayList<THListenerInterface> toNotifyPTH = new ArrayList<>();

    public TheaterService() throws RemoteException{
        PA = new Seat("PA", 100, 45);
        PB = new Seat("PB", 200, 35);
        PC = new Seat("PC", 400, 25);
        KE = new Seat("KE", 225, 30);
        PTH = new Seat("PTH", 75, 20);
        Collections.addAll(seats, PA, PB, PC, KE, PTH); // προσθήκη όλων τον θέσεων σε μια λίστα.
    }

    @Override
    public String availableSeats() throws RemoteException{ // επιστρέφει την λίστα με τα διαθέσιμα δωμάτια
        return seats.toString();
    }

    //δημιουργία κράτησης θέσεων
    @Override
    public String book(String type, int resvSeats, String name) throws RemoteException{ 
        return switch (type) {
            case "PA" -> reservation(0, resvSeats,  name); //κράτηση για την ζώνη ΠΑ
            case "PB" -> reservation(1, resvSeats,  name);//κράτηση για την ζώνη ΠΒ
            case "PC" -> reservation(2, resvSeats,  name);//κράτηση για την ζώνη ΠΓ
            case "KE" -> reservation(3, resvSeats,  name);//κράτηση για την ζώνη ΚΕ
            case "PTH" -> reservation(4, resvSeats,  name);//κράτηση για την ζώνη ΠΘ
            default -> null; //δεν υπάρχει η ζώνη
        };
    }

    public String reservation(int seatNum, int resvNum, String name){
        //επιστρέφει τις διαθέσιμε θέσεις.
        int availableSeats = seats.get(seatNum).getAvailableSeats(); 

        if(resvNum <= availableSeats){ // υπάρχουν διαθέσιμες θέσεις για να πραγματοποιήσει κράτηση
            availableSeats -= resvNum; // ενημέρωση των διαθέσιμων θέσεων 
            Seat seat = new Seat(seats.get(seatNum).getType(), resvNum, seats.get(seatNum).getCost()); //δημιουργία θέσης για να πραγματοποιηθεί η κράτηση.
            thCustomers.put(name, seat); // αποθηκεύση όλων των κρατήσεων
            seats.get(seatNum).setAvailableSeats(availableSeats); // αλλαγή των διαθέσιμων θέσεων
            return "\nSeat is booked and the cost is: " +seats.get(seatNum).getCost() * resvNum + "\n"; //επιστρέφει το κόστος της κράτησης
        
        }else if(resvNum > availableSeats && availableSeats > 0){ //αν δεν υπάρχουν διαθέσιμες θέσεις
            return String.valueOf(availableSeats); //επιστρέφει πόσες θέσεις είναι διαθέσιμες.
    
        }else{ //δεν υπάρχουν διαθέσιμες θέσεις.
            return "0";
        }
    }

    @Override
    public String guests() throws RemoteException{
        StringBuilder output = new StringBuilder(); // επιστρέφει την λίστα με τις κρατήσεις
        for(String name: thCustomers.keySet()){
            output.append("Name: ").append(name).append("\n").append(thCustomers.get(name)).append("\n");
        }
        return output.toString();
    }

    //Ακύρωση της κράτησης
    @Override
    public String cancelReservation(String type, int num, String name) throws RemoteException{
        String output = "Your reservation for those seats doesn't exist.\n"; // Ορίζουμε ως default πως δεν υπάρχει η κράτηση
        if(thCustomers.get(name) != null && Objects.equals(thCustomers.get(name).getType(), type)){ // Αν υπάρχει η κράτηση
            switch (type) {//άλλαξε τις διαθέσιμες θέσεις και ενημέρωση του χρήστη
                case "PA" -> changeSeatBooking(name, 0, num); 
                case "PB" -> changeSeatBooking(name, 1, num);
                case "PC" -> changeSeatBooking(name, 2, num);
                case "KE" -> changeSeatBooking(name, 3, num);
                case "PTH" -> changeSeatBooking(name, 4, num);
            }
        }
        return output;
    }

    public String changeSeatBooking(String name, int type, int cancleSeats) throws RemoteException{
        //αλλαγη της διαθεσιμότητας των θεσεων 
        int availableSeatsNum =  seats.get(type).getAvailableSeats();
        int resvNum = thCustomers.get(name).getAvailableSeats();
        int newAvailableSeats = availableSeatsNum + cancleSeats;
        int newResvSeats = resvNum = cancleSeats;

        if(newResvSeats >= 0){ 
            seats.get(type).setAvailableSeats(newAvailableSeats);
            thCustomers.get(name).setAvailableSeats(newResvSeats);
            Seat seat = thCustomers.get(name);

            switch(seat.getType()){
                case "PA" -> notifyCustomer(seats.get(type), toNotifyPA);
                case "PB" -> notifyCustomer(seats.get(type), toNotifyPB);
                case "PC" -> notifyCustomer(seats.get(type), toNotifyPC);
                case "KE" -> notifyCustomer(seats.get(type), toNotifyKE);
                case "PTH" -> notifyCustomer(seats.get(type), toNotifyPTH);
            }

            if(newResvSeats == 0 ){ // αν ο χρήστης ακυρώσει όλες τις θέσεις τότε γίνεται η αφαίρεση του απο την λίστα κρατήσεων
                thCustomers.remove(name);
            }

            return "Your Booking has now: " + newResvSeats + " seats.\n";
        }
        return "The booking has only " + resvNum + "and you wish to cancel this amount of seats: " + newResvSeats + "\n";
    }

    //Ενημέρωση χρήστη για διαθέσιμες θέσεις.
    public void notifyCustomer(Seat seat, ArrayList<THListenerInterface> toNotify){
        try {
            for(THListenerInterface customers : toNotify){
                customers.notifyCustomerListener(seat);
            }
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }


    @Override
    public String addGuestListener(String type, THListenerInterface thListener){
        //ενημέρωση του χρήστη για τις διαθέσιμε θέσεις
        switch(type){
            case "PA" -> toNotifyPA.add(thListener);
            case "PB" -> toNotifyPB.add(thListener);
            case "PC" -> toNotifyPC.add(thListener);
            case "KE" -> toNotifyKE.add(thListener);
            case "PTH" -> toNotifyPTH.add(thListener);
        };
        return "When the " + type + " of seat is available, a message will be sent to you to notify you! :)\n";
    
    }

    @Override
    public String removeGuestListener(String type, THListenerInterface thListener){
        return null;
    }
}

