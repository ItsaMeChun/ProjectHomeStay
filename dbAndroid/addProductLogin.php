<?php 
  include_once './connectDB.php';

    $jsonData = $_POST['res_data'];
    $dataDecode = json_decode($jsonData, true);

    foreach ($dataDecode as $value) {
      $gmail = $value['userName'];
      $idProduct = $value['idProduct'];
      $nameProduct = $value['nameProduct'];
      $priceProduct = $value['price'];
      $slProduct = $value['slProduct'];

      $queryStr = "INSERT INTO cart_login(id,email,idProduct,nameProduct,price,slProduct) VALUES(NULL,'$gmail','$idProduct','$nameProduct','$priceProduct','$slProduct')";

      $data = mysqli_query($connect, $queryStr);
    } 
     if($data){
      echo "Success";
    }else{
      echo "Failed";
    }
?>