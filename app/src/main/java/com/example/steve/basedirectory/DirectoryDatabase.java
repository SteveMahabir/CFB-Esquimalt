package com.example.steve.basedirectory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DirectoryDatabase {

    static final String KEY_ROWID = "_id";
    static final String KEY_UNIT = "unit";
    static final String KEY_SUB_UNIT = "subunit";
    static final String KEY_PHONE = "phone";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "GlobalDatabase";
    static final String DATABASE_TABLE = "Units";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE =
            "create table units (_id integer primary key autoincrement, "
                    + "unit text not null, subunit text not null, phone text not null);";

    static final String DATABASE_DESTROY =
            "drop table units;";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DirectoryDatabase(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_DESTROY);
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DirectoryDatabase open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertUnit(String unit, String subunit, String phone)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_UNIT, unit);
        initialValues.put(KEY_SUB_UNIT, subunit);
        initialValues.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the units---
    public Cursor getAllUnits()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_UNIT,
                KEY_SUB_UNIT, KEY_PHONE}, null, null, null, null, null);
    }

    //---retrieves all the subunits---
    public Cursor getAllSubUnits()
    {
        return db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_UNIT,
                KEY_SUB_UNIT, KEY_PHONE}, null, null, KEY_SUB_UNIT, null, null, null);

    }

    //--- retrieves all the units based on SubUnits ---
    public Cursor getAllSubunitsByUnits(String units)
    {
        try {

            //

            Cursor mCursor =
                    db.query(DATABASE_TABLE, new String[]{KEY_ROWID,
                                    KEY_UNIT, KEY_SUB_UNIT, KEY_PHONE}, KEY_SUB_UNIT + "= '" + units + "'", null,
                            null, null, null, null);

            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }
        catch(Exception e){
            Toast.makeText(this.context, "Error: " + e.toString(), Toast.LENGTH_LONG);
        }
        return null;
    }

    //---retrieves a particular contact---
    public Cursor getUnit(String unit) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_UNIT, KEY_SUB_UNIT, KEY_PHONE}, KEY_UNIT + "=" + unit, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateUnit(long rowId, String unit, String subunit, String phone)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_UNIT, unit);
        args.put(KEY_SUB_UNIT, subunit);
        args.put(KEY_PHONE, phone);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


    /*
                Global Directory List
     */

    public void Populate(){
        open();

        db.execSQL("DELETE FROM units");

        Directory dir = new Directory();

        for (String[] yjetty : dir.YJetty ) {
            this.insertUnit(yjetty[0], "Y-Jetty", yjetty[1] );
        }

        for (String[] health : dir.HealthCare ) {
            this.insertUnit(health[0], "Health Care", health[1] );
        }

        for (String[] base : dir.BaseServices ) {
            this.insertUnit(base[0], "Base Services", base[1] );
        }

        close();
    }

}
