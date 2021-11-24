package com.example.kgamify;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
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
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hbb20.CountryCodePicker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private TextView txtView_sign_in;
    private ImageView imgView_logo;
    private EditText edtText_enter_phone_no;
    private Button btn_send_otp, btn_skip, btn_select_lang;
    private CountryCodePicker ccp;
    int lang_selected;
    String code, country, number;
    String location_country, location_locality, location_address;
    Double location_latitude, location_longitude;

    //Api Integration Variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api;



    Context context;
    Resources resources;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String language = Locale.getDefault().getLanguage();         //code to select default language of phone
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getSupportActionBar().hide();        //Code to remove Action Bar

        initializeView();
        listeners();
        move();

        Toast.makeText(getApplicationContext(),"Latutide="+location_latitude+"\nLongitude"+location_longitude+"\nCountry"+location_country+"\nLocality"+location_locality+"\nAddress="+location_address,Toast.LENGTH_LONG).show();
        setPhoneNumberAndImei();

        getLocationInfo();
        setLanguage(language);
        postDataToApi();


        if (Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("en")) {
            context = Localehelper.setLocale(MainActivity.this, "en");
            resources = context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }
        else if (Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("es")) {
            context = Localehelper.setLocale(MainActivity.this, "es");
            resources = context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }
        else if (Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("fr")) {
            context = Localehelper.setLocale(MainActivity.this, "fr");
            resources = context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }
        else if (Localehelper.getLanguage(MainActivity.this).equalsIgnoreCase("ko")) {
            context = Localehelper.setLocale(MainActivity.this, "ko");
            resources = context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }
        else {
            context = Localehelper.setLocale(MainActivity.this, "hi");
            resources = context.getResources();


            setTitle(resources.getString(R.string.app_name));
            txtView_sign_in.setText(resources.getString(R.string.sign_in));
            edtText_enter_phone_no.setHint(resources.getString(R.string.enter_phone_number));
            btn_send_otp.setText(resources.getString(R.string.send_otp));
            btn_skip.setText(resources.getString(R.string.skip));

        }


    }

    private void getLocationInfo() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            showLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        /*if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

            showLocation();*/


    }

    private void showLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    try {
                        List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        location_latitude = address.get(0).getLatitude();
                        location_longitude = address.get(0).getLongitude();
                        location_country = address.get(0).getCountryName().toString();
                        location_locality = address.get(0).getLocality().toString();
                        location_address = address.get(0).getAddressLine(0).toString();

                        Toast.makeText(getApplicationContext(),"Latutide="+location_latitude+"\nLongitude"+location_longitude+"\nCountry"+location_country+"\nLocality"+location_locality+"\nAddress="+location_address,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, "Location Null", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Location Null", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(),"Latutide="+location_latitude+"\nLongitude"+location_longitude+"\nCountry"+location_country+"\nLocality"+location_locality+"\nAddress="+location_address,Toast.LENGTH_LONG).show();

            }
        });
    }

    private void postDataToApi() {


        Retrofit retrofitClient = RetrofitInstance.getRetrofit();
        api = retrofitClient.create(Api.class);


        btn_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtText_enter_phone_no.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter phone number", Toast.LENGTH_SHORT).show();

                    return;
                }

                //setPhoneNumberAndImei();
                String number=edtText_enter_phone_no.getText().toString();
                postData(number,ccp.getSelectedCountryCode().toString(),location_latitude,location_longitude,location_country,location_locality,location_address);

                Intent i=new Intent(MainActivity.this,OTP_verification.class);
                i.putExtra("mobile",edtText_enter_phone_no.getText().toString());
                i.putExtra("countryCode",ccp.getSelectedCountryCode().toString());
                startActivity(i);
            }
        });

    }

    public void postData(String phone2,String country2,Double location_latitude2,Double location_longitude2,String location_country2,String location_locality2,String location_address2) {
        Logins logins = new Logins(phone2, country2,location_latitude2,location_longitude2,location_country2,location_locality2,location_address2);
        Call<Logins> loginsCall= api.loginUser(new Logins(phone2, country2,location_latitude2,location_longitude2,location_country2,location_locality2,location_address2));
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

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void setPhoneNumberAndImei() {
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
       /* try {
            imei=telephonyManager.getImei().toString();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error="+e.toString(), Toast.LENGTH_LONG).show();
        }*/





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

    private void setLanguage(String language) {
        if(language.equals("hi")) {
            context=Localehelper.setLocale(getApplicationContext(),"hi");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }
        else if(language.equals("en")) {
            context=Localehelper.setLocale(getApplicationContext(),"en");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }

        else if(language.equals("ko")) {
            Toast.makeText(MainActivity.this,"good korea", Toast.LENGTH_SHORT).show();
            context=Localehelper.setLocale(getApplicationContext(),"ko");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }

        else if(language.equals("es")) {
            Toast.makeText(MainActivity.this,"good spanish", Toast.LENGTH_SHORT).show();
            context=Localehelper.setLocale(getApplicationContext(),"es");
            resources=context.getResources();
            setTitle("Kgamify");
            txtView_sign_in.setText("Sign In");
            edtText_enter_phone_no.setHint("Enter Phone Number");
            btn_send_otp.setText("Send OTP");
            btn_skip.setText("Skip");

        }
        else if(language.equals("fr")) {
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

    private void initializeView() {
        txtView_sign_in=(TextView) findViewById(R.id.txtView_sign_in);
        imgView_logo=(ImageView) findViewById(R.id.imgView_logo);
        edtText_enter_phone_no=(EditText) findViewById(R.id.edtText_enter_phone_no);
        btn_send_otp=(Button) findViewById(R.id.btn_send_otp);
        btn_skip=(Button)findViewById(R.id.btn_skip);
        ccp=(CountryCodePicker) findViewById(R.id.ccp);

        btn_send_otp.setEnabled(true);


    }

    //function for country code picker

    private void listeners() {
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

    void move() {


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Categories.class);
                startActivity(intent);
            }
        });
    }

}