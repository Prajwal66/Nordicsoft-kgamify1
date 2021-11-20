package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestionText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_text);
        getSupportActionBar().hide(); //Code to remove Action Bar
    }
}