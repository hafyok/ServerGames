package com.example.servergames.Model.POJO;

import android.util.Log;

public class GamePOJO {
    private int id;
    private String name;
    private String background_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground_img() {
        if (background_image == null){
            Log.d("IMG url", "NO FOUND");
        }
        return background_image;
    }


    public void setBackground_img(String background_img) {
        this.background_image = background_img;
    }
}
