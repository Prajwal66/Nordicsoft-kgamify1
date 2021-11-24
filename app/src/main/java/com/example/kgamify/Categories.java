package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class Categories extends AppCompatActivity {


    ImageView arrow, arrow1;
    LinearLayout hiddenView, category1, category2, hiddenview1, categoey3;
    CardView cardView, cardView1;
    Dialog info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().hide();

        info = new Dialog(this);    //info popup

        //card1
        cardView = findViewById(R.id.card1);
        arrow = findViewById(R.id.expand_more);
        hiddenView = findViewById(R.id.hidden_view);
        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);


        //card2
        arrow1 = findViewById(R.id.expand_more1);
        cardView1 = findViewById(R.id.card2);
        hiddenview1 = findViewById(R.id.hidden_view1);
        categoey3 = findViewById(R.id.category3);


        //card1
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            }
        });

        //card1 expand1
        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Categories.this,GameMode.class);
                startActivity(i);
            }
        });


        //card1 expand2
        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(Categories.this,GameMode.class);
                startActivity(in);

            }
        });



        //card2
        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenview1.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    hiddenview1.setVisibility(View.GONE);
                    arrow1.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    hiddenview1.setVisibility(View.VISIBLE);
                    arrow1.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            }
        });

        //card2 expand1
        categoey3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Categories.this,GameMode.class);
                startActivity(intent);

            }
        });


    }

    //info popup
    public void ShowPopupinfo(View v){
        TextView txtclose2;

        info.setContentView(R.layout.info);
        txtclose2 =(TextView) info.findViewById(R.id.txtclose2);
        txtclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.dismiss();
            }
        });
        info.show();
    }




}