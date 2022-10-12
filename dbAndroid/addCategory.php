<?php 
  include_once './connectDB.php';
  $nameCategory = $_POST['nameCategory'];
  $imgCategory = $_POST['imgCategory'];

  $queryStr = "INSERT INTO category(_id,nameCategory,imgCategory) VALUES(NULL, '$nameCategory', '$imgCategory')";



  if(strlen($nameCategory) && strlen($imgCategory)){
    $addCate = mysqli_query($connect, $queryStr);
    if($addCate != null) {
      return echo "success";
    }else{
      return echo "Failure";
    }
  }
?>