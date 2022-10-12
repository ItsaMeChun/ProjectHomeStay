package Model;

import java.io.Serializable;
import java.util.List;

import Model.product;

public class ListProduct implements Serializable {
    private List<product> listProducts;
    private String title;

    public ListProduct(List<product> listProducts, String title) {
        this.listProducts = listProducts;
        this.title = title;
    }

    public ListProduct() {
    }



    public List<product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<product> listProducts) {
        this.listProducts = listProducts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
