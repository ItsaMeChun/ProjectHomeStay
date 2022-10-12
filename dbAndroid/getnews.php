<?php 
  include_once './connectDB.php';

  $page = $_GET['page'];
  $space = 8;
  $limit = ($page - 1 ) * $space;

  $query =  "SELECT * FROM new LIMIT $limit, $space" ;


  $data = mysqli_query($connect, $query);
  $news = array();

  while($new = $data->fetch_assoc()){
    array_push($news, new news(
      $new['idNew'],
      $new['nameNew'],
      $new['descriptionNew'],
      $new['imgNew'],
      $new['authorNew']
    ));
  }

  echo json_encode($news);

  class news{
    function news($idNew, $nameNew, $descriptionNew,$imgNew, $authorNew){
      $this->idNew = $idNew;
      $this->nameNew = $nameNew;
      $this->descriptionNew = $descriptionNew;
      $this->imgNew = $imgNew;
      $this->authorNew = $authorNew;
    }
  }
?>