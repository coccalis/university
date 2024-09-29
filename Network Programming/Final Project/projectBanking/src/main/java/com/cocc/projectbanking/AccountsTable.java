
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "accountsTable", value = "/accountsTable")
public class AccountsTable extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        List<Account> accountList = AccountsDAO.getAllAccounts();

        // Δηλώνουμε το head της σελίδας
        out.println("<!DOCTYPE html>" +
                "                <html lang=\"en\">" +
                "                <head>" +
                "                    <meta charset=\"UTF-8\">" +
                "                    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "                    <title>View Accounts</title>" +
                "                                " +
                "                    <link rel=\"stylesheet\" href=\"css/cssAccountsTable.css\">" +
                "                </head>" +
                "                <body>");

        // Navigation Bar
        out.println(" <header class=\"header\" style=\"font-weight: bold;\">" +
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

        // Table για να εμφανιστούν οι λογαριασμοί
        out.println("<div class=\"table100\">" +
                "                        <table style=\"position: relative; top: 40px;\">" +
                "                            <thead>" +
                "                                <tr class=\"table100-head\">" +
                "                                    <th class=\"column1\">Account ID</th>" +
                "                                    <th class=\"column2\">First name</th>" +
                "                                    <th class=\"column3\">Surname</th>" +
                "                                    <th class=\"column4\">Date of Birth</th>" +
                "                                    <th class=\"column5\">Email</th>" +
                "                                    <th class=\"column6\">Phone</th>" +
                "                                    <th class=\"column7\">Address</th>" +
                "                                    <th class=\"column8\">Country</th>" +
                "                                    <th class=\"column8\">Balance</th>" +
                "                                    <th class=\"column8\">Status</th>" +
                "                                    <th class=\"column8\">View</th>" +
                "                                </tr>" +
                "                            </thead>" +
                "                            <tbody>");


        for (Account account: accountList){

            out.println("<tr>"
                        + "<td class=\"column1\">" + account.getId() + "</td>"
                        + "<td class=\"column2\">" + account.getFirstName() + "</td>"
                        + "<td class=\"column3\">" + account.getSurname() + "</td>"
                        + "<td class=\"column4\">" + account.getBirthDate() + "</td>"
                        + "<td class=\"column5\">" + account.getEmail() + "</td>"
                        + "<td class=\"column6\">" + account.getPhone() + "</td>"
                        + "<td class=\"column7\">"+account.getAddress()+"</td>"
                        + "<td class=\"column8\">"+account.getCountry()+"</td>"
                        + "<td class=\"column8\">"+account.getBalance()+"</td>");

            // Αν ο λογαριασμός είναι ενεργοποιημένος τότε θα εμφανίζετε με πράσινο η κατάσταση του στον πίνακα. Αλλιώς αν είναι απενεργοποιημένος τότε να εμφανίζετε με κόκκινο
            if((account.getStatus()).equals("ACTIVE") ) {
                out.println("<td class=\"column8\" style=\"color: rgb(88, 194, 88);\"><b>" + account.getStatus() + "</b></td>");
            }
            if((account.getStatus()).equals("DEACTIVATED") ){
                out.println("<td class=\"column8\" style=\"color: rgb(194, 88, 88)\"> <b>" + account.getStatus() + "</b></td>");
            }
            // Όταν ο χρήστης επιλέξει να δεί με περισσότερες λεπτομέρειες τον χρήστη παίρνουμε το id του χρήστη και καλούμε τη ViewAccount servlet
            out.println( "<td class=\"column8\"><a href='viewAccount?id="+account.getId()+"'><b>View</b></a></td>\n"
                    +"</tr>");
        }
        out.println("</tbody></table></div>");
        out.println("</body></html>");
        out.close();

    }

}
