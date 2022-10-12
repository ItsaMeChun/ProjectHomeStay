<?php 
    include_once './connectDB.php';
    $nameCustomer = $_POST['nameCustomer'];
    $phoneCustomer = $_POST['phoneCustomer'];
    $emailCustomer = $_POST['emailCustomer'];
    $addressCustomer = $_POST['addressCustomer'];

    if(strlen($nameCustomer) > 0 && strlen($phoneCustomer) > 0 && strlen($emailCustomer) > 0 && strlen($addressCustomer) > 0){
        $queryStr =  "INSERT INTO info_buy(id,nameCustomer,phoneCustomer,emailCustomer,addressCustomer) VALUES(NULL,'$nameCustomer','$phoneCustomer','$emailCustomer','$addressCustomer')";

        if(mysqli_query($connect, $queryStr)){
            $id  = $connect->insert_id;
            echo $id;
        }else{
            echo "Failure!";
        }
    }else{
        echo "Data validate";
    }
?>