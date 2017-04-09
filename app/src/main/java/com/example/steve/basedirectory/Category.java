package com.example.steve.basedirectory;

// Used to hold a single Catagory
public class Category {
    public int CategoryId;
    public String CategoryType;
    public int CategoryPicutreId;

    Category(int id, String type, int pictureid){
        this.CategoryId = id;
        this.CategoryType = type;
        this.CategoryPicutreId = pictureid;
    }

    Category(String type, int pictureid){
        this.CategoryType = type;
        this.CategoryPicutreId = pictureid;
    }

}
