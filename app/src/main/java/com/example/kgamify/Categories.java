package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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


    ImageView arrow;
    LinearLayout hiddenView, category1;
    CardView cardView;
    Dialog info;

    RecyclerView recyclerView,recyleview_champoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().hide();

        info = new Dialog(this);    //info popup

        cardView = findViewById(R.id.card1);
        arrow = findViewById(R.id.expand_more);
     //   hiddenView = findViewById(R.id.hidden_view);
        category1 = findViewById(R.id.category1);
        recyclerView=(RecyclerView) findViewById(R.id.recyleview);
        recyleview_champoin=(RecyclerView) findViewById(R.id.recyleview_champoin);





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

        // expand1 champoinship
        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Categories.this,GameMode.class);
                startActivity(i);
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