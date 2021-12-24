package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;

public class ChampInfo extends AppCompatActivity {


    Button btn_pop_continue;
    TextView tv_pop_champ_name,tv_pop_participants,tv_pop_k_coins,tv_pop_description;
    String champ_name_for_pop,champ_desc_for_pop;
    int champ_participants_for_pop,champ_coins_for_pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_info);
        getSupportActionBar().hide(); //Code to remove Action Bar


        initialize();
        getChampionshipsInfoFromChampPage();
        move();


    }

    private void getChampionshipsInfoFromChampPage() {

        champ_name_for_pop=getIntent().getStringExtra("champ_name_for_pop");
        champ_participants_for_pop=getIntent().getIntExtra("champ_participants_for_pop",0);
        champ_coins_for_pop=getIntent().getIntExtra("champ_coins_for_pop",0);
        champ_desc_for_pop=getIntent().getStringExtra("champ_desc");

        tv_pop_champ_name.setText(champ_name_for_pop);
        tv_pop_participants.setText(champ_participants_for_pop+" participants");
        tv_pop_k_coins.setText(champ_coins_for_pop+" coins");
        tv_pop_description.setText(champ_desc_for_pop);
    }


    private void initialize() {

        btn_pop_continue =(Button) findViewById(R.id.btn_pop_continue);
        tv_pop_champ_name=(TextView) findViewById(R.id.tv_pop_champ_name);
        tv_pop_participants=(TextView) findViewById(R.id.tv_pop_participants);
        tv_pop_k_coins=(TextView) findViewById(R.id.tv_pop_k_coins);
        tv_pop_description=(TextView) findViewById(R.id.tv_pop_description);

    }

    private void move() {
        btn_pop_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib= new Intent(ChampInfo.this,Championships.class);
                startActivity(ib);
            }
        });

    }
}