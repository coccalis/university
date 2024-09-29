
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteAccount", value = "/deleteAccount")
public class DeleteAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //παίρνουμε το id του χρήστη απο το accounts table
        String sid=request.getParameter("id");
        int id = Integer.parseInt(sid);
        int status = AccountsDAO.deleteAccount(id);
        if(status>0){ //Αν γίνει με επιτυχία τότε θα επιστρέψει τη σελίδα DeleteSuccess.jsp αλλιώς αν προκύψει κάποιο σφάλμα θα επιστρέψει τη σελίδα DeleteError.jsp
            request.getRequestDispatcher("DeleteSuccess.jsp").include(request,response);
        }else {
            request.getRequestDispatcher("DeleteError.jsp").include(request,response);

        }


    }

}
