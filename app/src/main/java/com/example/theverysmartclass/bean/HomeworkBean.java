package com.example.theverysmartclass.bean;

public class HomeworkBean {
    private String name;

    private int imageId;

    public HomeworkBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
