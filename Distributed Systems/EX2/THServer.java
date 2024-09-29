
ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class THServer{

    public static void main(String[] args) throws RemoteException{
        try {
            TheaterService thImpl = new TheaterService(); // create an implementation to save the previous actions
            int port = 3000;
            Registry registry = LocateRegistry.createRegistry(port);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/TheaterService";
            Naming.rebind(url,thImpl); // to save the previous actions
            System.out.println("Server started at port: " +port);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}