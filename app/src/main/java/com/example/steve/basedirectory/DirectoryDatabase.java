package com.example.steve.basedirectory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class DirectoryDatabase extends SQLiteOpenHelper implements Serializable {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                                                                                 */
    /*                                          Database Setup                                         */
    /*                                                                                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DirectoryDatabase";

    // Table Names
    private static final String TABLE_UNITS = "units";
    private static final String TABLE_CATEGORIES = "categories";

    // Common column names
    private static final String KEY_ID = "id";

    // Units Table - column names
    private static final String KEY_UNIT = "unit";
    private static final String KEY_PHONE_NO = "phoneno";
    private static final String KEY_PICTURE_ID = "pictureid";
    private static final String KEY_UNIT_CATEGORY = "unitcategoryid";


    // Categories Table - column names
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CATEGORY_PICTURE_ID = "pictureid";

    // Unit Create Statement
    private static final String CREATE_TABLE_UNITS = "CREATE TABLE "
            + TABLE_UNITS + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_UNIT + " TEXT,"
            + KEY_PHONE_NO + " TEXT,"
            + KEY_PICTURE_ID + " INTEGER, "
            + KEY_UNIT_CATEGORY + " INTEGER" + ")";

    // Category Create Statement
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE "
            + TABLE_CATEGORIES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CATEGORY + " TEXT,"
            + KEY_CATEGORY_PICTURE_ID + " INTEGER" + ")";


    // Constructor and Courtesy Call
    public DirectoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_UNITS);
        db.execSQL(CREATE_TABLE_CATEGORIES);
    }

    public void closeDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);

        // create new tables
        onCreate(db);
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                                                                                 */
    /*                                          Units CRUD                                             */
    /*                                                                                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    // Create a Unit
    public long insertUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNIT, unit.UnitName);
        values.put(KEY_PHONE_NO, unit.UnitTelephone);
        values.put(KEY_PICTURE_ID, unit.UnitPictureId);
        values.put(KEY_UNIT_CATEGORY, unit.UnitType.CategoryId);

        // insert row
        long unit_id = db.insert(TABLE_UNITS, null, values);

        return unit_id;
    }

    public Unit getUnitById(long unit_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_UNITS + " WHERE "
                + KEY_ID + " = " + unit_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Category cat = getCategoryById(
                c.getInt(c.getColumnIndex(KEY_UNIT_CATEGORY))
        );

        Unit unit = new Unit(
                c.getColumnIndex(KEY_ID),
                c.getString(c.getColumnIndex(KEY_UNIT)),
                c.getString(c.getColumnIndex(KEY_PHONE_NO)),
                c.getColumnIndex(KEY_PICTURE_ID),
                cat
        );

        return unit;
    }

    public Unit getUnitByName(String unit_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_UNITS + " WHERE "
                + KEY_UNIT + " = " + unit_name;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Category cat = getCategoryById(
                c.getInt(c.getColumnIndex(KEY_UNIT_CATEGORY))
        );

        Unit unit = new Unit(
                c.getColumnIndex(KEY_ID),
                c.getString(c.getColumnIndex(KEY_UNIT)),
                c.getString(c.getColumnIndex(KEY_PHONE_NO)),
                c.getColumnIndex(KEY_PICTURE_ID),
                cat
        );

        return unit;
    }

    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> units = new ArrayList<Unit>();
        String selectQuery = "SELECT  * FROM " + TABLE_UNITS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                Category category = getCategoryById(c.getColumnIndex(KEY_CATEGORY));

                Unit unit = new Unit(
                        c.getInt(c.getColumnIndex(KEY_ID)),
                        c.getString(c.getColumnIndex(KEY_UNIT)),
                        c.getString(c.getColumnIndex(KEY_PHONE_NO)),
                        c.getInt(c.getColumnIndex(KEY_PICTURE_ID)),
                        category
                );

                units.add(unit);
            } while (c.moveToNext());
        }

        return units;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                                                                                 */
    /*                                  Catagories CRUD                                                */
    /*                                                                                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */


    public long insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category.CategoryType);
        values.put(KEY_CATEGORY_PICTURE_ID, category.CategoryPicutreId);

        // insert row
        long category_id = db.insert(TABLE_CATEGORIES, null, values);

        return category_id;
    }

    public Category getCategoryById(long category_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES + " WHERE "
                + KEY_ID + " = " + category_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Category category = new Category(
                c.getInt(c.getColumnIndex(KEY_ID)),
                c.getString(c.getColumnIndex(KEY_CATEGORY)),
                c.getInt(c.getColumnIndex(KEY_CATEGORY_PICTURE_ID))
                );

        return category;
    }

    public Category getCategoryByName(String category_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES + " WHERE "
                + KEY_CATEGORY + " = '" + category_name + "'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Category category = new Category(
                c.getInt(c.getColumnIndex(KEY_ID)),
                c.getString(c.getColumnIndex(KEY_CATEGORY)),
                c.getInt(c.getColumnIndex(KEY_CATEGORY_PICTURE_ID))
        );

        return category;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category cat = new Category(
                        c.getInt(c.getColumnIndex(KEY_ID)),
                        c.getString(c.getColumnIndex(KEY_CATEGORY)),
                        c.getInt(c.getColumnIndex(KEY_CATEGORY_PICTURE_ID))
                );
                // adding to tags list
                categories.add(cat);
            } while (c.moveToNext());
        }
        return categories;
    }

    public ArrayList<Unit> getAllUnitsByCategory(String category_name) {
        ArrayList<Unit> units = new ArrayList<Unit>();

        Category category = getCategoryByName(category_name);

        String selectQuery = "SELECT * FROM " + TABLE_UNITS +
                " WHERE " + KEY_UNIT_CATEGORY + " = " + category.CategoryId;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                Unit unit = new Unit(
                        c.getInt(c.getColumnIndex(KEY_ID)),
                        c.getString(c.getColumnIndex(KEY_UNIT)),
                        c.getString(c.getColumnIndex(KEY_PHONE_NO)),
                        c.getInt(c.getColumnIndex(KEY_PICTURE_ID)),
                        category
                );

                units.add(unit);
            } while (c.moveToNext());
        }

        return units;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                                                                                 */
    /*                                  Populate the Database                                          */
    /*                                                                                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    public void Populate(){
        SQLiteDatabase db = this.getWritableDatabase();

        onUpgrade(db, 1,1);

        Directory dir = new Directory();

        Category YJetty = new Category("Y Jetty", R.drawable.mcdvs);
        Category HealthCare = new Category("Health Care", R.drawable.medical_service);
        Category BaseServices = new Category("Base Services", R.drawable.cfb_esquimalt);

        YJetty.CategoryId = (int)insertCategory(YJetty);
        HealthCare.CategoryId = (int)insertCategory(HealthCare);
        BaseServices.CategoryId = (int)insertCategory(BaseServices);

        dir.YJetty = YJetty;
        dir.HealthCare = HealthCare;
        dir.BaseServices = BaseServices;

        dir.SetupData();

        for (Unit yjetty : dir.YJettyGroup ) {
            this.insertUnit(yjetty);
        }

        for (Unit health : dir.HealthCareGroup ) {
            this.insertUnit(health);
        }

        for (Unit base : dir.BaseServicesGroup ) {
            this.insertUnit(base);
        }

        close();
    }

}
