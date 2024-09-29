/*

ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface THListenerInterface extends Remote{
    // είδοποιήση των πελατών σε περίπτωση διαθέσιμης θέσης
    void notifyCustomerListener(Seat seat) throws RemoteException;
}
