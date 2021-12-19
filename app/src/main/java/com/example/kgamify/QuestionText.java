package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    //Api variables
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api4;
    List<Backend_Question> que_arr;


    int x=0;
    Random random;
    List<Backend_Question> dummy_arr_que;
    Backend_Question bck;
    Set <Integer> sequence;
    List<Result> list_results;
    Result res;


    String champ_name,Label_of_champ,game_mode;
    int num_of_questions,time_for_quiz;
    long timer;
    String ans_from_db,selected_option,string_of_selected_option;
    Boolean result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_text);

        getSupportActionBar().hide(); //Code to remove Action Bar
        random=new Random();
        sequence=new HashSet<>();
        dummy_arr_que=new ArrayList<>();
        list_results=new ArrayList<Result>();






        initialize();
        Toast.makeText(getApplicationContext(),"Page opened",Toast.LENGTH_SHORT).show();
        getQuestionFromApi();
        setTimer();
        btnTick();

    }

    private void getQuestionFromApi() {


        //getting values ,transferred from game mode page
        Label_of_champ=getIntent().getStringExtra("Label_of_champ");
        champ_name=getIntent().getStringExtra("champ_name");
        game_mode=getIntent().getStringExtra("game_mode");
        num_of_questions=getIntent().getIntExtra("num_of_questions",0);
        time_for_quiz=getIntent().getIntExtra("time_for_quiz",0);



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
                        result=false;

                        res=new Result(ans_from_db,selected_option,string_of_selected_option,result);
                        list_results.add(res);
                    }

                    //setting first display question
                    tv_game_mode_name.setText(game_mode+" : ");
                    tv_que_champ_name.setText(champ_name);
                    tv_que_number.setText("Q - "+(x+1)+"/2");
                    tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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


                            //code to change the question when user click on next
                            if(x==dummy_arr_que.size()-1) {
                                //make next button disable
                                //code here to go on result page
                                Toast.makeText(getApplicationContext(),"Last que arrived,next disabled",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                x++;

                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q - "+(x+1)+"/2");
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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

                            //code to change the question when user click on skip
                            if(x==dummy_arr_que.size()-1) {
                                //make next button disable
                                //code here to go on result page
                                Toast.makeText(getApplicationContext(),"Last que arrived,Skip will go to result",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                x++;
                                tv_que_number.setText("Q - "+(x+1)+"/2");
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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
                        result=false;

                        res=new Result(ans_from_db,selected_option,string_of_selected_option,result);
                        list_results.add(res);
                    }


                    //setting first display question
                    tv_game_mode_name.setText(game_mode+" : ");
                    tv_que_champ_name.setText(champ_name);
                    tv_que_number.setText("Q - "+(x+1)+"/"+num_of_questions);
                    tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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

                            //code to change the question when user click on next
                            if(x==dummy_arr_que.size()-1) {
                                //make next button disable
                                //code here to go on result page
                                Toast.makeText(getApplicationContext(),"Last que arrived,next disabled",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                x++;

                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q -"+(x+1)+"/"+num_of_questions);
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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

                            //code to change the question when user click on skip
                            if(x==dummy_arr_que.size()-1) {
                                //make next button disable
                                //code here to go on result page
                                Toast.makeText(getApplicationContext(),"Last que arrived,Skip will go to result",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                x++;
                                tv_game_mode_name.setText(game_mode+" : ");
                                tv_que_champ_name.setText(champ_name);
                                tv_que_number.setText("Q -"+(x+1)+"/"+num_of_questions);
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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
                                tv_que_coins.setText(dummy_arr_que.get(x).getCoins().toString());
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


    }


    private void setTimer() {

        if(game_mode.equals("Quick_hit"))
        {
            timer=2;
        }
        else if(game_mode.equals("Select_gift"))
        {
            timer=time_for_quiz;
        }

        long duration= TimeUnit.MINUTES.toMillis(timer);            //Initialize time duration
        new CountDownTimer(duration, 1000) {
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
                Toast.makeText(getApplicationContext(), "Time Out", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

}