package com.example.neenjah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private ImageView neenjah, slime,rocket, coin1,coin2,coin3,heart1,heart2,heart3;
    private TextView textViewScore, textViewInfo;
    private ConstraintLayout constraintLayout;

    //music sound fx
    private MediaPlayer CoinSoundEffect;
    private MediaPlayer EnemySoundEffect;
    private MediaPlayer gameMusic;



    //touch screen
    private boolean touch = false;
    private boolean begin = false;
    private Runnable runnable;
    private Handler handler;

    //screen dim
    int screenWidth;
    int screenHeight;

    //lives left
    int lives = 3;

    //points
    int score = 0;


    //neejah and enemy positions
    int neenjahXPosition, slimeX, rocketX, coin1X, coin2X;
    int neenjahYPosition, slimeY, rocketY,coin1Y, coin2Y;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//find views
        neenjah = findViewById(R.id.imageViewNeenjah);
        slime = findViewById(R.id.imageViewSlime);
        rocket = findViewById(R.id.imageViewRocket);
        coin1 = findViewById(R.id.imageViewCoin1);
        coin2 = findViewById(R.id.imageViewCoin2);
        coin3 = findViewById(R.id.imageViewCoin3);
        heart1 = findViewById(R.id.Heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.Heart3);
        textViewScore =findViewById(R.id.textViewScore);
        textViewInfo = findViewById(R.id.textViewStartInfo);
        constraintLayout = findViewById(R.id.constraintLayout);


        gameMusic = gameMusic.create(GameActivity.this, R.raw.lofi);
        gameMusic.start();


