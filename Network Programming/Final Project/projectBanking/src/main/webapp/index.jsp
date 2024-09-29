<!--

    Τμήμα: ΔΠΖ03
-->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <link rel="stylesheet" href="css/cssHome.css">
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
        <h2 style="padding-left: 40px ;">Welcome to</h2>
        <h1><span style="color: #3361af;">Uniwa</span> Bank</h1>
        <div class="div-middle-button">
            <a href="accountsTable" class="button-middle">View All Accounts</a>
        </div>
        <div style="float: right" >
            <img src="css/creditcards.png" width="900" height="500" style="position: relative; top: -250px; padding-right: 50px;">
        </div>
    </div>


</body>
</html>