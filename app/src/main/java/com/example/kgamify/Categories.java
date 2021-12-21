package com.example.kgamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.navigation.NavigationView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Categories extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawers;
    LinearLayout hiddenView, category1;
    TextView tv_cat_name, tv_champ_name, tv_champ_start_time, textView;
    ImageView profile;
    //Api Integration Variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api2;
    List<Backend_Category> category_arr;

    RecyclerView recycler_view_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //textView = findViewById(R.id.t1);
        drawers = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);


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
                        Intent intent = new Intent(Categories.this, Categories.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_Notifications:
                        Toast.makeText(getApplicationContext(), "NOTIFICATIONS", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_wallet:
                        Toast.makeText(getApplicationContext(), "99+", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });

        initialize();
        getCategoriesFromApi();
        recycler_view_1.setLayoutManager(new LinearLayoutManager(this));


    }


    private void getCategoriesFromApi() {

        api2 = RetrofitInstance.getRetrofit().create(Api.class);
        Call<Backend_CategoryWrapper> call = api2.getBackend_categories();

        call.enqueue(new Callback<Backend_CategoryWrapper>() {
            @Override
            public void onResponse(Call<Backend_CategoryWrapper> call, Response<Backend_CategoryWrapper> response) {

                category_arr = response.body().getBackend_categories();


                recycler_view_1.setAdapter(new myAdapter(category_arr, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Backend_CategoryWrapper> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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


    private void initialize() {
        //card1
        category1 = findViewById(R.id.fixed_layout_champ);
        tv_cat_name = (TextView) findViewById(R.id.tv_cat_name);
        tv_champ_name = (TextView) findViewById(R.id.tv_champ_name);
        tv_champ_start_time = (TextView) findViewById(R.id.tv_champ_start_time);

        recycler_view_1 = (RecyclerView) findViewById(R.id.recycler_view_1);
        profile = (ImageView) findViewById(R.id.clickHere);


    }


}