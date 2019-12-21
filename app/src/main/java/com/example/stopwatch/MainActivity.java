package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timeText;
    CountDownTimer countDownTimer;
    int countTime = -1;

    public void timerControl (View view)    {
        final Button controlButton = (Button) view;

        String butStr = controlButton.getTag().toString();

        if (butStr.compareTo("run") == 0)  {
            seekBar.setVisibility(View.VISIBLE);
            controlButton.setText("START!");
            controlButton.setTag("ready");
            countDownTimer.cancel();
        }
        else if (butStr.compareTo("ready") == 0)    {
            seekBar.setVisibility(View.INVISIBLE);
            controlButton.setText("STOP!");
            controlButton.setTag("run");
            Log.i("time to use: ", String.valueOf(countTime));

            // start the timer
            countDownTimer = new CountDownTimer(countTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("millis: ", Long.toString(millisUntilFinished));
                    changeText(((int) millisUntilFinished/1000), timeText);
                }

                @Override
                public void onFinish() {
                    controlButton.setText("START!");
                    controlButton.setTag("ready");
                    seekBar.setVisibility(View.VISIBLE);
                    Log.i("FINISHED", "FDSAFDSAFSAD");
                }
            }.start();
        }
        else    {
            // error
        }
    }

    public void changeText (int num, TextView text) {
        int minutes = num/60;
        int seconds = num - minutes * 60;
        String time = Integer.toString(minutes) + ":" + Integer.toString(seconds);
        text.setText(time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(3600);
        seekBar.setProgress(1);

        timeText = (TextView) findViewById(R.id.timeText);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeText(progress, timeText);
                countTime = progress*1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
