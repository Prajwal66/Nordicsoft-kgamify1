package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChampInfo extends AppCompatActivity {
    Button info_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_info);
        getSupportActionBar().hide(); //Code to remove Action Bar


        info_continue =(Button) findViewById(R.id.btn_info_continue);

        info_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib= new Intent(ChampInfo.this,GameMode.class);
                startActivity(ib);
            }
        });
    }
}