package com.example.x_o;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    int person = 1;
    boolean isGameActive = true;

    public void enterRing(View view){

        Button button = (Button) findViewById(R.id.button);
        ImageView image = (ImageView) view;
        int tappedCounter = Integer.parseInt(image.getTag().toString());

        if (gameState[tappedCounter] == 2 && isGameActive) {
            gameState[tappedCounter] = person;

            image.setTranslationY(-1500);
            if (person == 1) {
                image.setImageResource(R.drawable.purple_coin);
                image.animate().translationYBy(1500).setDuration(200);
                person = 0;
            } else {
                image.setImageResource(R.drawable.red_coin);
                image.animate().translationYBy(1500).setDuration(200);
                person = 1;
            }

            for (int[] winningPosition : winningPositions)
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                    if (gameState[winningPosition[0]] == 0) {
                        Toast.makeText(this, "Red Ring Wins...", Toast.LENGTH_SHORT).show();
                        MediaPlayer music = MediaPlayer.create(this,R.raw.appl);
                        music.start();
                        button.setVisibility(View.VISIBLE);
                        isGameActive = false;
                    } else {
                        Toast.makeText(this, "Purple Ring Wins...", Toast.LENGTH_SHORT).show();
                        MediaPlayer music = MediaPlayer.create(this,R.raw.appl);
                        music.start();
                        button.setVisibility(View.VISIBLE);
                        isGameActive = false;
                    }
                }

            int count = 0;
            for (int iter : gameState){
                if (iter==2)
                    count++;
            }

           if (count==0){
               Toast.makeText(this, "Its a Tie...", Toast.LENGTH_SHORT).show();
               button.setVisibility(View.VISIBLE);
               isGameActive = false;
           }
        }
    }

    public void refresh(){
        ImageView image1 = (ImageView) findViewById(R.id.imageView);
        ImageView image2 = (ImageView) findViewById(R.id.imageView2);
        ImageView image3 = (ImageView) findViewById(R.id.imageView3);
        ImageView image4 = (ImageView) findViewById(R.id.imageView4);
        ImageView image5 = (ImageView) findViewById(R.id.imageView5);
        ImageView image6 = (ImageView) findViewById(R.id.imageView6);
        ImageView image7 = (ImageView) findViewById(R.id.imageView7);
        ImageView image8 = (ImageView) findViewById(R.id.imageView8);
        ImageView image9 = (ImageView) findViewById(R.id.imageView9);

        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);
    }

    public void playAgain(View view){

        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        MediaPlayer music = MediaPlayer.create(this,R.raw.appl);
        music.pause();

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_Layout);
//        for (int i = 0; i < gridLayout.getChildCount();i++){
//            ImageView count = (ImageView) gridLayout.getChildAt(i);
//            count.setImageDrawable(null);
//        }
//
        for (int z = 0; z < gameState.length;z++){
            gameState[z] = 2;
        }
        person = 1;
        isGameActive = true;
        refresh();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}