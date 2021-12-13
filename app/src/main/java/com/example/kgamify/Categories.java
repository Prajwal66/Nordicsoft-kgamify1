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
import android.widget.Button;
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
    TextView tv_cat_name,tv_champ_name,tv_champ_start_time,textView;
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
                switch (item.getItemId()){
                    case R.id.menu_categories:
                        Intent intent = new Intent(Categories.this,Categories.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_Notifications:
                        Toast.makeText(getApplicationContext(),"NOTIFICATIONS",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_wallet:
                        Toast.makeText(getApplicationContext(),"99+",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });

       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawers,toolbar, R.string.open);

        //  info = new Dialog(this);    //info popup
        initialize();
        getCategoriesFromApi();
        recycler_view_1.setLayoutManager(new LinearLayoutManager(this));
      /*  arrow.setOnClickListener(new View.OnClickListener() {
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
        });*/



    }


    private void getCategoriesFromApi() {

        api2=RetrofitInstance.getRetrofit().create(Api.class);
        Call<Backend_CategoryWrapper> call=api2.getBackend_categories();

        call.enqueue(new Callback<Backend_CategoryWrapper>() {
            @Override
            public void onResponse(Call<Backend_CategoryWrapper> call, Response<Backend_CategoryWrapper> response) {

                category_arr=response.body().getBackend_categories();


                recycler_view_1.setAdapter(new myAdapter(category_arr,getApplicationContext()));
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
        tv_cat_name=(TextView)findViewById(R.id.tv_cat_name);
        tv_champ_name=(TextView)findViewById(R.id.tv_champ_name);
        tv_champ_start_time=(TextView)findViewById(R.id.tv_champ_start_time);

        recycler_view_1=(RecyclerView) findViewById(R.id.recycler_view_1);

    }

    //info popup
  /*  public void ShowPopupinfo(View v){
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
    }*/




}