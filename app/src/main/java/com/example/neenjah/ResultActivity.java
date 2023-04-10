package com.example.neenjah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewResultInfo,textViewResultInfo2;
    private Button playAgainBtn;
    int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//views
        textViewResultInfo = findViewById(R.id.textViewResultinfo);
        textViewResultInfo2 = findViewById(R.id.textViewResultinfo2);
        playAgainBtn = findViewById(R.id.play_again_button);


        score =  getIntent().getIntExtra("score", 0);
        textViewResultInfo2.setText("Your Score :" + score);



        if (score >= 300){
            textViewResultInfo.setText("YOU WIN");
        }
        else {
            textViewResultInfo.setText("You Lose");
        }



        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }




}