<?php 
  include_once './connectDB.php';
  $nameProduct = $_POST['nameProduct'];
  $price = $_POST['price'];
  $description = $_POST['description'];
  $imgProduct = $_POST['imgProduct'];
  $location = $_POST['location'];
  $_idCategory = $_POST['_idCategory'];
  $_id = $_POST['_id'];


  $queryStr = "UPDATE product SET nameProduct = '$nameProduct' , location = '$location' , imgProduct = '$imgProduct' , description = '$description' , price = '$price', imgProduct = '$imgProduct', _idCategory = '$_idCategory' WHERE _id = '$_id'" ;


    $updateProduct = mysqli_query($connect, $queryStr);
    if($updateProduct != null) {
       echo "success";
    }else{
       echo "Failure";
    }

?>