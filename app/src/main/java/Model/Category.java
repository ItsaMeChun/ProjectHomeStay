package Model;

import java.io.Serializable;

public class Category implements Serializable {
    private int _id;
    private String nameCategory;
    private String imgCategory;

    public Category(int _id, String nameCategory) {
        this._id = _id;
        this.nameCategory = nameCategory;
    }

    public Category(int _id, String nameCategory, String imgCategory) {
        this._id = _id;
        this.nameCategory = nameCategory;
        this.imgCategory = imgCategory;
    }

    public Category() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(String imgCategory) {
        this.imgCategory = imgCategory;
    }
}
