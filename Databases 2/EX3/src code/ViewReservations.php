


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservations</title>
    <!-- link css -->
    <link rel="stylesheet" href="cssViewReservations.css" type="text/css">

</head>
<body>

    <?php
            //prepare and make the connection with the database
            $servername="localhost";
            $username="kokkalis";
            $password = "1234";
            $dbname="chartered_airlines_bd";

            $conn = mysqli_connect($servername,$username,$password,$dbname);
            if(!$conn){
                die("Connection failed: " . mysqli_connect_error());
            }

    ?>
    <div class="center">
        <h1>Reservations</h1>
        <p>Enter the customers number and the date to find his total number of reservations and the total price he has to pay.</p>
        <form action="#" method="get">
            <input class="inputs" type="text" name="customerNo" placeholder="Customers Number" >
            <input class="inputs" type="text" name="resDate" placeholder="YYYY-MM-DD">
            <br>
            <input class="button" type="submit" name="submit" value="Search" style="margin-top: 30px;" >

        </form>


        <br><br><br><br>
        <?php

            //if the submit button is pressed then continue
            if(isset($_GET['submit'])){

                //create local variables to store the data from the inputs
                $number = $_GET["customerNo"];
                $date = $_GET["resDate"];

                //prepare the sql command
                $sql = "select count(res_no) as res_no, sum(price) as payment from reservations
                where customer_no =' ".$number."' and MONTH(res_date) = MONTH('" .$date. "');";

                $res=mysqli_query($conn,$sql); //run the sql command

                if(mysqli_num_rows($res) > 0){

                    while($row = mysqli_fetch_assoc($res)){

                        //print the results
                        echo "<label><b> For the customer with the number: " .$number. "</b></label><br>".
                        "<label><b> Amount of Reservations:</b> </label>".
                        "<input class=\"inputs\" type=\"text\" name=\"reservationResults\" value=" .$row["res_no"]. " style=\"margin-left: 20px;\" readonly>".
                        " <br> <label><b> Total Amount to Pay :</b> </label>".
                        " <input class=\"inputs\" type=\"text\" name=\"totalCost\" value=' ".$row['payment']." €' style=\"margin-left: 42px;\" readonly>";

                    }
                }else{
                    echo "<label><b> Customer with the number: " .$number. " doesn't have any reservations for this month.</b></label><br>";
                }
            }

            mysqli_close($conn); //close connection

        ?>
    </div>

    <div >
        <form method="post" action="home.html">
            <input class="button" type="submit" value="Home" style="position: absolute; right:0; bottom:0;">
        </form>

    </div>


</body>
</html>