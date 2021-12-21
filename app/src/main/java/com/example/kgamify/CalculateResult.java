package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculateResult extends AppCompatActivity {

    TextView result_score, win_coin;
    Button btn_claimgift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        getSupportActionBar().hide(); //Code to remove Action Bar

        result_score=(TextView) findViewById(R.id.result_score);
        win_coin=(TextView) findViewById(R.id.winner_coins);
        btn_claimgift=(Button) findViewById(R.id.btn_claimgift);

        btn_claimgift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CalculateResult.this,ClaimGift.class);
                startActivity(i);
            }
        });


    }

}