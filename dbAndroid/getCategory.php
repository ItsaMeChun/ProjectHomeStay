<?php 
  include_once './connectDB.php';
  $query =  "SELECT * FROM category";

  $data = mysqli_query($connect, $query);
  $listCategory = array();

  while($category = $data->fetch_assoc()){
    array_push($listCategory, new category(
      $category['_id'],
      $category['nameCategory'],
      $category['imgCategory']
    ));
  }

  echo json_encode($listCategory);

  class category{
    function category($_id, $nameCategory, $imgCategory){
      $this->_id = $_id;
      $this->nameCategory = $nameCategory;
      $this->imgCategory = $imgCategory;
    }
  }
?>