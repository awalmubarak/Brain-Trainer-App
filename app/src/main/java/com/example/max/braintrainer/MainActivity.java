package com.example.max.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView answerText, questionText, scoreText, timerText;
    boolean isActive = true;
    Button button1, button2, button3, button4, tryagain, play;
    int answer;
    int totalQuestion = 0;
    int correctAnswer= 0;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = (TextView) findViewById(R.id.answerText);
        questionText = (TextView) findViewById(R.id.questionText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        timerText = (TextView) findViewById(R.id.timerText);
        relativeLayout = (RelativeLayout) findViewById(R.id.myRelative);

        scoreText.setText("0/0");

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        tryagain = (Button) findViewById(R.id.tryagain);
        play = (Button) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                startGame();

            }
        });



    }


    public void setQuestion(){

        //generate random numbers to add

        Random ran = new Random();
        int num1 = ran.nextInt(99) + 1;
        int num2 = ran.nextInt(99) + 1;

        //add random numbers to get answer
        answer = num1 + num2;


        //set Question on TextView
        questionText.setText(String.valueOf(num1) + " + " + String.valueOf(num2));


        //get random button for answer
        int ansButton = ran.nextInt(4) + 1;



        //check which random number we got and set answer to button based on number
        if (ansButton == 1){
            button1.setText(String.valueOf(answer));
            button1.setTag(String.valueOf(answer));

            button2.setText(String.valueOf(ran.nextInt(200) + 1));
            button2.setTag(String.valueOf(ran.nextInt(200) + 1));

            button3.setText(String.valueOf(ran.nextInt(200) + 1));
            button3.setTag(String.valueOf(ran.nextInt(200) + 1));

            button4.setText(String.valueOf(ran.nextInt(200) + 1));
            button4.setTag(String.valueOf(ran.nextInt(200) + 1));

        }else if(ansButton == 2){

            button1.setText(String.valueOf(ran.nextInt(200) + 1));
            button1.setTag(String.valueOf(ran.nextInt(200) + 1));

            button2.setText(String.valueOf(answer));
            button2.setTag(String.valueOf(answer));

            button3.setText(String.valueOf(ran.nextInt(200) + 1));
            button3.setTag(String.valueOf(ran.nextInt(200) + 1));

            button4.setText(String.valueOf(ran.nextInt(200) + 1));
            button4.setTag(String.valueOf(ran.nextInt(200) + 1));

        }else if(ansButton == 3){

            button1.setText(String.valueOf(ran.nextInt(200) + 1));
            button1.setTag(String.valueOf(ran.nextInt(200) + 1));

            button2.setText(String.valueOf(ran.nextInt(200) + 1));
            button2.setTag(String.valueOf(ran.nextInt(200) + 1));

            button3.setText(String.valueOf(answer));
            button3.setTag(String.valueOf(answer));

            button4.setText(String.valueOf(ran.nextInt(200) + 1));
            button4.setTag(String.valueOf(ran.nextInt(200) + 1));

        }else if (ansButton == 4){

            button1.setText(String.valueOf(ran.nextInt(200) + 1));
            button1.setTag(String.valueOf(ran.nextInt(200) + 1));

            button2.setText(String.valueOf(ran.nextInt(200) + 1));
            button2.setTag(String.valueOf(ran.nextInt(200) + 1));

            button3.setText(String.valueOf(ran.nextInt(200) + 1));
            button3.setTag(String.valueOf(ran.nextInt(200) + 1));

            button4.setText(String.valueOf(answer));
            button4.setTag(String.valueOf(answer));

        }

    }

    public void startGame(){

        setQuestion();

         new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (isActive) {
                    timerText.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                }
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                answerText.setText("Your Score: " + String.valueOf(correctAnswer) + "/" + String.valueOf(totalQuestion));
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                tryagain.setVisibility(View.VISIBLE);
                isActive = false;

            }
        }.start();

    }

    public void answered(View view){

        boolean isCorrect;

        //get button with the right answer
        String tag = view.getTag().toString();

        //check if button is equal to correct answer
        if (tag.equals(String.valueOf(answer))){
            isCorrect = true;
        }else{
            isCorrect = false;
        }

        //update score TextView
        if (isCorrect){
            answerText.setText("Correct!");
            ++correctAnswer;

        }else{
            answerText.setText("Wrong!");
        }

        ++totalQuestion;

        scoreText.setText(String.valueOf(correctAnswer) + "/" + String.valueOf(totalQuestion));
        setQuestion();

    }

    public void tryAgain(View view){

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        timerText.setText("30s");
        scoreText.setText("0/0");
        questionText.setText("Question");
        answerText.setText("");
        tryagain.setVisibility(View.GONE);
        isActive=true;
        totalQuestion = 0;
        correctAnswer = 0;
        startGame();

    }


}
