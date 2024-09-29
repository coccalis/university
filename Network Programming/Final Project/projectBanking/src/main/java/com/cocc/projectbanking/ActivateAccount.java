
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="activate", value = "/activate")
public class ActivateAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id"); //παίρνουμε το id του χρήστη απο το accounts table
        int id = Integer.parseInt(sid);
        Account account = AccountsDAO.getAccountById(id); //παίρνουμε τα στοιχεία του χρήστη απο τον πίνακα

        String statusAccount = account.getStatus(); //Παίρνουμε το status του λογαριασμού και το αποθηκεύουμε σε μια τοπική μεταβλητή
        statusAccount = "ACTIVE";  // Αλλάζουμε την κατάσταση του λογαριασμού σε ACTIVE

        //Δημιουργούμε το αντικείμενο account
        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setStatus(statusAccount);


        // Ανανεώνουμε την κατάσταση του λογαριασμού στον πίνακα
        int status = AccountsDAO.updateStatus(newAccount);
        if (status>0){ // Αν πραγματοποιηθεί η ενημέρωση με επιτυχία εμφανίζετε η σελίδα AccountActivatedSuccess διαφορετικά επιστρέφει στο accountTable
            request.getRequestDispatcher("AccountActivatedSuccess.jsp").include(request,response);
        }else {
            response.sendRedirect("accountsTable");
        }



    }
}
