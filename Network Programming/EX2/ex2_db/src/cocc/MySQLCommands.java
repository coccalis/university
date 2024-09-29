package cocc;

import java.sql.*;
import java.util.Scanner;

public class MySQLCommands {

    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;


    public static void connectDb() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");//MariaDB Driver
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/askisi1_db", "kokkalis", "1234"); //MariaDB
//            System.out.println("Connected\n");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnectDb(){
        try {
            //Close the connection and release the resources
            if(rs != null) {
                rs.close();
                assert st != null;
                st.close();
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Close Resources exception " + ex);
        }

    }

    public static void showDb() {
        String sqlCommand = "select * from students"; //enter the sql command
        executeSqlQuery(sqlCommand); //sent the sql command
    }

    public static String searchDb(String ID){

            String sqlCommand = "select * from students where ID like '%" +ID +"%'"; //sql command
            executeSqlQuery(sqlCommand); //call method to execute command
            try {
                while (true) {
                    assert rs != null;
                    if (!rs.next()) break;
                    String std = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +
                            rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
                    return std;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Statement exception " + ex);
            }
            return "Student not found";

    }


    public static String addDb(String data){

        String[] std = data.split("#");
        if (std.length == 6) {
            String sqlCommand = "INSERT INTO students (ID, firstname, lastname, university, semester, Modules) VALUES ('" +
                    std[0] + "','" +
                    std[1] + "','" +
                    std[2] + "','" +
                    std[3] + "','" +
                    std[4] + "','" +
                    std[5] + "')";


            try {
                // Create and execute the SQL statement
                pst = conn.prepareStatement(sqlCommand);
                pst.executeUpdate(); // insert the new data to the database
                return "Student with ID: " +std[0] + " and Surname: " + std[2] + " was added successfully";
            } catch (SQLException ex) {
                System.out.println("SQL Statement exception " + ex);
            }
        }else{
            return  "Error with student";
        }
        return "Failed to add Student. Check Again";

    }



    public static   void executeSqlQuery(String sqlCommand) {
        try {
            // Create an SQL statement
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(sqlCommand); // execute the command

        } catch (SQLException ex) {
            System.out.println("SQL Statement exception " + ex);
        }
    }



}