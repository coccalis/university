
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flights Table</title>

    <!-- Link css  -->
    <link rel="stylesheet" href="cssFlightsTable.css" type="text/css">

</head>
<style>

    .center-div {
        padding: 50px 0 ;
        text-align: center;
        color: #d4e5f0;
    }

</style>
<body>

    <div class="center-div">
        <h1>Flights Table</h1>
    </div>

    <div>
        <table class="table100" style="position: relative; top: -0px;">
                    <thead>
                        <tr class="table100-head">
                            <th class="column1">Flight Number</th>
                            <th class="column2">Departure</th>
                            <th class="column3">Arrival</th>
                            <th class="column4">Seats</th>
                            <th class="column5">Free Seats</th>
                            <th class="column6">Type Of Flight</th>
                            <th class="column7">Departure Date</th>

                        </tr>
                    </thead>
                    <tbody>

                    <?php
                        // prepare connection
                        $servername="localhost";
                        $username="kokkalis";
                        $password = "1234";
                        $dbname="chartered_airlines_bd";

                        $conn = mysqli_connect($servername,$username,$password,$dbname); //make conncetion with database
                        if(!$conn){ //if it fails end connection.
                            die("Connection failed: " . mysqli_connect_error());
                        }


                        $sql="select * from flights"; //prepare sql command
                        $result = mysqli_query($conn, $sql); //run sql command

                        if(mysqli_num_rows($result) > 0){ //if it is greater than zero keep going

                            while($row = mysqli_fetch_assoc($result)){ //run while loop until it finishes the select

                                //print data
                                echo "<tr><td class=\"column1\">" .$row["flight_no"]. "</td>".
                                    "<td class=\"column2\">" .$row["departure"]. "</td>".
                                    "<td class=\"column3\">" .$row["arrival"]. "</td>".
                                    "<td class=\"column4\">" .$row["seats"]. "</td>".
                                    "<td class=\"column5\">" .$row["free_seats"]. "</td>".
                                    "<td class=\"column6\">" .$row["type_of_flight"]."</td>".
                                    "<td class=\"column7\">" .$row["depart_date"]. "</td>";

                            }

                        }

                        mysqli_close($conn); //close connection


                    ?>
                    </tbody>
        </table>

    </div>

    <div >
        <form method="post" action="home.html">
            <input class="button" type="submit" value="Home" style="position: absolute; right:    0; bottom:   0;">
        </form>
    </div>

</body>
</html>