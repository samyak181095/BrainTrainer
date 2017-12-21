package com.sammy.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    RelativeLayout gameLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    int locationOfCorrectAnswer;
    int score = 0;
    int questions = 0;

    public void playAgain(View view) {
        score = 0;
        questions = 0;

        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }
            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(questions));
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);


            }
        }.start();
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }

    public void generateQuestions() {
        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int intCorrectAnswer;

        for(int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                intCorrectAnswer = rand.nextInt(101);

                while(intCorrectAnswer == a + b) {
                    intCorrectAnswer = rand.nextInt(101);
                }
                answers.add(intCorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else {
            resultTextView.setText("Incorrect!");
        }
        questions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(questions));
        generateQuestions();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgain = (Button)findViewById(R.id.playAgainButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gameLayout = (RelativeLayout)findViewById(R.id.gameLayout);
    }
}
