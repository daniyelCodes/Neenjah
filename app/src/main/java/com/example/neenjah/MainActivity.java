package com.example.neenjah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    //Views
  private ImageView neenjah , slime , rocket, coin;
  private Button buttonStart;
  private Animation animation;
  private  MediaPlayer WelcomeMusic;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// get views by its id
    neenjah = findViewById(R.id.playerNinja);
    slime = findViewById(R.id.enemySlime);
    rocket = findViewById(R.id.enemyRocket);
    coin = findViewById(R.id.coin);
    buttonStart = findViewById(R.id.buttonStart);

      //music
      WelcomeMusic = WelcomeMusic.create(MainActivity.this, R.raw.lofi);
      WelcomeMusic.start();
      WelcomeMusic.setVolume(1,1);

    //create animation object
    animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_animation);

    //starts animation as soon as activity loads
      neenjah.setAnimation(animation);
      slime.setAnimation(animation);
      rocket.setAnimation(animation);
      coin.setAnimation(animation);
      buttonStart.setAnimation(animation);




      //Start button goes to the next activity

      buttonStart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          WelcomeMusic.reset();
          Intent intent = new Intent(MainActivity.this, GameActivity.class);
          startActivity(intent);
        }
      });

    }


}