package com.example.kgamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
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

    //shared pref variables
    SharedPreferences sharedPreferences;
    private static final String shared_pref_name="my_pref";
    private static final String key_phone="phone";

    RecyclerView recycler_view_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        drawers = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.t1);

        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        String current_user_phone1=sharedPreferences.getString(key_phone,null);

        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_user_phone1 == null) {
                    Intent intent = new Intent(Categories.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Please sign in", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Already Signed",Toast.LENGTH_LONG).show();
                }

            }
        });


        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        String current_user_phone=sharedPreferences.getString(key_phone,null);

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

                    case R.id.menu_wallet:
                        Intent intent1 = new Intent(Categories.this,walletpage.class);
                        startActivity(intent1);
                        break;

                    case R.id.profile:
                        Intent intent2 = new Intent(Categories.this,profilepage.class);
                        startActivity(intent2);
                        break;


                    case R.id.logout:
                        if(current_user_phone!=null){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            finish();
                            Toast.makeText(getApplicationContext(),"Log out successfully!!",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"You are not Logged In",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });
        initialize();
        getCategoriesFromApi();
        recycler_view_1.setLayoutManager(new LinearLayoutManager(this));

    }

    private void Test() {
        Toast.makeText(getApplicationContext(),"testing",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        if(drawers.isDrawerOpen(GravityCompat.START)) {
            drawers.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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