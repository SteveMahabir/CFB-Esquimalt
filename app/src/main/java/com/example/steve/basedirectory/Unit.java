package com.example.steve.basedirectory;


// Used to hold a single unit
public class Unit {

    public String UnitName;
    public String UnitTelephone;
    public int UnitPictureId;
    public Catagory UnitType;

    Unit(String name, String phoneno, int pictureid, Catagory type)
    {
        this.UnitName = name;
        this.UnitTelephone = phoneno;
        this.UnitPictureId = pictureid;
        this.UnitType = type;
    }
}
