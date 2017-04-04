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
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SubUnitActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    private DirectoryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_unit);

        // Initialize
        db = new DirectoryDatabase(this);

        // Obtain Units
        SharedPreferences requested_units = getSharedPreferences("default", MODE_PRIVATE);
        String subunit_name = requested_units.getString("unit", null);


        // Get and Show All SubUnits
        db.open();
        Cursor c = db.getAllSubunitsByUnits(subunit_name);

        ArrayList<String> units = new ArrayList<>();
        final ArrayList<String> subUnitsPhoneNumbers = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {
                units.add(c.getString(1));
                subUnitsPhoneNumbers.add(c.getString(2));
            } while (c.moveToNext());
        }
        db.close();

        // Populate List View
        lv = (ListView) findViewById(R.id.listview_units);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                units);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                // Launch Phone Number
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(subUnitsPhoneNumbers.get(position).toString()));
                startActivity(i);
            }
        });
    }




}
