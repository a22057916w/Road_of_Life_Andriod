package com.bilab.lunsenluandroid;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bilab.lunsenluandroid.conf.Constant;

import java.io.File;
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


    @Override
    protected void onStop() {
        super.onStop();
        clearCache();
    }

    private void clearCache() {
        try {
            File dir = getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
