package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Categories extends AppCompatActivity {


    ImageView arrow;
    LinearLayout hiddenView, category1;
    CardView cardView;
    Dialog info;
    TextView tv_cat_name,tv_champ_name,tv_champ_start_time;


    //Api Integration Variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api2;
    List<Backend_Category> category_arr;




    RecyclerView recycler_view_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().hide();

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