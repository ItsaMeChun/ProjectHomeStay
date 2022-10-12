<?php 
  include_once './connectDB.php';
  $username = $_POST['username'];
  $email = $_POST['email'];
  $password = md5($_POST['password']);


  $queryStr = "INSERT INTO user(id,userName,email,passswordHash) VALUES(NULL, '$username','$email','$password')";


  $checkEmail = "SELECT * FROM user WHERE email = '$email' LIMIT 1";
  $check = mysqli_query($connect, $checkEmail);
  if(!$check || mysqli_num_rows($check) == 0) {
    $addUser = mysqli_query($connect, $queryStr);

    if($addUser != null) {
       echo "success";
    }else{
       echo "Failure";
    }
  }else{
    echo "already";
  }

?>