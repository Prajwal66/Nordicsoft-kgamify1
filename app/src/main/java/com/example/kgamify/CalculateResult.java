package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculateResult extends AppCompatActivity {

    TextView tv_score,tv_coins_earned,tv_msg;
    Button btn_claimgift;

    int num_of_true,coins_earned,bonus_coins,total_que,coins_to_api_1,coins_to_api_2;
    double percentage;
    String from_page;


    Api api6;
    String current_user_phone;
    List<Logins> all_user_info;
    Logins single_user_info;
    SharedPreferences sharedPreferences;
    private static final String shared_pref_name="my_pref";
    private static final String key_phone="phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        getSupportActionBar().hide(); //Code to remove Action Bar


        //getting values transferred from question page
        num_of_true= getIntent().getIntExtra("num_of_true",0);
        coins_earned=getIntent().getIntExtra("coins_earned",0);
        percentage=getIntent().getDoubleExtra("percentage",0);
        from_page=getIntent().getStringExtra("from_page");
        bonus_coins=getIntent().getIntExtra("bonus_coins",0);
        total_que=getIntent().getIntExtra("total_que",0);


        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        current_user_phone=sharedPreferences.getString(key_phone,null);

        initialize();
        move();
        showResult();
        getUserInfo();



    }

    private void getUserInfo() {

        api6=RetrofitInstance.getRetrofit().create(Api.class);
        Call<LoginsWrapper> loginsWrapperCall= api6.getCurrentUser();

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

                coins_to_api_2=single_user_info.getWallet_coins();
                coins_to_api_2=coins_to_api_2+coins_to_api_1;
                single_user_info.setWallet_coins(coins_to_api_2);
                postCoinsToApi(single_user_info);
            }

            @Override
            public void onFailure(Call<LoginsWrapper> call, Throwable t) {

            }
        });
    }

    private void postCoinsToApi(Logins single_user_info){

        Call<Logins> loginsCall=api6.setWalletCoins(single_user_info);
        loginsCall.enqueue(new Callback<Logins>() {
            @Override
            public void onResponse(Call<Logins> call, Response<Logins> response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<Logins> call, Throwable t) {

            }
        });

    }

    private void showResult() {

        //if user completes championship , else quit championship
        if(from_page.equals("with_flow")){

            //if user given more than 50% correct answer then he get bonus else he will not get bonus
            if(percentage>50){

                coins_earned=coins_earned+bonus_coins;

                tv_msg.setText("Congratulations \n you got bonus of"+bonus_coins+" coins!!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_to_api_1+coins_earned;

            }
            else if(percentage<=50 && percentage>0){
                tv_msg.setText("Congratulations !!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_earned;
            }
            else if(percentage==0) {
                tv_msg.setText("Bad Luck !!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_earned;
            }
        }
        else if(from_page.equals("quit_page")){

            tv_msg.setText("Hard Luck !!");
            tv_score.setText(num_of_true+"/"+total_que);
            tv_coins_earned.setText(coins_earned+"coins");

            coins_to_api_1=coins_earned;
        }
        else if(from_page.equals("time_out")) {
            //if user given more than 50% correct answer then he get bonus else he will not get bonus
            if(percentage>50){

                coins_earned=coins_earned+bonus_coins;

                tv_msg.setText("Congratulations \n You Got Bonus of \n"+bonus_coins+" coins!!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_earned;

            }
            else if(percentage<=50 && percentage>0){
                tv_msg.setText("Congratulations !!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_earned;
            }
            else if(percentage==0) {
                tv_msg.setText("Bad Luck !!");
                tv_score.setText(num_of_true+"/"+total_que);
                tv_coins_earned.setText(coins_earned+"coins");

                coins_to_api_1=coins_earned;
            }
        }

    }

    private void initialize() {
        tv_score=(TextView) findViewById(R.id.tv_score);
        tv_coins_earned=(TextView) findViewById(R.id.tv_coins_earned);
        btn_claimgift=(Button) findViewById(R.id.btn_claimgift);
        tv_msg=(TextView) findViewById(R.id.tv_msg);

    }

    private void move() {
        btn_claimgift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CalculateResult.this,Categories.class);
                startActivity(i);
            }
        });

    }

}