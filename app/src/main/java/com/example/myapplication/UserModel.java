package com.example.myapplication;

public class UserModel {
    private String id;
    private String email;
    private String nume;

    public UserModel(String id,String email, String nume) {
        this.id = id;
        this.email = email;
        this.nume = nume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
