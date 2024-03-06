package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.bilab.lunsenluandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class HealthPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_pass);
        Objects.requireNonNull(getSupportActionBar()).hide();


        Intent intent = new Intent(HealthPassActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"); //Add your url in "yourUrlHere"

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

