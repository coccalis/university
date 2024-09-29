
// ΤΜΗΜΑ: ΔΠΖ03
package com.cocc.projectbanking;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "transfer2", value = "/transfer2")
public class Transfer2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int statusSender = 0;
        int statusRecipient=0;

        //Παίρνουμε τα στοιχεία απο Transfer σελίδα και τα αποθηκεύουμε σε τοπικές μεταβλητές
        String sid = request.getParameter("AccountIDSend");
        int idSend = Integer.parseInt(sid);

        //get senders data
        Account sender = AccountsDAO.getAccountById(idSend);
        String senderBalance = sender.getBalance();

        double senderNewBalance = Double.parseDouble(senderBalance);

        // get transfer amount
        String transfer = request.getParameter("Transfer");
        double transferAmount = Double.parseDouble(transfer);

        // Ελέγχουμε αν το υπόλοιπο του χρήστη είναι επαρκή για να μπορεί να γίνει η μεταφορά. Αν δεν είναι επαρκή τότε εμφανίζεται η σελίδα InsufficientBalance.jsp
        if( (senderNewBalance-transferAmount) >= 0) {

            //get recipients id
            String sid2 = request.getParameter("AccountIDRecv");
            int idRecv = Integer.parseInt(sid2);

            Account recipient = AccountsDAO.getAccountById(idRecv);

            // Ελέγχουμε αν ο χρήστης υπάρχει. Αν τα στοιχεία είναι null σημαίνει πως δεν υπάρχει ο χρήστης στον πίνακα όποτε θα εμφανιστεί και η σελίδα AccountDoesntExists.jsp
            if(checkIfNull(idRecv)){

                // Εφόσον υπάρχει ο χρήστης παίρνουμε τα απαραίτητα στοιχεία για τη μεταφορά χρημάτων
                String balanceRecv = recipient.getBalance();
                double recipientNewBalance = Double.parseDouble(balanceRecv);
                String recipientStatus = recipient.getStatus();

                //Ελέγχουμε αν ο λογαριασμός είναι ενεργός ή απενεργοποιημένος. Αν είναι ενεργός γίνετε κανονικά η μεταφορά χρημάτων αλλιώς εμφανίζεται η σελίδα AccountDeactivated.jsp
                switch (recipientStatus){
                    case "ACTIVE":
                        //calculate balances
                        senderNewBalance -= transferAmount;
                        recipientNewBalance += transferAmount;

                        Account newSender = new Account();
                        newSender.setId(idSend);
                        newSender.setBalance(String.valueOf(senderNewBalance));


                        Account newRecipient = new Account();
                        newRecipient.setId(idRecv);
                        newRecipient.setBalance(String.valueOf(recipientNewBalance));

                        statusSender = AccountsDAO.updateBalance(newSender);
                        statusRecipient = AccountsDAO.updateBalance(newRecipient);

                        if(statusSender > 0 && statusRecipient > 0 ){ //Αν γίνει με επιτυχία τότε θα επιστρέψει τη σελίδα TransferSuccess.jsp αλλιώς αν προκύψει κάποιο σφάλμα θα επιστρέψει τη σελίδα TransferError.jdp
                            response.sendRedirect("TransferSuccess.jsp");
                        }else {
                            request.getRequestDispatcher("TransferError.jsp").include(request,response);
                        }
                        break;

                    case "DEACTIVATED":
                        request.getRequestDispatcher("AccountDeactivated.jsp").include(request,response);
                        break;
                }

            }else{
                request.getRequestDispatcher("AccountDoesntExists.jsp").include(request,response);
            }


        }else{
                request.getRequestDispatcher("InsufficientBalance.jsp").include(request,response);
        }

        out.close();
    }

    public boolean checkIfNull(int id){

        Account recipient = AccountsDAO.getAccountById(id);

        if(  (recipient.getBalance()) != null && (recipient.getStatus()) != null ){
            return true;
        }

        return false;
    }
}
