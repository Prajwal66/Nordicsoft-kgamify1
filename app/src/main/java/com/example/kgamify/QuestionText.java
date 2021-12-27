package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionText extends AppCompatActivity {

    TextView txt_timer;
    TextView btn1,btn2,btn3,btn4,btn5,tv_que_coins,tv_que,tv_que_opt1,tv_que_opt2,tv_que_opt3,tv_que_opt4,tv_que_number,tv_que_champ_name,tv_game_mode_name;
    ImageView img_que_previous,img_que_skip,img_que_next,img_que_quit;
    TextView tv_next,tv_previous,tv_skip,tv_quit;

    Dialog quit_game;
    Dialog wrong_question;

    //Api variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api4;
    List<Backend_Question> que_arr;

    //shared pref variables
    SharedPreferences sharedPreferences;
    private static final String shared_pref_name="my_pref";
    private static final String key_phone="phone";


    int x=0;
    Random random;
    int que_right_wrong=0;
    List<Backend_Question> dummy_arr_que;
    Backend_Question bck,bck2;
    Set <Integer> sequence;
    List<Result> list_results;
    Result res;


    String champ_name,Label_of_champ,game_mode,current_user_phone;
    int num_of_questions,time_for_quiz,bonus_coins;
    long timer;
    String ans_from_db,selected_option,string_of_selected_option;
    int coins_for_que;
    Boolean result;

    int num_of_true=0,coins_earned=0;
    double percentage;

    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_text);
        getSupportActionBar().hide(); //Code to remove Action Bar


        random=new Random();
        sequence=new HashSet<>();
        dummy_arr_que=new ArrayList<>();
        list_results=new ArrayList<Result>();

        quit_game = new Dialog(this);
        wrong_question =new Dialog(this);

        sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        current_user_phone=sharedPreferences.getString(key_phone,null);

        initialize();
        getQuestionFromApi();
        setTimer();
        btnTick();

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void getQuestionFromApi() {


        //getting values ,transferred from game mode page
        Label_of_champ=getIntent().getStringExtra("Label_of_champ");
        champ_name=getIntent().getStringExtra("champ_name");
        game_mode=getIntent().getStringExtra("game_mode");
        num_of_questions=getIntent().getIntExtra("num_of_questions",0);
        time_for_quiz=getIntent().getIntExtra("time_for_quiz",0);
        bonus_coins=getIntent().getIntExtra("bonus_coins",0);


        api4=RetrofitInstance.getRetrofit().create(Api.class);
        Call<Backend_QuestionWrapper> call= api4.getQuestion(Label_of_champ);

        call.enqueue(new Callback<Backend_QuestionWrapper>() {
            @Override
            public void onResponse(Call<Backend_QuestionWrapper> call, Response<Backend_QuestionWrapper> response) {

                que_arr=response.body().getQuestions();


                if(game_mode.equals("Quick Hit")) {

                    //adding random questions to dummy array
                    while(dummy_arr_que.size()!=2) {
                        int ranQue=random.nextInt(que_arr.size());
                        bck=que_arr.remove(ranQue);
                        dummy_arr_que.add(bck);
                    }

                    //adding data to list_result List
                    for(int i=0;i<dummy_arr_que.size();i++)
                    {
                        ans_from_db=dummy_arr_que.get(i).getAnswer().toString();
                        selected_option="no";
                        string_of_selected_option="no";
                        coins_for_que=dummy_arr_que.get(i).getCoins();
                        result=false;

                        res=new Result(ans_from_db,selected_option,string_of_selected_option,coins_for_que,result);
                        list_results.add(res);
                    }

                    //setting first display question
                    tv_game_mode_name.setText(game_mode+" : ");
                    tv_que_champ_name.setText(champ_name);
                    tv_que_number.setText("Q - "+(x+1)+"/2");
                    tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                    tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                    tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                    tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                    tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                    tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());




                    //if user clicked on next
                    img_que_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //storing selected option
                            if(btn1.getText().equals("✔")) {
                                String selected_ans=dummy_arr_que.get(x).getOption1();
                                list_results.get(x).setSelected_option("A");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true); }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn2.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption2();
                                list_results.get(x).setSelected_option("B");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn3.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption3();
                                list_results.get(x).setSelected_option("C");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn4.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption4();
                                list_results.get(x).setSelected_option("D");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }

                            if(x==dummy_arr_que.size()-2)
                            {
                                Toast.makeText(getApplicationContext(),"This is last question",Toast.LENGTH_SHORT).show();
                                tv_next.setText("Submit");

                            }

                            //code to change the question when user click on next
                            //if current question is last question then move to next page after that else change the question
                            if(x==dummy_arr_que.size()-1) {

                                //code here to go on result page

                                //calculating result-------------------------------------------------------------

                                //calculate total number of correct questions and store it in num_of_true
                                for(int i=0;i<list_results.size();i++)
                                {
                                    if(list_results.get(i).result==true)
                                    {
                                        num_of_true++;
                                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                                    }
                                }

                                percentage=num_of_true/(double)list_results.size();
                                percentage=percentage*100;

                                countDownTimer.cancel();

                                //if current user is signed in then only show result otherwise go to sign in page
                                if(current_user_phone!=null)
                                {
                                    Intent i=new Intent(getApplicationContext(),CalculateResult.class);
                                    i.putExtra("num_of_true",num_of_true);
                                    i.putExtra("coins_earned",coins_earned);
                                    i.putExtra("percentage",percentage);
                                    i.putExtra("from_page","with_flow");
                                    i.putExtra("bonus_coins",bonus_coins);
                                    i.putExtra("total_que",list_results.size());
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Please sign in first to play championships",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                }




                            }
                            else {

                                x++;

                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q - "+(x+1)+"/2");
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on next or restore answer if user revisited question
                            if(list_results.get(x).getSelected_option().equals("no")) {

                                //code to clear all options if user visit the question for the first time
                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }
                            }
                            else {

                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }

                                //code to restore option if user revisited question
                                String stored_opt=list_results.get(x).getSelected_option().toString();
                                if (btn1.getText().equals(stored_opt)) {
                                    btn1.setText("✔");
                                }
                                else if(btn2.getText().equals(stored_opt)) {
                                    btn2.setText("✔");
                                }
                                else if(btn3.getText().equals(stored_opt)) {
                                    btn3.setText("✔");
                                }
                                else if(btn4.getText().equals(stored_opt)) {
                                    btn4.setText("✔");
                                }
                            }

                        }
                    });


                    //if user clicked on skip
                    img_que_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(x==dummy_arr_que.size()-2) {
                                Toast.makeText(getApplicationContext(),"This is last question",Toast.LENGTH_SHORT).show();
                                tv_next.setText("Submit");

                            }

                            //code to change the question when user click on skip
                            if(x==dummy_arr_que.size()-1) {
                                //code here to go on result page

                                //calculating result-------------------------------------------------------------

                                //calculate total number of correct questions and store it in num_of_true
                                for(int i=0;i<list_results.size();i++)
                                {
                                    if(list_results.get(i).result==true)
                                    {
                                        num_of_true++;
                                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                                    }
                                }

                                percentage=num_of_true/(double)list_results.size();
                                percentage=percentage*100;


                                //if current user is signed in then only show result otherwise go to sign in page
                                if(current_user_phone!=null)
                                {
                                    Intent i=new Intent(getApplicationContext(),CalculateResult.class);
                                    i.putExtra("num_of_true",num_of_true);
                                    i.putExtra("coins_earned",coins_earned);
                                    i.putExtra("percentage",percentage);
                                    i.putExtra("from_page","with_flow");
                                    i.putExtra("bonus_coins",bonus_coins);
                                    i.putExtra("total_que",list_results.size());
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Please sign in first to play championships",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                }
                            }
                            else {
                                x++;
                                tv_que_number.setText("Q - "+(x+1)+"/2");
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on skip or restore answer if user revisited question
                            if(list_results.get(x).getSelected_option().equals("no")) {

                                //code to clear all options if user visit the question for the first time
                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }
                            }
                            else {

                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }

                                //code to restore option if user revisited question
                                String stored_opt=list_results.get(x).getSelected_option().toString();
                                if (btn1.getText().equals(stored_opt)) {
                                    btn1.setText("✔");
                                }
                                else if(btn2.getText().equals(stored_opt)) {
                                    btn2.setText("✔");
                                }
                                else if(btn3.getText().equals(stored_opt)) {
                                    btn3.setText("✔");
                                }
                                else if(btn4.getText().equals(stored_opt)) {
                                    btn4.setText("✔");
                                }
                            }


                        }
                    });


                    //if user clicked om previous
                    img_que_previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //code to change the question when user click on previous
                            if(x==0) {
                                //code to disable previous button
                                Toast.makeText(getApplicationContext(),"This is first question",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                x--;
                                tv_que_number.setText("Q - "+(x+1)+"/2");
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on previous
                            if(btn1.getText().equals("✔")) {
                                btn1.setText("A");
                            }
                            if(btn2.getText().equals("✔")) {
                                btn2.setText("B");
                            }
                            if(btn3.getText().equals("✔")) {
                                btn3.setText("C");
                            }
                            if(btn4.getText().equals("✔")) {
                                btn4.setText("D");
                            }

                            //restoring answer ticked by user when user click on previous question
                            String stored_opt=list_results.get(x).getSelected_option().toString();
                            if (btn1.getText().equals(stored_opt)) {
                                btn1.setText("✔");
                            }
                            else if(btn2.getText().equals(stored_opt)) {
                                btn2.setText("✔");
                            }
                            else if(btn3.getText().equals(stored_opt)) {
                                btn3.setText("✔");
                            }
                            else if(btn4.getText().equals(stored_opt)) {
                                btn4.setText("✔");
                            }




                        }
                    });
                  }


                else if(game_mode.equals("Select Gift and Play")) {

                    //adding random questions to dummy array
                    while(dummy_arr_que.size()!=num_of_questions) {
                        int ranQue=random.nextInt(que_arr.size());
                        bck=que_arr.remove(ranQue);
                        dummy_arr_que.add(bck);

                    }

                    //adding data to list_result List
                    for(int i=0;i<dummy_arr_que.size();i++)
                    {
                        ans_from_db=dummy_arr_que.get(i).getAnswer().toString();
                        selected_option="no";
                        string_of_selected_option="no";
                        coins_for_que=dummy_arr_que.get(i).getCoins();
                        result=false;

                        res=new Result(ans_from_db,selected_option,string_of_selected_option,coins_for_que,result);
                        list_results.add(res);
                    }


                    //setting first display question
                    tv_game_mode_name.setText(game_mode+" : ");
                    tv_que_champ_name.setText(champ_name);
                    tv_que_number.setText("Q - "+(x+1)+"/"+num_of_questions);
                    tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                    tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                    tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                    tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                    tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                    tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());



                    //if user clicked on next
                    img_que_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //storing selected option
                            if(btn1.getText().equals("✔")) {
                                String selected_ans=dummy_arr_que.get(x).getOption1();
                                list_results.get(x).setSelected_option("A");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true); }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn2.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption2();
                                list_results.get(x).setSelected_option("B");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn3.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption3();
                                list_results.get(x).setSelected_option("C");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }
                            else if(btn4.getText().equals("✔")) {

                                String selected_ans=dummy_arr_que.get(x).getOption4();
                                list_results.get(x).setSelected_option("D");
                                list_results.get(x).setString_of_selected_option(selected_ans);

                                //if selected answer is correct then store true in result of list_result
                                if(list_results.get(x).getAns_from_db().equals(list_results.get(x).getString_of_selected_option())) {
                                    list_results.get(x).setResult(true);
                                }
                                else {
                                    list_results.get(x).setResult(false);
                                }
                            }

                            if(x==dummy_arr_que.size()-2) {
                                Toast.makeText(getApplicationContext(),"This is last question",Toast.LENGTH_SHORT).show();
                                tv_next.setText("Submit");

                            }

                            //code to change the question when user click on next
                            if(x==dummy_arr_que.size()-1) {
                                //code here to go on result page

                                //calculating result-------------------------------------------------------------

                                //calculate total number of correct questions and store it in num_of_true
                                for(int i=0;i<list_results.size();i++)
                                {
                                    if(list_results.get(i).result==true)
                                    {
                                        num_of_true++;
                                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                                    }
                                }

                                percentage=num_of_true/(double)list_results.size();
                                percentage=percentage*100;

                                //if current user is signed in then only show result otherwise go to sign in page
                                if(current_user_phone!=null)
                                {
                                    Intent i=new Intent(getApplicationContext(),CalculateResult.class);
                                    i.putExtra("num_of_true",num_of_true);
                                    i.putExtra("coins_earned",coins_earned);
                                    i.putExtra("percentage",percentage);
                                    i.putExtra("from_page","with_flow");
                                    i.putExtra("bonus_coins",bonus_coins);
                                    i.putExtra("total_que",list_results.size());
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Please sign in first to play championships",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                }
                            }
                            else {
                                x++;

                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q -"+(x+1)+"/"+num_of_questions);
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on next or restore answer if user revisited question
                            if(list_results.get(x).getSelected_option().equals("no")) {

                                //code to clear all options if user visit the question for the first time
                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }
                            }
                            else {

                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }

                                //code to restore option if user revisited question
                                String stored_opt=list_results.get(x).getSelected_option().toString();
                                if (btn1.getText().equals(stored_opt)) {
                                    btn1.setText("✔");
                                }
                                else if(btn2.getText().equals(stored_opt)) {
                                    btn2.setText("✔");
                                }
                                else if(btn3.getText().equals(stored_opt)) {
                                    btn3.setText("✔");
                                }
                                else if(btn4.getText().equals(stored_opt)) {
                                    btn4.setText("✔");
                                }
                            }
                        }
                    });


                    //if user clicked on skip
                    img_que_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(x==dummy_arr_que.size()-2) {
                                Toast.makeText(getApplicationContext(),"This is last question",Toast.LENGTH_SHORT).show();
                                tv_next.setText("Submit");

                            }

                            //code to change the question when user click on skip
                            if(x==dummy_arr_que.size()-1) {
                                //code here to go on result page

                                //calculating result-------------------------------------------------------------

                                //calculate total number of correct questions and store it in num_of_true
                                for(int i=0;i<list_results.size();i++)
                                {
                                    if(list_results.get(i).result==true)
                                    {
                                        num_of_true++;
                                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                                    }
                                }

                                percentage=num_of_true/(double)list_results.size();
                                percentage=percentage*100;


                                //if current user is signed in then only show result otherwise go to sign in page
                                if(current_user_phone!=null)
                                {
                                    Intent i=new Intent(getApplicationContext(),CalculateResult.class);
                                    i.putExtra("num_of_true",num_of_true);
                                    i.putExtra("coins_earned",coins_earned);
                                    i.putExtra("percentage",percentage);
                                    i.putExtra("from_page","with_flow");
                                    i.putExtra("bonus_coins",bonus_coins);
                                    i.putExtra("total_que",list_results.size());
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Please sign in first to play championships",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                }
                            }
                            else {
                                x++;
                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q -"+(x+1)+"/"+num_of_questions);
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on skip or restore answer if user revisited question
                            if(list_results.get(x).getSelected_option().equals("no")) {

                                //code to clear all options if user visit the question for the first time
                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }
                            }
                            else {

                                if(btn1.getText().equals("✔")) {
                                    btn1.setText("A");
                                }
                                if(btn2.getText().equals("✔")) {
                                    btn2.setText("B");
                                }
                                if(btn3.getText().equals("✔")) {
                                    btn3.setText("C");
                                }
                                if(btn4.getText().equals("✔")) {
                                    btn4.setText("D");
                                }

                                //code to restore option if user revisited question
                                String stored_opt=list_results.get(x).getSelected_option().toString();
                                if (btn1.getText().equals(stored_opt)) {
                                    btn1.setText("✔");
                                }
                                else if(btn2.getText().equals(stored_opt)) {
                                    btn2.setText("✔");
                                }
                                else if(btn3.getText().equals(stored_opt)) {
                                    btn3.setText("✔");
                                }
                                else if(btn4.getText().equals(stored_opt)) {
                                    btn4.setText("✔");
                                }
                            }


                        }
                    });


                    //if user clicked on previous
                    img_que_previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //code to change the question when user click on previous
                            if(x==0) {
                                //code to disable previous button
                                Toast.makeText(getApplicationContext(),"This is first question",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                x--;

                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q -"+(x+1)+"/"+num_of_questions);
                                tv_que_coins.setText(""+dummy_arr_que.get(x).getCoins());
                                tv_que.setText(dummy_arr_que.get(x).getQuestion().toString());
                                tv_que_opt1.setText("A : "+dummy_arr_que.get(x).getOption1().toString());
                                tv_que_opt2.setText("B : "+dummy_arr_que.get(x).getOption2().toString());
                                tv_que_opt3.setText("C : "+dummy_arr_que.get(x).getOption3().toString());
                                tv_que_opt4.setText("D : "+dummy_arr_que.get(x).getOption4().toString());

                            }

                            //code to clear options when user click on previous
                            if(btn1.getText().equals("✔")) {
                                btn1.setText("A");
                            }
                            if(btn2.getText().equals("✔")) {
                                btn2.setText("B");
                            }
                            if(btn3.getText().equals("✔")) {
                                btn3.setText("C");
                            }
                            if(btn4.getText().equals("✔")) {
                                btn4.setText("D");
                            }

                            //restoring answer ticked by user when user click on previous question
                            String stored_opt=list_results.get(x).getSelected_option().toString();
                            if (btn1.getText().equals(stored_opt)) {
                                btn1.setText("✔");
                            }
                            else if(btn2.getText().equals(stored_opt)) {
                                btn2.setText("✔");
                            }
                            else if(btn3.getText().equals(stored_opt)) {
                                btn3.setText("✔");
                            }
                            else if(btn4.getText().equals(stored_opt)) {
                                btn4.setText("✔");
                            }
                        }
                    });
                }
                }


            @Override
            public void onFailure(Call<Backend_QuestionWrapper> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void btnTick() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    btn1.setText("✔");
                if (btn1.getText().equals("A"))
                {
                    btn1.setText("✔");

                }
                else if (btn1.getText().equals("✔"))
                {
                    btn1.setText("A");

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    btn1.setText("✔");
                if (btn2.getText().equals("B"))
                {
                    btn2.setText("✔");

                }
                else if (btn2.getText().equals("✔"))
                {
                    btn2.setText("B");

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    btn1.setText("✔");
                if (btn3.getText().equals("C"))
                {
                    btn3.setText("✔");

                }
                else if (btn3.getText().equals("✔"))
                {
                    btn3.setText("C");

                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    btn1.setText("✔");
                if (btn4.getText().equals("D"))
                {
                    btn4.setText("✔");

                }
                else if (btn4.getText().equals("✔"))
                {
                    btn4.setText("D");

                }
            }
        });
    }

    private void initialize() {

        txt_timer=(TextView) findViewById(R.id.txt_timer);
        btn1 = (TextView) findViewById(R.id.button1);
        btn2 = (TextView) findViewById(R.id.button2);
        btn3 = (TextView) findViewById(R.id.button3);
        btn4 = (TextView) findViewById(R.id.button4);
        btn5 = (TextView) findViewById(R.id.button5);

        tv_que_coins=(TextView) findViewById(R.id.tv_que_coins);
        tv_que=(TextView) findViewById(R.id.tv_que);
        tv_que_opt1=(TextView)findViewById(R.id.tv_que_opt1);
        tv_que_opt2=(TextView)findViewById(R.id.tv_que_opt2);
        tv_que_opt3=(TextView)findViewById(R.id.tv_que_opt3);
        tv_que_opt4=(TextView)findViewById(R.id.tv_que_opt4);
        tv_que_number=(TextView)findViewById(R.id.tv_que_number);
        tv_que_champ_name=(TextView)findViewById(R.id.tv_que_champ_name);
        tv_game_mode_name=(TextView)findViewById(R.id.tv_game_mode_name);
        img_que_previous=(ImageView) findViewById(R.id.img_que_previous);
        img_que_skip=(ImageView) findViewById(R.id.img_que_skip);
        img_que_next=(ImageView) findViewById(R.id.img_que_next);
        img_que_quit=(ImageView) findViewById(R.id.img_que_quit);

        tv_next=(TextView) findViewById(R.id.tv_next);
        tv_previous=(TextView) findViewById(R.id.tv_previous);
        tv_skip=(TextView) findViewById(R.id.tv_skip);
        tv_quit=(TextView) findViewById(R.id.tv_quit);



    }

    private void setTimer() {

        if(game_mode.equals("Quick Hit"))
        {
            timer=2;
        }
        else if(game_mode.equals("Select Gift and Play"))
        {
            timer=time_for_quiz;
        }

        long duration= TimeUnit.MINUTES.toMillis(timer);            //Initialize time duration

        countDownTimer=new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {


                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                txt_timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

            }



            @Override
            public void onFinish()
            {
                //when finish
                //display toast

                //calculate total number of correct questions and store it in num_of_true
                for(int i=0;i<list_results.size();i++)
                {
                    if(list_results.get(i).result==true)
                    {
                        num_of_true++;
                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                    }
                }

                percentage=num_of_true/(double)list_results.size();
                percentage=percentage*100;

                Toast.makeText(getApplicationContext(), "Time Out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),CalculateResult.class);

                i.putExtra("num_of_true",num_of_true);
                i.putExtra("coins_earned",coins_earned);
                i.putExtra("percentage",percentage);
                i.putExtra("from_page","time_out");
                i.putExtra("bonus_coins",bonus_coins);
                i.putExtra("total_que",list_results.size());

                startActivity(i);
            }
        }.start();
    }

    public void ShowPopupquitgame(View v){
        Button btn_quit_yes,btn_quit_no;

        quit_game.setContentView(R.layout.quit_game_popup);
        btn_quit_yes =(Button) quit_game.findViewById(R.id.btn_quit_yes);
        btn_quit_no =(Button) quit_game.findViewById(R.id.btn_quit_no);

        btn_quit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit_game.dismiss();
            }
        });
        quit_game.show();

        btn_quit_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calculate total number of correct questions and store it in num_of_true
                for(int i=0;i<list_results.size();i++)
                {
                    if(list_results.get(i).result==true)
                    {
                        num_of_true++;
                        coins_earned=coins_earned+list_results.get(i).getCoins_for_que();
                    }
                }

                percentage=num_of_true/(double)list_results.size();
                percentage=percentage*100;

                countDownTimer.cancel();

                Intent intent =new Intent(QuestionText.this,CalculateResult.class);
                intent.putExtra("from_page","quit_page");
                intent.putExtra("bonus_coins",bonus_coins);
                intent.putExtra("num_of_true",num_of_true);
                intent.putExtra("total_que",list_results.size());
                startActivity(intent);

            }
        });

    }

    public void ShowPopupwrongque(View v){
        Button btn_wrong_yes,btn_wrong_no;

        wrong_question.setContentView(R.layout.wrong_que_popup);
        btn_wrong_yes =(Button) wrong_question.findViewById(R.id.btn_wrong_yes);
        btn_wrong_no =(Button) wrong_question.findViewById(R.id.btn_wrong_no);

        btn_wrong_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wrong_question.dismiss();
            }
        });
        wrong_question.show();

        btn_wrong_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wrong_question.dismiss();

                que_right_wrong=1;
                bck2=dummy_arr_que.get(x);
                bck2.setWrong_que(que_right_wrong);
                postQueRightWrongResult(bck2);

                Toast.makeText(QuestionText.this,"We will check at out end,Click On Next Button",Toast.LENGTH_SHORT).show();

            }
        });
        wrong_question.show();

    }

    private void postQueRightWrongResult(Backend_Question bck2) {



        Call<Backend_Question> backend_questionCall= api4.setQueRightWrong(bck2);
        backend_questionCall.enqueue(new Callback<Backend_Question>() {
            @Override
            public void onResponse(Call<Backend_Question> call, Response<Backend_Question> response) {

                System.out.println(response);
            }

            @Override
            public void onFailure(Call<Backend_Question> call, Throwable t) {

            }
        });


    }


}