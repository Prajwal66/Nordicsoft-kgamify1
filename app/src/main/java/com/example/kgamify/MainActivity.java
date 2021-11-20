package com.example.kgamify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtView_sign_in;
    private ImageView imgView_logo;
    private EditText edtText_enter_phone_no;
    private Button btn_send_otp,btn_skip,btn_select_lang;
    private CountryCodePicker ccp;
    int lang_selected;
    String code,country,number;

    Context context;
    Resources resources;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String language= Locale.getDefault().getLanguage();         //code to select default language of phone


        getSupportActionBar().hide();        //Code to remove Action Bar

        initializeView();
        listeners();
        move();
        setPhoneNumber();
        setLanguage(language);


        if(Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("en"))
        {
            context=Localehelper.setLocale(MainActivity.this,"en");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }

        else if(Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("es"))
        {
            context=Localehelper.setLocale(MainActivity.this,"es");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }

        else if(Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("fr"))
        {
            context=Localehelper.setLocale(MainActivity.this,"fr");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }

        else if(Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("ko"))
        {
            context=Localehelper.setLocale(MainActivity.this,"ko");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }


        else
        {
            context=Localehelper.setLocale(MainActivity.this,"hi");
            resources=context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }


    }

    private void setPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.READ_PHONE_STATE
            },121);

            return;
        }




        String ph=telephonyManager.getLine1Number();
        edtText_enter_phone_no.setText(ph);
        //Toast.makeText(this,ph,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==121 && resultCode==RESULT_OK)
        {
            //Permission granted
        }
    }

    private void setLanguage(String language)
    {
        if(language.equals("hi"))
        {
            context=Localehelper.setLocale(getApplicationContext(),"hi");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }
        else if(language.equals("en"))
        {
            context=Localehelper.setLocale(getApplicationContext(),"en");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }

        else if(language.equals("ko"))
        {
            Toast.makeText(MainActivity.this,"good korea", Toast.LENGTH_SHORT).show();
            context=Localehelper.setLocale(getApplicationContext(),"ko");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }

        else if(language.equals("es"))
        {
            Toast.makeText(MainActivity.this,"good spanish", Toast.LENGTH_SHORT).show();
            context=Localehelper.setLocale(getApplicationContext(),"es");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }
        else if(language.equals("fr"))
        {
            Toast.makeText(MainActivity.this,"good french", Toast.LENGTH_SHORT).show();
            context=Localehelper.setLocale(getApplicationContext(),"fr");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }



    }

    //function to initialize all declared components

    private void initializeView()
    {
        txtView_sign_in=(TextView) findViewById(R.id.txtView_sign_in);
        imgView_logo=(ImageView) findViewById(R.id.imgView_logo);
        edtText_enter_phone_no=(EditText) findViewById(R.id.edtText_enter_phone_no);
        btn_send_otp=(Button) findViewById(R.id.btn_send_otp);
        btn_skip=(Button)findViewById(R.id.btn_skip);
        ccp=(CountryCodePicker) findViewById(R.id.ccp);



        btn_send_otp.setEnabled(true);


    }

    //function for country code picker

    private void listeners()
    {
        btn_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get variables
                code=ccp.getSelectedCountryCode();
                country=ccp.getSelectedCountryName();
                number=edtText_enter_phone_no.getText().toString();

                //create toast
                Context context=getApplicationContext();
                CharSequence text="Country="+country+" ,Code="+code+" "+number;
                int duration= Toast.LENGTH_SHORT;
                Toast toast=Toast.makeText(context,text,duration);
                toast.show();
            }
        });
    }

    //function to move from one activity to another

    void move()
    {
        btn_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                code=ccp.getSelectedCountryCode();

               if(!edtText_enter_phone_no.getText().toString().trim().isEmpty())
                {
                    Intent i=new Intent(MainActivity.this,OTP_verification.class);
                    i.putExtra("mobile",edtText_enter_phone_no.getText().toString());
                    i.putExtra("countryCode",code);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Categories.class);
                startActivity(intent);
            }
        });
    }

}