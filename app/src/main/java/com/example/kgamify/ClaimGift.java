package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClaimGift extends AppCompatActivity {

    Button rtn_category_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_gift);
        getSupportActionBar().hide(); //Code to remove Action Bar

        rtn_category_page =(Button) findViewById(R.id.return_category_page);

        rtn_category_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib=new Intent(ClaimGift.this,Categories.class);
                startActivity(ib);
            }
        });


    }
}