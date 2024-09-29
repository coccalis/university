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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account doesn't exists</title>

    <link rel="stylesheet" href="css/cssGeneral.css">
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
        <img src="css/cancel.png" style="width: auto; height: 100px;" alt="error icon!">
        <h1>Transaction Error! Account not found</h1>
        <p>If you want to create this account please proceed to the <b>Create Account</b> page. <br>Otherwise  return to the homepage!</p>

        <a href="CreateAccount.jsp" class="button">Create Account</a>
        <a href="index.jsp" class="button">Home</a>


    </div>
</body>
</html>