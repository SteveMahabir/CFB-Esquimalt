package com.example.steve.basedirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    private DirectoryDatabase db;
    ArrayList<Category> categories;

    // Default Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);

        InitializeDatabase();

        // Categories


        // Recycler View Setup
        RecyclerView rv = (RecyclerView)findViewById(R.id.recViewCategory);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        // Custom Adapter
        RecycleViewAdapterCategory adapter = new RecycleViewAdapterCategory(categories);
        rv.setAdapter(adapter);
    }

    private void InitializeDatabase() {
        db = new DirectoryDatabase(this);

        // get the existing database file or from assets folder if doesn't exist
        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/databases";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();

                //---copy the db from the assets folder into the databases folder
                CopyDB(getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath + "/MyDB"));
            }

            // SetupDatabase();
            db.Populate();

            // Get all the Categories
            categories = db.getAllCategories();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Needed for Database Deposit
    public void CopyDB(InputStream inputStream,
                       OutputStream outputStream) throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    // Populate all the rows and register the onClickListener
    public void SetupDatabase(){

        // Populate the Database
        db.Populate();

        // Get and Show All SubUnits
        //ArrayList<Category> categories = db.getAllCategories();

        // Populate List View
        //lv = (ListView) findViewById(R.id.listView_Directory);

        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categories);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                // Commit Shared Preferences
                SharedPreferences sharedData = getSharedPreferences("default", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedData.edit();

                editor.putString("unit", lv.getItemAtPosition(position).toString());
                editor.commit();

                Intent appInfo = new Intent(CategoriesActivity.this, UnitsActivity.class);
                startActivity(appInfo);

            }
        });
    }

}
