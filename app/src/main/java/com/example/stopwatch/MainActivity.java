package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0 ;
    private boolean running ;
    ImageView anchor ;
    Animation roudingAlone ;
    Button btnStart , btnStop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart_ID);
        btnStop = findViewById(R.id.btnStop_ID);
        btnStop.setAlpha(0);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds") ;
            running = savedInstanceState.getBoolean("running ") ;
        } // end if
        runTimer();
    } // end onCreate()

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(" seconds " ,seconds );
        savedInstanceState.putBoolean(" running " , running);
    } // end onSaveInstanceState()

    public void onClockStart(View view ){
        running = true ;
        animation();
        btnStop.animate().alpha(1).translationY(-80).setDuration(3000).start();
        btnStart.animate().alpha(0).setDuration(3000).start();
//        if(running ) {
//            btnStart.setAnimation(roudingAlone);
//        }

    }// end onClockStart()


    public void onClickStop(View view ){
        running = false ;
        anchor.animate().cancel();
        anchor.clearAnimation();
       // btnStop.getAnimation();
        btnStart.animate().alpha(1).translationY(-80).setDuration(2000).start();
        btnStop.animate().alpha(0).setDuration(2000).start();
    } // end onClickStop()


    public void onClickReset(View view ){
        running = false ;
        seconds = 0 ;
    } // end onClickReset()
    public void runTimer(){
        final TextView textView = findViewById(R.id.txtResult_ID);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600 ;
                int minutes = (seconds % 3600) / 60  ;
                int secs = seconds % 60  ;
                String time = String.format("%02d:%02d:%02d" , hours , minutes , secs);
                textView.setText(time);

                if (running) {
                    seconds++;
                } // end if
                handler.postDelayed(this , 1000);
            } // end run()
        }); // end handler


    } // end runTimer()
    public void animation(){
        anchor = findViewById(R.id.anchor_ID);
        roudingAlone = AnimationUtils.loadAnimation(this , R.anim.rouding);
        anchor.startAnimation(roudingAlone);

    } //animation()
} // end class
