
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="deposit2", value = "/deposit2")
public class Deposit2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        //Παίρνουμε τα στοιχεία απο Deposit σελίδα και τα αποθηκεύουμε σε τοπικές μεταβλητές
        String sid = request.getParameter("AccountID");
        int id = Integer.parseInt(sid);
        String deposit = request.getParameter("Deposit");

        Account account = AccountsDAO.getAccountById(id);
        String balance = account.getBalance();

        //μετατρέπουμε τα String σε double
        double newBalance = Double.parseDouble(balance);
        double depositAmount = Double.parseDouble(deposit);


        newBalance += depositAmount;

        //Δημιουργούμε το αντικείμενο account
        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setBalance(String.valueOf(newBalance));


        int status = AccountsDAO.updateBalance(newAccount);
        if (status>0){//Αν γίνει με επιτυχία τότε θα επιστρέψει τη σελίδα DepositSuccess.jsp αλλιώς αν προκύψει κάποιο σφάλμα θα επιστρέψει τη σελίδα DepositError.jsp
            request.getRequestDispatcher("DepositSuccess.jsp").include(request,response);
        }else {
            request.getRequestDispatcher("DepositError.jsp").include(request,response);

        }


    }


}
