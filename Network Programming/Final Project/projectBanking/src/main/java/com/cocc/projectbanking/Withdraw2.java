
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "withdraw2", value = "/withdraw2")
public class Withdraw2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        //Παίρνουμε τα στοιχεία απο Withdraw σελίδα και τα αποθηκεύουμε σε τοπικές μεταβλητές
        String sid = request.getParameter("AccountID");
        int id = Integer.parseInt(sid);
        String withdraw = request.getParameter("Withdraw");

        Account account = AccountsDAO.getAccountById(id);
        String balance = account.getBalance();

        double newBalance = Double.parseDouble(balance);
        double withdrawAmount = Double.parseDouble(withdraw);

        // Ελέγχουμε αν το υπόλοιπο του χρήστη είναι επαρκή για να μπορεί να γίνει η ανάληψη. Αν δεν είναι επαρκή τότε εμφανίζεται η σελίδα InsufficientBalance.jsp
        if( (newBalance - withdrawAmount) >= 0 ) {
            newBalance -= withdrawAmount;

            Account newAccount = new Account();
            newAccount.setId(id);
            newAccount.setBalance(String.valueOf(newBalance));


            int status = AccountsDAO.updateBalance(newAccount);
            if (status > 0) { //Αν γίνει με επιτυχία τότε θα επιστρέψει τη σελίδα WithdrawSuccess.jsp αλλιώς αν προκύψει κάποιο σφάλμα θα επιστρέψει τη σελίδα WithdrawError
                request.getRequestDispatcher("WithdrawSuccess.jsp").include(request, response);
            } else {
                response.sendRedirect("WithdrawSuccess.jsp");

            }
        }else{
            request.getRequestDispatcher("InsufficientBalance.jsp").include(request, response);
        }


    }

}
