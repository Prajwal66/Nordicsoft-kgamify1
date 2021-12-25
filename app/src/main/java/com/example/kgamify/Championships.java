package com.example.kgamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Championships extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayouts;
    TextView tv_select_champ;
    RecyclerView recycler_view_2;

    //Api variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api3;
    List<Backend_Championship> champ_arr;

    //shared pref variables
    SharedPreferences sharedPreferences;
    private static final String shared_pref_name="my_pref";
    private static final String key_phone="phone";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championships);

        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        String current_user_phone=sharedPreferences.getString(key_phone,null);



        nav = findViewById(R.id.nav_view1);
        drawerLayouts = findViewById(R.id.newdrawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayouts, R.string.open, R.string.close);
        //this is used for side menu bar icon which will open and close the navigation

        drawerLayouts.addDrawerListener(toggle);
        toggle.syncState();


        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_categories:
                        Intent intent = new Intent(Championships.this, Categories.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_wallet:
                        Intent intent1 = new Intent(Championships.this,walletpage.class);
                        startActivity(intent1);
                        break;
                    case R.id.profile:
                        Intent intent2 = new Intent(Championships.this,profilepage.class);
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
        getChampionshipsFromApi();


        recycler_view_2.setLayoutManager(new LinearLayoutManager(this));





    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void getChampionshipsFromApi() {

        String Cat_selected=getIntent().getStringExtra("Category_selected");

        api3=RetrofitInstance.getRetrofit().create(Api.class);
        Call<Backend_ChampionshipWrapper> call=api3.getItems(Cat_selected);

        call.enqueue(new Callback<Backend_ChampionshipWrapper>() {
            @Override
            public void onResponse(Call<Backend_ChampionshipWrapper> call, Response<Backend_ChampionshipWrapper> response) {

                champ_arr=response.body().getItems();

                recycler_view_2.setAdapter(new myAdapter2(champ_arr,getApplicationContext()));

            }

            @Override
            public void onFailure(Call<Backend_ChampionshipWrapper> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initialize() {

        tv_select_champ=(TextView) findViewById(R.id.tv_select_champ);
        recycler_view_2=(RecyclerView) findViewById(R.id.recycler_view_2);


    }
}