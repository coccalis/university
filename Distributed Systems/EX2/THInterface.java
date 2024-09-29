/*

ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface THInterface extends Remote{
    // invalid param

    //available seats
    String availableSeats() throws RemoteException;

    //book seat
    String book(String type, int seats, String name) throws RemoteException;

    //all guests
    String guests() throws RemoteException;

    //cancel
    String cancelReservation(String type, int rooms, String name) throws RemoteException;

    //add the guests to the waiting list
    String addGuestListener(String type, THListenerInterface thListener) throws RemoteException;

    //removes the guests from the waiting list
    String removeGuestListener(String type, THListenerInterface thListener) throws RemoteException;
}
