package com.example.kgamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class walletpage extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawers;
    Button btn_wallet_continue;
    TextView tv_wallet_coins;

    //Api variables
    Api api7;
    String current_user_phone;
    List<Logins> all_user_info;
    Logins single_user_info;
    int current_user_coins;

    //shared pref variables
    SharedPreferences sharedPreferences;
    private static final String shared_pref_name="my_pref";
    private static final String key_phone="phone";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walletpage);


        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        current_user_phone=sharedPreferences.getString(key_phone,null);


        initialize();
        move();
        getCoinsFromApi();





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


                    case R.id.logout:
                         SharedPreferences.Editor editor=sharedPreferences.edit();
                         editor.clear();
                         editor.commit();
                         finish();
                         Toast.makeText(getApplicationContext(),"Log out successfully!!",Toast.LENGTH_SHORT).show();
                         Intent i=new Intent(getApplicationContext(),MainActivity.class);
                         startActivity(i);
                         break;


                }
                return true;
            }
        });



    }

    private void getCoinsFromApi() {

        api7=RetrofitInstance.getRetrofit().create(Api.class);
        Call<LoginsWrapper> loginsWrapperCall= api7.getCurrentUser();

        loginsWrapperCall.enqueue(new Callback<LoginsWrapper>() {
            @Override
            public void onResponse(Call<LoginsWrapper> call, Response<LoginsWrapper> response) {
                all_user_info=response.body().getLogins();

                for(int i=0;i<all_user_info.size();i++)
                {
                    if(current_user_phone.equals(all_user_info.get(i).getPhone()))
                    {
                        single_user_info=all_user_info.get(i);
                        break;
                    }
                }

                current_user_coins=single_user_info.getWallet_coins();

                tv_wallet_coins.setText(" "+current_user_coins);

            }

            @Override
            public void onFailure(Call<LoginsWrapper> call, Throwable t) {

            }
        });


    }

    private void initialize() {
        drawers = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        btn_wallet_continue=(Button)findViewById(R.id.btn_wallet_continue);
        tv_wallet_coins=(TextView) findViewById(R.id.tv_walllet_coins);
    }

    private void move() {
        btn_wallet_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(walletpage.this,Categories.class);
                startActivity(i);
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