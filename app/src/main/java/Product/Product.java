package Product;

import java.util.List;

import Popular.Popular;

public class Product {
    private List<Popular> list_popular;
    private String title_popularList;

    public Product(List<Popular> listPopular, String popular) {
        this.list_popular = listPopular;
        this.title_popularList = popular;
    }

    public List<Popular> getList_popular() {
        return list_popular;
    }

    public void setList_popular(List<Popular> list_popular) {
        this.list_popular = list_popular;
    }

    public String getTitle_popularList() {
        return title_popularList;
    }

    public void setTitle_popularList(String title_popularList) {
        this.title_popularList = title_popularList;
    }
}
