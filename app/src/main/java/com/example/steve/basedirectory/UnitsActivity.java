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

public class UnitsActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    DirectoryDatabase db;
    ArrayList<Unit> units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        // Initialize
        db = new DirectoryDatabase(this);

        // Obtain Units
        SharedPreferences requested_units = getSharedPreferences("default", MODE_PRIVATE);
        final String subunit_name = requested_units.getString("unit", null);

        // Create Lists for units and phone number lists

        // Get and Show All Units
        units = db.getAllUnits();

        // Populate List View
        //lv = (ListView) findViewById(R.id.listview_units);

        ArrayAdapter<Unit> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_2,
                units);

        //todo: Make the adapter work with simple_list_item_2 (two layers of text!)
        lv.setAdapter(arrayAdapter);

        // When a user selects a unit, call the unit
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                // Launch Phone Number
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(units.get(position).toString()));
                startActivity(i);
            }
        });



    }




}
