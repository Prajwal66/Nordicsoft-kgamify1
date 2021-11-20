package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class Categories extends AppCompatActivity {

    ImageView add;
    ExpandableRelativeLayout expand;
    ExpandableRelativeLayout expand2;
    ExpandableRelativeLayout expand3;
    TextView categorytype;
      Dialog myDialogwallet;
      Dialog myDialoginfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().hide();

        add=(ImageView) findViewById(R.id.add);
        categorytype=(TextView) findViewById(R.id.categorytype);

        categorytype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Categories.this,GameMode.class);
                startActivity(i);
            }
        });
    }

    public void showmyinfo(View view){

        expand=(ExpandableRelativeLayout) findViewById(R.id.expand);
        expand.toggle();

        expand2=(ExpandableRelativeLayout) findViewById(R.id.expand2);
        expand2.toggle();

        expand3=(ExpandableRelativeLayout) findViewById(R.id.expand3);
        expand3.toggle();
    }

    public void ShowPopupwallet(View v) {
        myDialogwallet.setContentView(R.layout.wallet);

        myDialogwallet.show();
    }

    public void ShowPopupinfo(View v){
        TextView txtclose2;

        myDialoginfo.setContentView(R.layout.info);
        txtclose2 =(TextView) myDialoginfo.findViewById(R.id.txtclose2);
        txtclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialoginfo.dismiss();
            }
        });
        myDialoginfo.show();
    }
}