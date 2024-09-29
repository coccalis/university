package cocc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        Socket socket = null;

        try {
            System.out.println("+-----------------------------------------------+");
            System.out.println("|Trying to connect to server please be patient..");
            socket = new Socket(hostname,port);
            System.out.println("|Connection successful..!!");
            System.out.println("+-----------------------------------------------+");


            boolean userOption = true;
            while (userOption){

                System.out.println("""
                        \n
                        1. Search Student by his ID.
                        2. Add new Student.
                        3. Exit.
                        """);
                System.out.println("Choose one of the above options: ");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();

                userOption = options(choice,socket);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static boolean options(int choice,  Socket socket){

        if(choice == 1){
            System.out.println("Enter the ID of the Student: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            controlData(id,choice,socket);

        }else if (choice == 2){

            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the follow user data and make sure they are separated with #: " +
                    "Example: AM#FIRSTNAME#LASTNAME#UNIVERSITY#SEMESTER#PASSED_MODULES");
            String data = sc.nextLine();
            controlData(data,choice,socket);


        }else if(choice == 3){
            System.out.println("Disconnecting from the server!");
            try {
                System.out.println("Closing connection!");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;

        }

        return true;
    }


    public static void controlData(String data, int choice, Socket socket){

            try{
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner reader = new Scanner(socket.getInputStream());

                writer.println(choice + "-" + data);
                System.out.println("+-----------------------------------------------+");
                System.out.println("| Sending the data to server!");
                String student = reader.nextLine();
                System.out.println("| Reading the server answer!");
                System.out.println("+-----------------------------------------------+");
                System.out.println(student);

            } catch (IOException e) {
                e.printStackTrace();
            }


    }





}
