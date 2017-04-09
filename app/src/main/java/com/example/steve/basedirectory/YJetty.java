package com.example.steve.basedirectory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class YJetty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjetty);


        findViewById(R.id.imageBrandon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_BRANDON));
                startActivity(i);
            }
        });

        findViewById(R.id.imageNanaimo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_NANAIMO));
                startActivity(i);
            }
        });



        findViewById(R.id.imageWhitehorse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_WHITEHORSE));
                startActivity(i);
            }
        });



        findViewById(R.id.imageYellowknife).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_YELLOWKNIFE));
                startActivity(i);
            }
        });



        findViewById(R.id.imageEdmonton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_EDMONTON));
                startActivity(i);
            }
        });



        findViewById(R.id.imageSaskatoon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(Directory.HMCS_SASKATOON));
                startActivity(i);
            }
        });

    }
}