//when user touches screen
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // tap to play info becomes invisible
                textViewInfo.setVisibility(View.INVISIBLE);

                if (!begin){
                    //when screen is touched ...
                    begin = true;

                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight= (int) constraintLayout.getHeight();
                    neenjahXPosition = (int) neenjah.getX();
                    neenjahYPosition = (int ) neenjah.getY();


                    //handler method runs
                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            moveNeenjah();
                            moveEnemy();
                            collision();

                        }

                    };
                    handler.post(runnable);
                }else{

                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        touch = true;

                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        touch = false;

                    }
                }

                //win
                  if (lives > 0 && score >= 300){
                    handler.removeCallbacks(runnable);
                    gameMusic.reset();
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }


                return true;
            }
        });
    }

    public void moveNeenjah(){

        //if screen is touched. neenjah moves up depending on screen height and width
        if(touch) {
            neenjahYPosition = neenjahYPosition - (screenHeight/ 50);
        } else {
            neenjahYPosition = neenjahYPosition + (screenHeight / 50);
        }

        // prevents neenjah from moving off screen on the Y axis
        if (neenjahYPosition <= 0 ){
            neenjahYPosition = 0;
        }
        if (neenjahYPosition >= (screenHeight - neenjah.getHeight())){

            neenjahYPosition = (screenHeight - neenjah.getHeight());
        }

        //set Y position to neenJAH
        neenjah.setY(neenjahYPosition);



    }

    public void moveEnemy(){
        //enemy become visible
        slime.setVisibility(View.VISIBLE);
        rocket.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        //Enemy position on screen
        slimeX = slimeX - (screenWidth / 100);

        //if the slime enemy gets to the 0 x axis then it reappears on a different position on the y axis
       if (slimeX < 0){
            slimeX = screenWidth + 200;
            slimeY = (int)Math.floor(Math.random() * screenHeight);

            if (slimeY <= 0 ){
                slimeY = 0;
            }
            if (slimeY >= (screenHeight - slime.getHeight())){

                slimeY = (screenHeight - slime.getHeight());
            }


        }

        slime.setX(slimeX);
        slime.setY(slimeY);


        //Enemy rocket
        rocketX = rocketX - (screenWidth / 130);

        //if the slime enemy gets to the 0 x axis then it reappears on a different position on the y axis
        if (rocketX < 0){
            rocketX = screenWidth + 200;
            rocketY = (int)Math.floor(Math.random() * screenHeight);

            if (rocketY <= 0 ){
                rocketY = 0;
            }
            if (rocketY >= (screenHeight - slime.getHeight())){

                rocketY = (screenHeight - slime.getHeight());
            }


        }
        rocket.setX(rocketX);
        rocket.setY(rocketY);

        //coin


        coin1X = coin1X - (screenWidth / 180);

        //if the slime enemy gets to the 0 x axis then it reappears on a different position on the y axis
        if (coin1X < 0){
            coin1X = screenWidth + 200;
            coin1Y = (int)Math.floor(Math.random() * screenHeight);

            if (coin1Y <= 0 ){
                coin1Y = 0;
            }
            if (coin1Y >= (screenHeight - slime.getHeight())){

                coin1Y = (screenHeight - slime.getHeight());
            }


        }

        coin1.setX(coin1X);
        coin1.setY(coin1Y);

        //coin 2

        coin2X = coin2X - (screenWidth / 150);

        //if the slime enemy gets to the 0 x axis then it reappears on a different position on the y axis
        if (coin2X < 0){
            coin2X = screenWidth + 200;
            coin2Y = (int)Math.floor(Math.random() * screenHeight);

            if (coin2Y <= 0 ){
                coin2Y = 0;
            }
            if (coin2Y >= (screenHeight - slime.getHeight())){

                coin2Y = (screenHeight - slime.getHeight());
            }


        }

        coin2.setX(coin2X);
        coin2.setY(coin2Y);
    }


    public void collision() {

//slime
        int centerSlimeX = slimeX + slime.getWidth() / 2;
        int centerSlimeY = slimeY + slime.getHeight() / 2;


        if (centerSlimeX >= neenjahXPosition
                && centerSlimeX <= (neenjahXPosition + neenjah.getWidth())
                && centerSlimeY >= neenjahYPosition
                && centerSlimeY <= (neenjahYPosition + neenjah.getHeight())) {

            //when the collision happens
            EnemySoundEffect = EnemySoundEffect.create(GameActivity.this, R.raw.enemy);
            EnemySoundEffect.start();
            slimeX = screenWidth + 200;
            lives--;

        }

// rocket

        int centerRocketX = rocketX + rocket.getWidth() / 2;
        int centerRocketY = rocketY + rocket.getHeight() / 2;


        if (centerRocketX >= neenjahXPosition
                && centerRocketX <= (neenjahXPosition + neenjah.getWidth())
                && centerRocketY >= neenjahYPosition
                && centerRocketY <= (neenjahYPosition + neenjah.getHeight())) {

            //when the collision happens
            EnemySoundEffect = EnemySoundEffect.create(GameActivity.this, R.raw.enemy);
            EnemySoundEffect.start();
            rocketX = screenWidth + 200;
            lives--;
        }

//coin 1

        int centerCoin1X = coin1X + coin1.getWidth() / 2;
        int centerCoin1Y = coin1Y + coin1.getHeight() / 2;


        if (centerCoin1X >= neenjahXPosition
                && centerCoin1X <= (neenjahXPosition + neenjah.getWidth())
                && centerCoin1Y >= neenjahYPosition
                && centerCoin1Y <= (neenjahYPosition + neenjah.getHeight())) {

            //when the collision happens
            coin1X = screenWidth + 200;
            CoinSoundEffect = CoinSoundEffect.create(GameActivity.this, R.raw.coin);
            CoinSoundEffect.start();
            score = score + 10;
            textViewScore.setText("" + score);

        }


//coin 2
        int centerCoin2X = coin2X + coin2.getWidth() / 2;
        int centerCoin2Y = coin2Y + coin2.getHeight() / 2;


        if (centerCoin2X >= neenjahXPosition
                && centerCoin2X <= (neenjahXPosition + neenjah.getWidth())
                && centerCoin2Y >= neenjahYPosition
                && centerCoin2Y <= (neenjahYPosition + neenjah.getHeight())) {

            //when the collision happens
            coin2X = screenWidth + 200;
            CoinSoundEffect = CoinSoundEffect.create(GameActivity.this, R.raw.coin);
            CoinSoundEffect.start();
            score = score + 10;
            textViewScore.setText("" + score);

        }

           // if lives above zero and score is 300 you win
        if (lives > 0 ) {


            if (lives == 2) {
                heart1.setVisibility(View.INVISIBLE);
            }

            if (lives == 1) {
                heart2.setVisibility(View.INVISIBLE);
            }
            handler.postDelayed(runnable, 20);

        }

// lose condition
        else if (lives == 0) {

            handler.removeCallbacks(runnable);
            heart3.setVisibility(View.INVISIBLE);
            gameMusic.reset();
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        else {

        }



    } //collision()



}

