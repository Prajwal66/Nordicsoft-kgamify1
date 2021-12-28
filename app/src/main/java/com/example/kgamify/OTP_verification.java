package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OTP_verification extends AppCompatActivity {

    private TextView txtView_verification_code,txtView_msg,txtView_phone_no,txtView_timer,txtView_resend_otp;
    private LinearLayout linear_layout_msg,linear_layout_otp;
    private ImageView imgView_otp_img;
    private EditText input_otp_1,input_otp_2,input_otp_3,input_otp_4;
    private Button btn_continue;

    Context context;
    Resources resources;
    CountDownTimer countDownTimer;

    Api api;

    String mobile,countryCode,location_country,location_locality,location_address;
    int wallet_coins;
    double location_latitude,location_longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        String language= Locale.getDefault().getLanguage();         //code to select default language of phone

        getSupportActionBar().hide();        //Code to remove Action Bar

        initializeView();
        setTimer();
        move();
        dataTransfer();
        numberotpMove();


        mobile=getIntent().getStringExtra("mobile");
        countryCode=getIntent().getStringExtra("countryCode");
        wallet_coins=getIntent().getIntExtra("wallet_coins",0);
        location_latitude=getIntent().getDoubleExtra("location_latitude",0);
        location_longitude=getIntent().getDoubleExtra("location_longitude",0);
        location_country=getIntent().getStringExtra("location_country");
        location_locality=getIntent().getStringExtra("location_locality");
        location_address=getIntent().getStringExtra("location_address");


        if(Localehelper.getLanguage(getApplicationContext()).equalsIgnoreCase("en")) {
            context=Localehelper.setLocale(getApplicationContext(),"en");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_verification_code.setText(resources.getString(R.string.verification_code));
            txtView_msg.setText(resources.getString(R.string.please_type_verification_code_sent_to));
            txtView_timer.setText(resources.getString(R.string.valid_for_09_59));
            txtView_resend_otp.setText(resources.getString(R.string.resend_otp));


        }
        else if(Localehelper.getLanguage(getApplicationContext()).equalsIgnoreCase("es")) {
            context=Localehelper.setLocale(getApplicationContext(),"es");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_verification_code.setText(resources.getString(R.string.verification_code));
            txtView_msg.setText(resources.getString(R.string.please_type_verification_code_sent_to));
            txtView_timer.setText(resources.getString(R.string.valid_for_09_59));
            txtView_resend_otp.setText(resources.getString(R.string.resend_otp));

        }
        else if(Localehelper.getLanguage(getApplicationContext()).equalsIgnoreCase("fr")) {
            context=Localehelper.setLocale(getApplicationContext(),"fr");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_verification_code.setText(resources.getString(R.string.verification_code));
            txtView_msg.setText(resources.getString(R.string.please_type_verification_code_sent_to));
            txtView_timer.setText(resources.getString(R.string.valid_for_09_59));
            txtView_resend_otp.setText(resources.getString(R.string.resend_otp));

        }
        else if(Localehelper.getLanguage(getApplicationContext()).equalsIgnoreCase("ko")) {
            context=Localehelper.setLocale(getApplicationContext(),"ko");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_verification_code.setText(resources.getString(R.string.verification_code));
            txtView_msg.setText(resources.getString(R.string.please_type_verification_code_sent_to));
            txtView_timer.setText(resources.getString(R.string.valid_for_09_59));
            txtView_resend_otp.setText(resources.getString(R.string.resend_otp));

        }
        else {
            context=Localehelper.setLocale(getApplicationContext(),"hi");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_verification_code.setText(resources.getString(R.string.verification_code));
            txtView_msg.setText(resources.getString(R.string.please_type_verification_code_sent_to));
            txtView_timer.setText(resources.getString(R.string.valid_for_09_59));
            txtView_resend_otp.setText(resources.getString(R.string.resend_otp));

        }
    }



    private void initializeView() {
           txtView_verification_code=(TextView) findViewById(R.id.txtView_verification_code);
           txtView_msg=(TextView) findViewById(R.id.txtView_msg);
           txtView_phone_no=(TextView) findViewById(R.id.txtView_phone_no);
           txtView_timer=(TextView) findViewById(R.id.txtView_timer);
           txtView_resend_otp=(TextView) findViewById(R.id.txtView_resend_otp);

           imgView_otp_img=(ImageView) findViewById(R.id.imgView_otp_img);

           input_otp_1=(EditText) findViewById(R.id.input_otp_1);
           input_otp_2=(EditText) findViewById(R.id.input_otp_2);
           input_otp_3=(EditText) findViewById(R.id.input_otp_3);
           input_otp_4=(EditText) findViewById(R.id.input_otp_4);

           btn_continue=(Button) findViewById(R.id.btn_continue);
    }

    private void move() {
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!input_otp_1.getText().toString().trim().isEmpty() && !input_otp_2.getText().toString().trim().isEmpty() && !input_otp_3.getText().toString().trim().isEmpty() && !input_otp_4.getText().toString().trim().isEmpty())
                {
                    countDownTimer.cancel();

                    postDataToApi();

                    Intent i=new Intent(OTP_verification.this,Categories.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(OTP_verification.this,"Please enter all numbers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtView_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OTP_verification.this,"OTP resent to provided mobile number",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postDataToApi(){

        Retrofit retrofit=RetrofitInstance.getRetrofit();
        api= retrofit.create(Api.class);

        Logins logins = new Logins(mobile, countryCode,wallet_coins,location_latitude,location_longitude,location_country,location_locality,location_address);

        Call<Logins> loginsCall= api.loginUser(new Logins(mobile, countryCode,wallet_coins,location_latitude,location_longitude,location_country,location_locality,location_address));
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

    private void dataTransfer() {
        txtView_phone_no.setText(String.format("+"+getIntent().getStringExtra("countryCode")+"\t"+getIntent().getStringExtra("mobile")));
    }

    private void numberotpMove() {
         input_otp_1.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(!charSequence.toString().trim().isEmpty())
                 {
                     input_otp_2.requestFocus();
                 }

             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });

        input_otp_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    input_otp_3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input_otp_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    input_otp_4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setTimer() {
        long duration= TimeUnit.MINUTES.toMillis(10);            //Initialize time duration
        countDownTimer=new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {


                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                txtView_timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

            }

            @Override
            public void onFinish()
            {
                //when finish
                //display toast
                Toast.makeText(getApplicationContext(), "Time Out", Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}