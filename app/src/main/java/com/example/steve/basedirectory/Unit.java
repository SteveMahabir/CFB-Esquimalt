package com.example.steve.basedirectory;


// Used to hold a single unit
public class Unit {

    public String UnitName;
    public String UnitTelephone;
    public int UnitPictureId;
    Unit(String name, String phoneno, int pictureid)
    {
        this.UnitName = name;
        this.UnitTelephone = phoneno;
        this.UnitPictureId = pictureid;
    }
}
