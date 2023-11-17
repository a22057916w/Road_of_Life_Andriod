package com.bilab.lunsenluandroid;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bilab.lunsenluandroid.util.Constant;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loading);

        Intent openPrivacyPolicyIntent = new Intent(this, PrivacyPolicyActivity.class);
        openPrivacyPolicyIntent.putExtra(Constant.EXTRA_STARTER_ACTIVITY_NAME, this.getClass().getName());


        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                // This code will run after a delay of 2000 milliseconds (2 seconds)
                startActivity(openPrivacyPolicyIntent);
                finish();
            }
        };

        // Scheduling the TimerTask to run after a delay of 2000 milliseconds (2 seconds)
        timer.schedule(tast, 2000);


    }
}
