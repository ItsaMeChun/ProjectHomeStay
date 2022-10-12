package Category;


import homestay.homestay;

import java.util.List;

public class categoryA {
    private String nameListCategory;
    private List<homestay> categorys;

    public categoryA(String nameListCategory, List<homestay> categorys) {
        this.nameListCategory = nameListCategory;
        this.categorys = categorys;
    }

    public String getNameListCategory() {
        return nameListCategory;
    }

    public void setNameListCategory(String nameListCategory) {
        this.nameListCategory = nameListCategory;
    }

    public List<homestay> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<homestay> categorys) {
        this.categorys = categorys;
    }
}
