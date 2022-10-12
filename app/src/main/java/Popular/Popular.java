package Popular;

import java.io.Serializable;

public class Popular implements Serializable {
    private String title;
    private String desc;
    private int ResourceId;
    private  String location;
    private int Price;
    private String categoryName;

    public Popular() {
    }

    public Popular(String title, String desc, int resourceId, String location, int price, String categoryName) {
        this.title = title;
        this.desc = desc;
        ResourceId = resourceId;
        this.location = location;
        Price = price;
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResourceId() {
        return ResourceId;
    }

    public void setResourceId(int resourceId) {
        ResourceId = resourceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
