package com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.Model;

public class User  {
    private String id;
    private String username;
    private String imageURL;
    private String status;
    private String search;
    private String type_user;

    public User(String id, String username, String imageURL, String status, String search, String type_user) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status= status;
        this.search= search;
        this.type_user = type_user;
    }

    public User() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageUrl) {
        this.imageURL = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }
}
