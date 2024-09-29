
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="deactivate", value = "/deactivate")
public class DeactivateAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Η διαδικασία είναι η ίδια με το ActivateAccount Servlet
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);


        Account account = AccountsDAO.getAccountById(id);

        String statusAccount = account.getStatus();
        statusAccount = "DEACTIVATED";

        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setStatus(statusAccount);



        int status = AccountsDAO.updateStatus(newAccount);
        if (status>0){
            request.getRequestDispatcher("AccountDeactivatedSuccess.jsp").include(request,response);
        }else {
            response.sendRedirect("accountsTable");
        }



    }
}
