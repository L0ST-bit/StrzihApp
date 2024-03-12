package com.example.strzihapp;

public class TaskModel  {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean check;


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public TaskModel(int id, String name, String description, String imageUrl, boolean check) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.check = check;
    }
}


