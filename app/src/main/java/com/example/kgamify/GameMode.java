package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameMode extends AppCompatActivity {

    ImageView back;
    Dialog myDialog;  //quickhit popup (showpopup)
    Dialog myDialog1; //select gift and play popup (showpopup1)
    Dialog myDialog2;   //info popup (showpopup2)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        getSupportActionBar().hide(); //Code to remove Action Bar

        myDialog = new Dialog(this);
        myDialog1 =new Dialog(this);
        myDialog2 = new Dialog(this);

        back =(ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GameMode.this,Categories.class);
                startActivity(i);
            }
        });


    }

    public void ShowPopup(View v){
        TextView txtclose;
        Button btnstart1;

        myDialog.setContentView(R.layout.quickhit_popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        btnstart1 =(Button) myDialog.findViewById(R.id.btnstart1);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();
            }
        });
        myDialog.show();

        btnstart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GameMode.this,QuestionText.class);
                startActivity(i);
            }
        });


    }



    public void ShowPopup1(View v) {
        TextView txtclose1;
        Button btnstart2;

        myDialog1.setContentView(R.layout.selectgift_popup);
        txtclose1 =(TextView) myDialog1.findViewById(R.id.txtclose1);
        btnstart2 =(Button) myDialog1.findViewById(R.id.btnstart2);
        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog1.dismiss();
            }
        });
        myDialog1.show();

        btnstart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(GameMode.this,QuestionText.class);
                startActivity(in);
            }
        });
    }



    public void ShowPopup2(View v){
        TextView txtclose2;

        myDialog2.setContentView(R.layout.info);
        txtclose2 =(TextView) myDialog2.findViewById(R.id.txtclose2);
        txtclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog2.dismiss();
            }
        });
        myDialog2.show();
    }


}