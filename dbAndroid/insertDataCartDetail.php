<?php 
    include_once './connectDB.php';

    $jsonData = $_POST['res_data'];
    $dataDecode = json_decode($jsonData, true);
    foreach ($dataDecode as $value) {
      $idCart = $value['idCart'];
      $idProduct = $value['idProduct'];
      $nameProduct = $value['nameProduct'];
      $priceProduct = $value['priceProduct'];
      $slProduct = $value['slProduct'];
      $queryStr = "INSERT INTO cart_details(id,idCart,idProduct,nameProduct,priceProduct,slProduct) VALUES(NULL,'$idCart','$idProduct','$nameProduct','$priceProduct','$slProduct')";
      
      $data = mysqli_query($connect, $queryStr);
    }

    if($data){
      echo "Success";
    }else{
      echo "Failed";
    }
?>