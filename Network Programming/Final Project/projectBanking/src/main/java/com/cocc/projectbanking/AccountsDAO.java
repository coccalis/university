
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAO {

    //Πραγματοποίηση σύνδεσης με τη βάση δεδομένων
    public static Connection getConnection(){
        Connection con = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");//MariaDB Driver
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/banking_db", "kokkalis", "1234"); //MariaDB
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }


    // Προσθέτουμε τον νέο λογαριασμό στον πίνακα accounts
    public static int addAccount(Account account){
        int status =0;
        Connection conn = AccountsDAO.getConnection();
        String sqlCommand = "INSERT INTO accounts (A_FirstName , A_Surname , A_BirthDate , A_Email, A_Phone , A_Address, A_Country , A_Balance, A_Status) " +
                            "VALUES (?,?,?,?,?,?,?,?,?);" ;
        try{
            PreparedStatement ps = conn.prepareStatement(sqlCommand);
            //Αποθηκεύουμε τα στοιχεία στον πίνακα.
            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getSurname());
            ps.setString(3, account.getBirthDate());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setString(6, account.getAddress());
            ps.setString(7, account.getCountry());
            ps.setString(8, account.getBalance());
            ps.setString(9, "ACTIVE"); //Όταν δημιουργείτε ένας καινούργιος λογαριασμός υποθέτουμε πως by default θα είναι ενεργός.


            status= ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    // Ανανέωση υπολοίπου
    public static int updateBalance(Account account){
        int status =0;
        Connection con = AccountsDAO.getConnection();

        try{
            PreparedStatement ps =con.prepareStatement("update accounts set A_Balance= ? where Account_ID = ?");
            ps.setString(1,account.getBalance());
            ps.setInt(2,account.getId());

            status=ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }
    //Ανανέωση κατάστασης λογαριασμού
    public static int updateStatus(Account account){
        int status =0;
        Connection con = AccountsDAO.getConnection();

        try{
            PreparedStatement ps =con.prepareStatement("update accounts set A_Status= ? where Account_ID = ?");
            ps.setString(1,account.getStatus());
            ps.setInt(2,account.getId());

            status=ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }


    //Διαγραφή λογαριασμού απο τον πίνακα
    public static int deleteAccount(Integer ID){
        int status=0;
        Connection con = AccountsDAO.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("delete from accounts where Account_ID=?");
            ps.setInt(1, ID);
            status=ps.executeUpdate();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;

    }

    //Για να παίρνουμε τα στοιχεία του χρήστη απο τον πίνακα και να κάνουμε τις απαραίτητες λειτουργίες όπως πχ να δούμε τα στοιχεία του στο ViewAccounnt.
    public static Account getAccountById(Integer ID){
        Account account = new Account();
        Connection con = AccountsDAO.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from accounts where Account_ID=?");
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                    account.setId(rs.getInt(1));
                    account.setFirstName(rs.getString(2));
                    account.setSurname(rs.getString(3));
                    account.setBirthDate(rs.getString(4));
                    account.setEmail(rs.getString(5));
                    account.setPhone(rs.getString(6));
                    account.setAddress(rs.getString(7));
                    account.setCountry(rs.getString(8));
                    account.setBalance(rs.getString(9));
                    account.setStatus(rs.getString(10));


            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return account;
    }

    // Αντλούμε όλα τα στοιχεία των λογαριασμών για να τα εμφανίσουμε στο AccountsTable
    public static List<Account> getAllAccounts(){
        List<Account> studentList = new ArrayList<>();
        Connection con = AccountsDAO.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from accounts");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setFirstName(rs.getString(2));
                account.setSurname(rs.getString(3));
                account.setBirthDate(rs.getString(4));
                account.setEmail(rs.getString(5));
                account.setPhone(rs.getString(6));
                account.setAddress(rs.getString(7));
                account.setCountry(rs.getString(8));
                account.setBalance(rs.getString(9));
                account.setStatus(rs.getString(10));

                studentList.add(account);
            }
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentList;
    }


}
