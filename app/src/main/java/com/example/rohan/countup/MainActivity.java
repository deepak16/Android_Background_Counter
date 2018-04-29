package com.example.rohan.countup;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean mRunning;
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = (Chronometer) findViewById(R.id.chrono);
        mChronometer.start();
        mRunning = true;
    }

    private long convertTimeStringToSeconds(String s) {
        String[] segments = s.split(":");
        long multiplier = 1;
        long result = 0;
        for (int i = segments.length-1; i >= 0; i--) {
            long segmentVal = Integer.valueOf(segments[i]);
            segmentVal *= multiplier;
            multiplier *= 60;
            result += segmentVal;
        }
        return result;
    }

    public void toggle(View v) {
        if (mRunning) {
            mChronometer.stop();
        } else {
            long elapsedTime = SystemClock.elapsedRealtime();
            String timeString = mChronometer.getText().toString().substring(6);
            long secondsCounted = convertTimeStringToSeconds(timeString);
            android.widget.Toast.makeText(this, secondsCounted, Toast.LENGTH_SHORT).show();
            long base = elapsedTime - secondsCounted*1000;
            mChronometer.setBase(base);
            mChronometer.start();
        }
        mRunning = !mRunning;
    }
}
