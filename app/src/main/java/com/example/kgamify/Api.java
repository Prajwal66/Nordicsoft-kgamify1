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
import retrofit2.http.Query;

public interface Api {

    //Api to get all categories on catergory page in recycler view
    @GET("categories/all")
    Call<Backend_CategoryWrapper> getBackend_categories();


    //Api to get all championships under pertiular category on championship page in recycler view
    @GET("championships/getByCategory")
    Call<Backend_ChampionshipWrapper> getItems(@Query("category") String category);

    //Api to post login info into database
    @POST("login")
    Call<Logins> loginUser(@Body Logins login);

}