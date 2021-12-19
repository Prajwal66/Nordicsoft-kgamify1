package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameMode extends AppCompatActivity {


    ImageView back;
    Dialog myDialog;  //quickhit popup (showpopup)
    Dialog myDialog1; //select gift and play popup (showpopup1)
    Dialog myDialog2;   //info popup (showpopup2)
    Dialog update;    //update popup

    String Label_of_champ,champ_name,game_mode;
    int num_of_questions,time_for_quiz;
    TextView tv_game_mode_quick_hit,tv_game_mode_select_gift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        getSupportActionBar().hide(); //Code to remove Action Bar


        initialize();



        //getting values, transfered from category page
        Label_of_champ=getIntent().getStringExtra("Label_of_champ");
        champ_name=getIntent().getStringExtra("champ_name");
        num_of_questions=getIntent().getIntExtra("num_of_questions",0);
        time_for_quiz=getIntent().getIntExtra("time_for_quiz",0);

        myDialog = new Dialog(this);
        myDialog1 =new Dialog(this);
        myDialog2 = new Dialog(this);
        update= new Dialog(this);


        back =(ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GameMode.this,Categories.class);
                startActivity(i);
            }
        });



    }

    private void initialize() {
        tv_game_mode_quick_hit=(TextView) findViewById(R.id.tv_game_mode_quick_hit);
        tv_game_mode_select_gift=(TextView) findViewById(R.id.tv_game_mode_select_gift);
    }

    public void ShowPopupupdate(View v){
        TextView cancel;
        Button btnupdate;

        update.setContentView(R.layout.update_popup);
        cancel =(TextView) update.findViewById(R.id.cancel);
        btnupdate =(Button) update.findViewById(R.id.btn_update);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.dismiss();
            }
        });
        update.show();



    }

    //quickhit popup
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
                game_mode="Quick Hit";
                i.putExtra("Label_of_champ",Label_of_champ);
                i.putExtra("champ_name",champ_name);
                i.putExtra("game_mode",game_mode);
                i.putExtra("num_of_questions",num_of_questions);
                i.putExtra("time_for_quiz",time_for_quiz);
                startActivity(i);
            }
        });


    }

    //select gift and play popup
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
                game_mode="Select Gift and Play";
                in.putExtra("Label_of_champ",Label_of_champ);
                in.putExtra("champ_name",champ_name);
                in.putExtra("game_mode",game_mode);
                in.putExtra("num_of_questions",num_of_questions);
                in.putExtra("time_for_quiz",time_for_quiz);

                startActivity(in);
            }
        });
    }

    //info popup
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