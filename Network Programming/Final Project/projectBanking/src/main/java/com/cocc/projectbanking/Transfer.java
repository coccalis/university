
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "transfer", value = "/transfer")
public class Transfer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        //παίρνουμε το id του χρήστη απο το accounts table
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Account account = AccountsDAO.getAccountById(id);

        // Δηλώνουμε το head της σελίδας
        out.println("<!DOCTYPE html>" +
                "                <html lang=\"en\">" +
                "                <head>" +
                "                    <meta charset=\"UTF-8\">" +
                "                    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "                    <title>Account Withdraw</title>" +
                "                                " +
                "                    <link rel=\"stylesheet\" href=\"css/cssGeneral.css\">" +
                "                                " +
                "                </head>" +
                "                <body>");
        // Navigation Bar
        out.println("<header class=\"header\" style=\"font-weight: bold;\">" +
                "                        <div class=\"wrapper header-wrapper\">" +
                "                            <a href=\"index.jsp\" class=\"brand\" style=\"font-size: 25px; padding-left: 10px;\"><span style=\"color: #3361af;\">Uniwa</span> Bank</a>" +
                "                            <nav class=\"nav\">" +
                "                                <ul class=\"nav-wrapper\" style=\"font-weight: bold;\">" +
                "                                    <li class=\"nav-item\"><a href=\"index.jsp\" >Home</a></li>" +
                "                                    <li class=\"nav-item\"><a href=\"accountsTable\">View All Accounts</a></li>" +
                "                                    <li class=\"nav-item\"><a href=\"About.jsp\">About</a></li>" +
                "                                    <li class=\"nav-item\"><a href=\"CreateAccount.jsp\" class=\"button-nav\">Create Account</a></li>" +
                "                                </ul>" +
                "                            </nav>" +
                "                        </div>" +
                "                    </header>");

        // Ta inputs που θα βάλει ο χρήστης
        out.println("<div class=\"div-middle\">\n" +
                "        <h1>Transfer Money</h1>\n" +
                "        <label style=\"font-size: medium;\" >Enter the recipients Account ID and the amount you want to transfer.</label>\n" +
                "        <form  action='transfer2' method='post'>\n" +
                "            <input class=\"inputs\" type=\"text\"  name=\"AccountIDSend\" value='" + account.getId() + "' readonly required/>\n" +
                "            <input class=\"inputs\" type=\"text\"  name=\"AccountIDRecv\" placeholder=\"The Recipients Account ID\"  required/>\n" +
                "            <br><br>\n" +
                "            <input class=\"inputs\" type=\"text\"  name=\"Transfer\" placeholder=\"Amount\" required/><br>\n" +
                "            <input type=\"submit\" value=\"Transfer\" class=\"button\" style= \"margin-top: 30px;\" ></input>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");

        out.close();
    }

}
