<?php 
  include_once './connectDB.php';
  $nameNew = $_POST['nameNew'];
  $descriptionNew = $_POST['descriptionNew'];
  $authorNew = $_POST['authorNew'];
  $imgNew = $_POST['imgNew'];

  $queryStr = "INSERT INTO new(idNew,nameNew,descriptionNew,imgNew,authorNew) VALUES(NULL, '$nameNew', '$descriptionNew','$imgNew','$authorNew')";



  if(strlen($nameNew) && strlen($descriptionNew) && strlen($imgNew) && strlen($authorNew)){
    $addNew = mysqli_query($connect, $queryStr);
    if($addNew != null) {
       echo "success";
    }else{
       echo "Failure";
    }
  }
?>