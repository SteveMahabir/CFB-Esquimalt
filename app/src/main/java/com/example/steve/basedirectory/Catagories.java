package com.example.steve.basedirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Catagories extends AppCompatActivity {

    // Data Members
    ListView lv;
    private DirectoryDatabase db;


    // Default Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);

        // Initialize
        db = new DirectoryDatabase(this);

        // get the existing database file or from assets folder if doesn't exist
        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/databases";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();

                //---copy the db from the assets folder into
                // the databases folder---
                CopyDB(getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath + "/MyDB"));
            }

            SetupDatabase();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        db.open();
        Cursor c = db.getAllSubUnits();

        ArrayList<String> subUnits = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {
                subUnits.add(c.getString(2));
            } while (c.moveToNext());
        }
        db.close();

        // Populate List View
        lv = (ListView) findViewById(R.id.listView_Directory);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                subUnits);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                // Commit Shared Preferences
                SharedPreferences sharedData = getSharedPreferences("default", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedData.edit();

                editor.putString("unit", lv.getItemAtPosition(position).toString());
                editor.commit();

                Intent appInfo = new Intent(Catagories.this, Units.class);
                startActivity(appInfo);

            }
        });
    }

}
