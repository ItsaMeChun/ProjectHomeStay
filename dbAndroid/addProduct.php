<?php 
  include_once './connectDB.php';
  $nameProduct = $_POST['nameProduct'];
  $price = $_POST['price'];
  $description = $_POST['description'];
  $imgProduct = $_POST['imgProduct'];
  $location = $_POST['location'];
  $_idCategory = $_POST['_idCategory'];


  $queryStr = "INSERT INTO product(_id,nameProduct,price,description,imgProduct,_idCategory,location) VALUES(NULL, '$nameProduct', '$price','$description','$imgProduct','$_idCategory','$location')";



  if(strlen($nameProduct) && strlen($price) && strlen($description) && strlen($imgProduct) && strlen($_idCategory) && strlen($location)){
    $addNew = mysqli_query($connect, $queryStr);
    if($addNew != null) {
       echo "success";
    }else{
       echo "Failure";
    }
  }
?>