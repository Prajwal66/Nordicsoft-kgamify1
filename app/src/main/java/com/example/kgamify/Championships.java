package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Championships extends AppCompatActivity {

    ImageView img_champ_sidemenu;
    SearchView search_view_champ;
    TextView tv_select_champ;
    RecyclerView recycler_view_2;

    Dialog myDialog_champ;

    //Api variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api3;
    List<Backend_Championship> champ_arr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championships);

        getSupportActionBar().hide();

        myDialog_champ=new Dialog(this);

        initialize();
        getChampionshipsFromApi();

        recycler_view_2.setLayoutManager(new LinearLayoutManager(this));



    }

    public void ShowPopup3(View v){
        TextView txtclose2;

        myDialog_champ.setContentView(R.layout.info);
        txtclose2 =(TextView) myDialog_champ.findViewById(R.id.txtclose2);
        txtclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_champ.dismiss();
            }
        });
        myDialog_champ.show();
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

        img_champ_sidemenu=(ImageView) findViewById(R.id.img_champ_sidemenu);
        search_view_champ=(SearchView)  findViewById(R.id.search_view_champ);
        tv_select_champ=(TextView) findViewById(R.id.tv_select_champ);
        recycler_view_2=(RecyclerView) findViewById(R.id.recycler_view_2);

    }
}