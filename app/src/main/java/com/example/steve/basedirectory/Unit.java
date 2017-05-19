package com.example.steve.basedirectory;


import java.io.Serializable;

// Used to hold a single unit
public class Unit implements Serializable {

    public int UnitId;
    public String UnitName;
    public String UnitTelephone;
    public int UnitPictureId;
    public Category UnitType;

    Unit(int id, String name, String phoneno, int pictureid, Category type)
    {
        this.UnitId = id;
        this.UnitName = name;
        this.UnitTelephone = phoneno;
        this.UnitPictureId = pictureid;
        this.UnitType = type;
    }

    Unit(String name, String phoneno, int pictureid, Category type)
    {
        this.UnitName = name;
        this.UnitTelephone = phoneno;
        this.UnitPictureId = pictureid;
        this.UnitType = type;
    }
}
