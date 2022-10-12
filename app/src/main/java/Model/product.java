package Model;

import java.io.Serializable;

public class product implements Serializable {
    public int _id;
    public String nameProduct;
    public int price;
    public String description;
    public String imgProduct;
    public int _idCategory;
    public String location;

    public  product(){};

    public product(int _id, String nameProduct, int price, String description, String imgProduct, int _idCategory, String location) {
        this._id = _id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.description = description;
        this.imgProduct = imgProduct;
        this._idCategory = _idCategory;
        this.location = location;
    }

    public product(String nameProduct, int price, String description, String imgProduct, int _idCategory, String location) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.description = description;
        this.imgProduct = imgProduct;
        this._idCategory = _idCategory;
        this.location = location;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public int get_idCategory() {
        return _idCategory;
    }

    public void set_idCategory(int _idCategory) {
        this._idCategory = _idCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
