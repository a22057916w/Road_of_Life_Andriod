package com.bilab.lunsenluandroid.main.setting;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.util.JsonUtils;
import com.bilab.lunsenluandroid.util.ZipUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthPassActivity extends AppCompatActivity {
    private Button btn_read, btn_download;
    private TextView tv_demo;

    // ActivityResultContract 會定義產生結果所需的輸入類型，以及結果的輸出類型。而 API 會針對拍照、要求取得權限等基本意圖動作提供預設合約
    // ActivityResultContracts.GetContent(), let user select the file in the file system
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    try {
                        JSONObject jo = JsonUtils.readJsonFileFromUri(getApplicationContext(), uri);
                        tv_demo.setText(jo.toString());
                    } catch (IOException | JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_pass);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tv_demo = findViewById(R.id.tv_demo);
        btn_read = findViewById(R.id.btn_read);
        btn_download = findViewById(R.id.btn_download);

        btn_read.setOnClickListener(new View.OnClickListener() {

            // The input is the mime type to filter by, e.g. image/\*
            @Override
            public void onClick(View view) {
                mGetContent.launch("application/json");
            }
        });

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        Intent intent = new Intent(HealthPassActivity.this, WebViewActivity.class);
//        intent.putExtra("url", "https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"); //Add your url in "yourUrlHere"
    }



    @Override
    protected void onRestart() {
        super.onRestart();

    }


}



