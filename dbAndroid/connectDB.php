<?php 
  include './config.php';
?>

<?php 
     $host = DB_HOST;
     $user = DB_USER;
     $pass = DB_PASS;
     $dbname = DB_NAME;

     $connect = mysqli_connect($host, $user, $pass, $dbname);

     mysqli_query($connect,"SET NAMES 'utf8'");
?>
