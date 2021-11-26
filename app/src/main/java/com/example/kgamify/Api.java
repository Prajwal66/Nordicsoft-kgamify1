package com.example.kgamify;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("categories/all")
    Call<Backend_CategoryWrapper> getBackend_categories();

    @GET("championships/getByCategory?category=Computer Science")
    Call<Backend_ChampionshipWrapper> getItems();

    @POST("login")
//    @FormUrlEncoded
    Call<Logins> loginUser(@Body Logins login);

}