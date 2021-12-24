package com.example.kgamify;


import retrofit2.Call;
import retrofit2.http.Body;

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

    //Api to get all questions under pertiular championship
    @GET("questions/getByLabel")
    Call<Backend_QuestionWrapper> getQuestion(@Query("label") String label);

    //Api to post login info into database
    @POST("login")
    Call<Logins> loginUser(@Body Logins login);

    //Api to post question is wrong to database
    @POST("questions")
    Call<Backend_Question> setQueRightWrong(@Body Backend_Question backend_question);

    //Api to get current user info
    @GET("login/all")
    Call<LoginsWrapper> getCurrentUser();

    //Api to set coins in user wallet
    @POST("login")
    Call<Logins> setWalletCoins(@Body Logins logins);

}