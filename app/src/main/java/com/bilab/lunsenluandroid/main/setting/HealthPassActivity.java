package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bilab.lunsenluandroid.R;

import java.util.Objects;

public class HealthPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_pass);
        Objects.requireNonNull(getSupportActionBar()).hide();


        Intent intent = new Intent(HealthPassActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"); //Add your url in "yourUrlHere"
        startActivity(intent);
    }
}