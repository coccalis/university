package cocc;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class echo extends Thread{

    private Socket socket;

    public echo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner reader = new Scanner(socket.getInputStream());

            while (true){

                Boolean running = true;

                int choice;
                String std;

                while (running){

                    String usrInput = reader.nextLine();
                    if (usrInput != null){
                        String[] data = usrInput.split("-");

                        choice = Integer.parseInt(data[0]);

                        if(choice == 1){
                            System.out.println("Searching. Please wait!");
                            std = MySQLCommands.searchDb(data[1]);
                            writer.println(std);
                            running = false;

                        }else  if(choice == 2){
                            System.out.println("Adding student");
                            std = MySQLCommands.addDb(data[1]);
                            writer.println(std);
                            running=false;

                        }else if(choice == 3){
                            running=false;
                            System.out.println("Exit...");
                        }


                    }

                }

            }





        }catch (IOException ex){
            ex.printStackTrace();

        }finally {
            {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Oh " + e.getMessage());
                }
            }
        }

    }




}