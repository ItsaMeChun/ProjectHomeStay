<?php 
  include_once './connectDB.php';

  $email = $_POST['username'];
  $password = md5($_POST['password']);

  $checkEmail = "SELECT * FROM user WHERE email = '$email' LIMIT 1";

  $check = mysqli_query($connect, $checkEmail);
  if(!$check || mysqli_num_rows($check) == 0) {
    echo "404";
  }else{
    $queryStr = "SELECT * FROM user WHERE email = '$email' AND passswordHash = '$password' LIMIT 1";
    $User = mysqli_query($connect, $queryStr);

    if(!$User || mysqli_num_rows($User) == 0) {
       echo "Failure";
    }else{
       echo "success";
    }
  }


?>