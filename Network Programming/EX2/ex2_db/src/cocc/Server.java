package cocc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        int port = 5000;
        ServerSocket serverSocket = null;
        Socket socket = null;

        MySQLCommands.connectDb();
        System.out.println("Connecting with the database!");

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port: " + port);
            System.out.println("Waiting for client...");

            while(true){
                socket = serverSocket.accept();
                echo echo = new echo(socket);
                echo.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        MySQLCommands.disconnectDb();

    }

}
