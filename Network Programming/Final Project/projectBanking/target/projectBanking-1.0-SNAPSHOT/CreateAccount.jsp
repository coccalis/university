<!--
    ΧΡΗΣΤΟΣ ΚΟΚΚΑΛΗΣ
    ΑΜ:19390090
    Τμήμα: ΔΠΖ03
-->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=, initial-scale=1.0">
    <title>Create Account</title>

    <link rel="stylesheet" href="css/cssCreateAccount.css">

</head>
<body>

    <header class="header" style="font-weight: bold;">
        <div class="wrapper header-wrapper">
            <a href="index.jsp" class="brand" style="font-size: 25px; padding-left: 10px;"><span style="color: #3361af;">Uniwa</span> Bank</a>
            <nav class="nav">
                <ul class="nav-wrapper" style="font-weight: bold;">
                    <li class="nav-item"><a href="index.jsp" >Home</a></li>
                    <li class="nav-item"><a href="accountsTable">View All Accounts</a></li>
                    <li class="nav-item"><a href="About.jsp">About</a></li>
                    <li class="nav-item"><a href="CreateAccount.jsp" class="button-nav">Create Account</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <div class="div-middle">
        <h1>Create New Account</h1>
        <p>Please fill the requirements fields inorder to create a new account.</p>
        <!--
         Όταν ο χρήστης συμπληρώσει όλα τα inputs τότε θα πατήσει το κουμπί και εκτελεστεί η form action για να γίνει κλήση του createAccount-servlet.
         Ο χρήστης επίσης θα πρέπει να συμπληρώσει όλα τα πεδία γι'αυτό στο τέλος κάθε input υπάρχει το required.
        -->
        <form action="createAccount-servlet" method="post">
            <input class="inputs" type="text"  name="Firstname" placeholder="FirstName" required/><br>
            <input class="inputs" type="text"  name="Surname" placeholder="Surname" required/><br>
            <input class="inputs" type="text"  name="BirthDate" placeholder="Date of Birth (MM/DD/YYYY)" required/><br>
            <input class="inputs" type="text"  name="Email" placeholder="Email: example@example.com" required/><br>
            <input class="inputs" type="text"  name="Phone" placeholder="PhoneNumber" required/><br>
            <input class="inputs" type="text"  name="Address" placeholder="Address" required/><br>
            <input class="inputs" type="text"  name="Country" placeholder="Country" required/><br>
            <input class="inputs" type="text"  name="Balance" placeholder="Balance" required/><br>


            <input type="submit" value="Create" class="button" style= "margin-top: 30px;" >
        </form>
    </div>

</body>
</html>
