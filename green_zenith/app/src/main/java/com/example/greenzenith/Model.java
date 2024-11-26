package com.example.greenzenith;

import android.graphics.Bitmap;

public class Model{

    private String name, email;
    private Bitmap image;

    public Model(String name, String email, Bitmap image) {
        this.name = name;
        this.email = email;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
}