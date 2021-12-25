package com.example.kgamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class walletpage extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawers;
    Button btn_wallet_continue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walletpage);

        drawers = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        btn_wallet_continue=(Button)findViewById(R.id.btn_wallet_continue);

        btn_wallet_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(walletpage.this,Categories.class);
                startActivity(i);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawers, R.string.open, R.string.close);

        drawers.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_categories:
                        Intent intent = new Intent(walletpage.this, Categories.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_wallet:
                        Intent intent1 = new Intent(walletpage.this,walletpage.class);
                        startActivity(intent1);
                        break;

                    case R.id.profile:
                        Intent intent2 = new Intent(walletpage.this,profilepage.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}