package com.example.kgamify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class QuestionText extends AppCompatActivity {

    TextView txt_timer;
    TextView btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_text);
        getSupportActionBar().hide(); //Code to remove Action Bar

        settimer();

        txt_timer=(TextView) findViewById(R.id.txt_timer);
        btn1 = (TextView) findViewById(R.id.button1);
        btn2 = (TextView) findViewById(R.id.button2);
        btn3 = (TextView) findViewById(R.id.button3);
        btn4 = (TextView) findViewById(R.id.button4);
        btn5 = (TextView) findViewById(R.id.button5);

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


    private void settimer() {
        long duration= TimeUnit.MINUTES.toMillis(1);            //Initialize time duration
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