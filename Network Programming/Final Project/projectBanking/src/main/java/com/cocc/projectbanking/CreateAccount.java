
// ΤΜΗΜΑ: ΔΠΖ03

package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createAccountServlet", value = "/createAccount-servlet")
public class CreateAccount extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Παίρνουμε τα στοιχεία απο τη jsp-html σελίδα και τα αποθηκεύουμε σε τοπικές μεταβλητές για να τα αποθηκεύσουμε στην βάση δεδομένων
        String firstName = request.getParameter("Firstname");
        String Surname = request.getParameter("Surname");
        String BirthDate = request.getParameter("BirthDate");
        String Email = request.getParameter("Email");
        String Phone = request.getParameter("Phone");
        String Address = request.getParameter("Address");
        String Country = request.getParameter("Country");
        String Balance = request.getParameter("Balance");


        //Δημιουργούμε το αντικείμενο account
        Account account = new Account();
        account.setFirstName(firstName);
        account.setSurname(Surname);
        account.setBirthDate(BirthDate);
        account.setEmail(Email);
        account.setPhone(Phone);
        account.setAddress(Address);
        account.setCountry(Country);
        account.setBalance(Balance);

        //Κάλεσε την addAccount για να αποθηκεύσει τον νέο λογαριασμό
        int status = AccountsDAO.addAccount(account);
        if(status>0){ //Αν γίνει με επιτυχία τότε θα επιστρέψει τη σελίδα CreatedSuccess.jsp αλλιώς αν προκύψει κάποιο σφάλμα θα επιστρέψει τη σελίδα CreatedError.jsp
            request.getRequestDispatcher("CreatedSuccess.jsp").include(request,response);
        }else {
            request.getRequestDispatcher("CreatedError.jsp").include(request,response);

        }
        out.close();

    }

    public void destroy() {
    }
}
