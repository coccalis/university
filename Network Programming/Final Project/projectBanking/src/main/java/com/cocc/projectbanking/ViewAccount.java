
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "viewAccount", value = "/viewAccount")
public class ViewAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String sid = request.getParameter("id"); //παίρνουμε το id του χρήστη απο το accounts table
        String id =sid;
        Account account = AccountsDAO.getAccountById(Integer.parseInt(id)); //παίρνουμε τα στοιχεία του χρήστη απο τον πίνακα

        // Δηλώνουμε το head της σελίδας
        out.println("<!DOCTYPE html>" +
                "                <html lang=\"en\">" +
                "                <head>" +
                "                    <meta charset=\"UTF-8\">" +
                "                    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "                    <title>Account</title>" +
                "                    <link rel=\"stylesheet\" href=\"css/cssViewAccount.css\">" +
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


        // Div για να εμφανίσει τα στοιχεία του χρήστη
        out.println("<div style=\"float: left;\">"
                        + "<div class=\"div-info\">"
                            + "<h2>"+ account.getFirstName() +" "+ account.getSurname() +"</h2>"
                            + "<p style=\"color: gray;\">"
                            + "<b>Account ID: </b> "+account.getId()+" <br>"
                            + "<b>BirthDate:</b> "+account.getBirthDate()+" <br>"
                            + "<b>Phone: </b>"+account.getPhone() +" <br>"
                            + "<b>Email: </b> "+account.getEmail() +" <br>"
                            + "<b>Address: </b>"+account.getAddress()+" <br>"
                            + "<b>Country: </b>"+account.getCountry()+" <br><br>");

                            // Αν ο λογαριασμός είναι ενεργοποιημένος τότε θα εμφανίζετε με πράσινο η κατάσταση του στον πίνακα. Αλλιώς αν είναι απενεργοποιημένος τότε να εμφανίζετε με κόκκινο
                            if((account.getStatus()).equals("ACTIVE") ) {
                                out.println("<b>Accounts Status: <span style=\"color: rgb(88, 194, 88);\">" + account.getStatus() + "</span></b> <br>");
                            }
                            if((account.getStatus()).equals("DEACTIVATED") ){
                                out.println("<b>Accounts Status: <span style=\"color: rgb(194, 88, 88)\">" + account.getStatus() + "</span></b> <br>");
                            }
        out.println( "</p>"
                        + "</div>"
                        + "<div class=\"div-info\">"
                            + "<h2>Balance</h2>"
                            + "<p style=\"color: gray; font-size:25px;\"><b>"+account.getBalance()+" $</b></p>"
                        + "</div>"
                    + "</div>");

        // Αν ο λογαριασμός είναι ενεργοποιημένος τότε θα εμφανίζονται κανονικά τα κουμπιά transfer, withdraw και deposit όπου θα μπορούν να κάνουν κανονικά τις λειτουργίες τους.
        // Επίσης, θα εμφανίζετε το κουμπί Deactivate και το κουμπί Activate δεν υπάρχει.
        if((account.getStatus()).equals("ACTIVE") ){
            out.println(  "<div style=\"float: left;\" class=\"div-buttons\"> "
                        + "<a  class=\"buttons\" href='transfer?id=" + account.getId() + "'>Transfer</a><br><br>"
                        + "<a  class=\"buttons\" href='withdraw?id=" + account.getId() + "'>Withdraw</a><br><br>"
                        + "<a class=\"buttons\" href='deposit?id=" + account.getId() + "'>Deposit</a> <br><br>");

            out.println( "<a class=\"buttons\" style=\"background-color: rgb(124, 124, 124); margin-top: 200px; width: auto;\" href='deactivate?id=" + account.getId() + "'>Deactivate</a>");

        }
        // Αν ο λογαριασμός είναι απενεργοποιημένος τότε δε θα εμφανίζονται κανονικά τα κουμπιά transfer, withdraw και deposit και ο χρήστης δε θα μπορεί να εκτελέσει τις λειτουργίες που θέλει μέχρι να ενεργοποιήσει τον λογαριασμό.
        // Επίσης, θα εμφανίζετε το κουμπί Activate και το κουμπί Deactivate δεν υπάρχει.
        if((account.getStatus()).equals("DEACTIVATED") ){
            out.println(  "<div style=\"float: left;\" class=\"div-buttons\"> "
                    + "<a  class=\"inactiveButton\" href='transfer?id=" + account.getId() + "'>Transfer</a><br><br>"
                    + "<a  class=\"inactiveButton\" href='withdraw?id=" + account.getId() + "'>Withdraw</a><br><br>"
                    + "<a class=\"inactiveButton\" href='deposit?id=" + account.getId() + "'>Deposit</a> <br><br>");
            out.println( "<a class=\"buttons\" style=\"background-color: rgb(88, 194, 88); width: auto; margin-top: 250px; margin-right: 10px;\" href='activate?id=" + account.getId() + "'>Activate</a>");
        }


        out.println( "<a class=\"buttons\" style=\"background-color: rgb(194, 88, 88); width: auto;\" href='deleteAccount?id=" + account.getId() + "'>Delete</a>"
                    + "</div>"
                    +"</body></html>");

        out.close();

    }

}
