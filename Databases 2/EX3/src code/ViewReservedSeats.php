
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserved Seats</title>
    <!-- link css-->
    <link rel="stylesheet" href="cssViewReservedSeats.css" type="text/css">

</head>
<body>


<?php
        // prepare, make connection with database
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
        <h1> View Reserved Seats</h1>
    </div>

    <div>
        <div class="select-div">
            <p>Choose of the flights to view the clients.</p>

            <?php
                $sql = "select flight_no from flights"; //prepare sql command to get the flight_no
                $result=mysqli_query($conn,$sql);
                $array=array(); //create array
                //store into the array the flight numbers
                while($row = mysqli_fetch_assoc($result)){
                    $array[]=$row;
                }
            ?>

            <form action="#" method="post">
                <select name="flightsNumber">
                    <?php
                        //add to the drop list all the flight numbers the database has.
                        foreach($array as $data){
                            echo "<option value=\"".$data['flight_no']. "\" >".$data['flight_no']. "</option> ";
                        }
                    ?>
                </select>
                <br>
                <input class="button" type="submit" name="submit" value="Done" style="margin-top: 30px;" >
            </form>
        </div>


        <table class="table100" style="position: relative; top: -80px;">
            <thead>
                <tr class="table100-head">
                    <th class="column1">Customer Number</th>
                    <th class="column2">Lastname</th>
                    <th class="column3">Firstname</th>
                    <th class="column4">Citizenship</th>
                    <th class="column5">Birth Date</th>
                    <th class="column6">Flight Number</th>


                </tr>
            </thead>
            <tbody>
                <?php
                    //when the submit button is pressed then
                    if(isset($_POST['submit'])){

                        $selected_val= $_POST['flightsNumber']; // insert into local variable the flight number from the drop list.

                        //prepare sql command
                        $sqlCommand = "select customers.* , reservations.flight_no
                        from customers, reservations, flights
                        where reservations.customer_no = customers.customer_no and reservations.flight_no = flights.flight_no and	reservations.flight_no =" .$selected_val. ";  ";
                        $res=mysqli_query($conn,$sqlCommand); //run sql command


                        if(mysqli_num_rows($res) > 0){

                            while($row = mysqli_fetch_assoc($res)){

                                //print the data
                                echo "<tr><td class=\"column1\">" .$row["customer_no"]. "</td>".
                                    "<td class=\"column2\">" .$row["customer_lastname"]. "</td>".
                                    "<td class=\"column3\">" .$row["customer_firstname"]. "</td>".
                                    "<td class=\"column4\">" .$row["citizenship"]. "</td>".
                                    "<td class=\"column5\">" .$row["birth_date"]. "</td>".
                                    "<td class=\"column6\">" .$row["flight_no"]. "</td>";
                            }
                        }
                        mysqli_close($conn); //close connection
                    }
                ?>
            </tbody>
        </table>
    </div>

    <div>
        <form method="post" action="home.html">
            <input class="button" type="submit" value="Home" style="position: absolute; right:0; bottom:0;">
        </form>
    </div>

</body>
</html>