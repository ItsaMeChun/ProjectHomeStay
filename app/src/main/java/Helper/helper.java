package Helper;

public class helper {
    public static String ip = "192.168.1.206";
    public static String localhost = "http://"+ip+":8080/dbandroid/";
    public static String getCategory = localhost+  "getCategory.php";
    public static String getProduct =localhost+  "getProduct.php";
    public static String getGetProductById =localhost+  "getProductById.php?page=";
    public static String getAllProduct = localhost+  "getAllProduct.php?page=";
    public static String postInformation = localhost+  "infomationCustomer.php";
    public static String insertDataCartDetail =  localhost+ "insertDataCartDetail.php";
    public static String registerURL =localhost+ "register.php";
    public static String loginURL = localhost+  "login.php";
    public static String getAllNews = localhost+  "getNews.php?page=";
    public static String addCategory = localhost+  "addCategory.php";
    public static String addNews = localhost+  "addNew.php";
    public static String addProduct = localhost+  "addProduct.php";
    public static String removeProduct = localhost+  "removeProductById.php";
    public static String updateProduct = localhost+  "updateProductById.php";
    public static String removeCategory = localhost+  "removeCategoryById.php"; //sqlite
    public static String updateCategory = localhost+  "updateCategoryById.php";
    public static String removeNews = localhost+  "removeNewsById.php";
    public static String updateNews = localhost+  "updateNewsById.php";
    public static String addCatertLogin = localhost+  "addProductLogin.php";
}