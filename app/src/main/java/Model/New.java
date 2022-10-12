package Model;

import java.io.Serializable;

public class New implements Serializable {
    private int idNew;
    private String nameView,descriptionNew,authorNew,imgNew;

    public New() {
    }

    public New(int idNew, String nameView, String descriptionNew, String authorNew, String imgNew) {
        this.idNew = idNew;
        this.nameView = nameView;
        this.descriptionNew = descriptionNew;
        this.authorNew = authorNew;
        this.imgNew = imgNew;
    }

    public New(String nameView, String descriptionNew, String authorNew, String imgNew) {
        this.nameView = nameView;
        this.descriptionNew = descriptionNew;
        this.authorNew = authorNew;
        this.imgNew = imgNew;
    }

    public int getIdNew() {
        return idNew;
    }

    public void setIdNew(int idNew) {
        this.idNew = idNew;
    }

    public String getNameView() {
        return nameView;
    }

    public void setNameView(String nameView) {
        this.nameView = nameView;
    }

    public String getDescriptionNew() {
        return descriptionNew;
    }

    public void setDescriptionNew(String descriptionNew) {
        this.descriptionNew = descriptionNew;
    }

    public String getAuthorNew() {
        return authorNew;
    }

    public void setAuthorNew(String authorNew) {
        this.authorNew = authorNew;
    }

    public String getImgNew() {
        return imgNew;
    }

    public void setImgNew(String imgNew) {
        this.imgNew = imgNew;
    }
}
