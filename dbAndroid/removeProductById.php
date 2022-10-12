<?php 
  include_once './connectDB.php';
  $id = $_POST['_id'];


  $queryStr = "DELETE FROM product WHERE _id = $id";

    $addNew = mysqli_query($connect, $queryStr);
    if($addNew != null) {
       echo "success";
    }else{
       echo "Failure";
    }
?>