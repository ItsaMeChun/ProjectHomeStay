package Model;

import java.io.Serializable;

public class Cart implements Serializable {
        private int _idProduct;
    private String nameProduct;
    private int price;
    private String imgProduct;
    private int number;
    private String position;
    public Cart(){};

    public Cart(String nameProduct, int price, String imgProduct, int number,String position) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.imgProduct = imgProduct;
        this.number = number;
        this.position = position;
    }

    public Cart(int _idProduct, String nameProduct, int price, String imgProduct, int number,String position) {
        this._idProduct = _idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.imgProduct = imgProduct;
        this.number = number;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int get_idProduct() {
        return _idProduct;
    }

    public void set_idProduct(int _idProduct) {
        this._idProduct = _idProduct;
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

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
