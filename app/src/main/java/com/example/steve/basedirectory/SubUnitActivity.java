package com.example.steve.basedirectory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubUnitActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    private DirectoryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_unit);

        // Initialize
        db = new DirectoryDatabase(this);

        // Obtain Units
        SharedPreferences requested_units = getSharedPreferences("default", MODE_PRIVATE);
        final String subunit_name = requested_units.getString("unit", null);

        // Create Lists for units and phone number lists
        final ArrayList<String> units = new ArrayList<>();
        final ArrayList<String> subUnitsPhoneNumbers = new ArrayList<>();

        // Get and Show All SubUnits
        db.open();
        Cursor c = db.getAllSubunitsByUnits(subunit_name);

        // Populate ArrayLists
        if (c.moveToFirst())
        {
            do {
                units.add(c.getString(1));
                subUnitsPhoneNumbers.add(c.getString(3));
            } while (c.moveToNext());
        }
        db.close();

        // Populate List View
        //lv = (ListView) findViewById(R.id.listview_units);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_2,
                units);

        //todo: Make the adapter work with simple_list_item_2 (two layers of text!)

        // Link units to the listview
        lv.setAdapter(arrayAdapter);

        // When a user selects a unit, call the unit
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
