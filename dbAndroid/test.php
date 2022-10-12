<?php 
  include_once './connectDB.php';
  
  $email = "binhbinh@gmail.co ";
  echo date("Y-m-d h:i:sa");

  $checkEmail = "SELECT * FROM user WHERE email = '$email' ";
  $check = mysqli_query($connect, $checkEmail);   
  if(!$check || mysqli_num_rows($check) == 0){
    echo "User";
  }else{
    echo "User fail";
  }

?>  

