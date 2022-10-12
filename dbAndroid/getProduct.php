<?php 
  include_once './connectDB.php';
  $queryStr = "SELECT * FROM product ORDER BY _id DESC LIMIT 8";

  $getListProduct = mysqli_query($connect,$queryStr);
  $listProduct = array();
  while($product = $getListProduct->fetch_assoc()){
    array_push($listProduct, new Product(
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