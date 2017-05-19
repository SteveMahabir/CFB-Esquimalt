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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class UnitsActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    private DirectoryDatabase db;
    static ArrayList<Unit> units;
    static View.OnClickListener unitClickListener;
    private static RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        unitClickListener = new UnitsActivity.UnitClickListener(this);
        InitializeDatabase();


        // Recycler View Setup
        rv = (RecyclerView)findViewById(R.id.recViewUnits);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        // Custom Adapter
        RecycleViewAdapterUnit adapter = new RecycleViewAdapterUnit(units);
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

            // Get All Units
            SharedPreferences sharedData = getSharedPreferences("default", Context.MODE_PRIVATE);
            String category_name = sharedData.getString("category", "failed");

            units = db.getAllUnitsByCategory(category_name);


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

    public static class UnitClickListener implements View.OnClickListener {

        private final Context context;

        private UnitClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = rv.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder = rv.findViewHolderForPosition(selectedItemPosition);

            Unit unit = units.get(viewHolder.getAdapterPosition());
            Intent appInfo = new Intent(Intent.ACTION_DIAL, Uri.parse(unit.UnitTelephone));
            context.startActivity(appInfo);
        }

    }
}
