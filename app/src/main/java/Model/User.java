package Model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName,displayUrl,email,id;

    public User(String userName, String displayUrl, String email, String id) {
        this.userName = userName;
        this.displayUrl = displayUrl;
        this.email = email;
        this.id = id;
    }

    public User(String userName, String id) {
        this.userName = userName;
        this.id = id;
    }

    public User(String userName, String displayUrl, String id) {
        this.userName = userName;
        this.displayUrl = displayUrl;
        this.id = id;
    }

    public User(String userName){
        this.userName = userName;
    }

    public  User(){};

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
