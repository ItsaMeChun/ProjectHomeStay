<?php 
  include_once './connectDB.php';

  $page = $_GET['page'];
  $idCate = $_POST['_id'];
  $space = 5;
  $limit = ($page - 1 ) * $space;

  $listProduct = array();

  $queryStr = "SELECT * FROM product WHERE _idCategory = $idCate LIMIT $limit, $space ";

  $data = mysqli_query($connect, $queryStr);

  while($product = $data->fetch_assoc()){
    array_push($listProduct, new product(
      $product['_id'],
      $product['nameProduct'],
      $product['price'],
      $product['description'],
      $product['imgProduct'],
      $product['_idCategory'],
      $product['location']
    ));
  }

  echo json_encode($listProduct);

  class product{
    function product($_id,$nameProduct,$price , $description,$imgProduct,$_idCategory,$location){
      $this->_id = $_id;
      $this->nameProduct = $nameProduct;
      $this->price = $price;
      $this->description = $description;
      $this->imgProduct = $imgProduct;
      $this->_idCategory = $_idCategory;
      $this->location = $location;
    }
  }
?>