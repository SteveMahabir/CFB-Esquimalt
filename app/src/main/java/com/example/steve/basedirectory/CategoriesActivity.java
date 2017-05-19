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
import java.io.Serializable;
import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    // Data Members
    ListView lv;
    static DirectoryDatabase db;
    static ArrayList<Category> categories;
    static View.OnClickListener categoryClickListener;
    private static RecyclerView rv;

    // Default Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Courtesy Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);
        categoryClickListener = new CategoryClickListener(this);
        InitializeDatabase();

        // Recycler View Setup
        rv = (RecyclerView)findViewById(R.id.recViewCategory);
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



    public static class CategoryClickListener implements View.OnClickListener {

        private final Context context;

        private CategoryClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            loadUnits(v);
            //Toast.makeText(v.getContext(), "Hello toast!!", Toast.LENGTH_LONG).show();
        }

        private void loadUnits(View v) {
            int selectedItemPosition = rv.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder = rv.findViewHolderForPosition(selectedItemPosition);

            Category c = categories.get(viewHolder.getAdapterPosition());

            // Commit Shared Preferences
            SharedPreferences sharedData = context.getSharedPreferences("default", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedData.edit();

            editor.putString("category", c.CategoryType);
            editor.commit();

            Intent appInfo = new Intent(context, UnitsActivity.class);
            db.close();
            db.closeDatabase();
            appInfo.putExtra("database", db);
            context.startActivity(appInfo);


        }
    }



}
