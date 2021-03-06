package com.iot.android.lesson13_recyclerviewdemo;

/**
 * @author Nanrong Zeng
 * @version 1.0
 */
public class Fruit {
    private String name = null;
    private int imageId = 0;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
