package com.bilab.lunsenluandroid.main.setting;

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
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
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

import org.json.JSONObject;

public class HealthPassActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_pass);
        Objects.requireNonNull(getSupportActionBar()).hide();


//        Intent intent = new Intent(HealthPassActivity.this, WebViewActivity.class);
//        intent.putExtra("url", "https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"); //Add your url in "yourUrlHere"

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        String sourceZipPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MHBJSON_1130305_1.zip";
//        String destinationFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/folder";
//        String password = "F129915906";
//        extractZipFile(sourceZipPath, destinationFolderPath, password);

        File jsonFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MHBJSON_1130305_1-1", "健康存摺醫療類_1130305.JSON");
        try {
            JSONObject jsonObject = JsonUtils.readJsonFile(jsonFile);
            // Now you can use jsonObject for further processing
            Toast.makeText(this, "JSON file read successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to read JSON file", Toast.LENGTH_SHORT).show();
        }
//        verifyStoragePermissions(this);
    }

    private void extractZipFile(String sourceZipPath, String destinationFolderPath, String password) {
        try {
            ZipFile zipFile = new ZipFile(sourceZipPath);
            zipFile.setPassword(password.toCharArray());
            zipFile.extractAll(destinationFolderPath);
            Toast.makeText(this, "File unzipped successfully", Toast.LENGTH_SHORT).show();
        } catch (ZipException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to unzip file", Toast.LENGTH_SHORT).show();
        }
    }

    // Called when the activity needs to check permissions
    public void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission, so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {
            // We already have permission
            File zipFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MHBJSON_1130305_1.zip");
            File targetDirectory = new File(getFilesDir(), "unzipped");

            String password = "F129915902";

            try {
                ZipUtils.unzip(zipFile, targetDirectory);
                Toast.makeText(this, "File unzipped successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to unzip file", Toast.LENGTH_SHORT).show();
            }        }
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with the unzip operation
                File zipFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MHBJSON_1130305_1.zip");
                File targetDirectory = new File(getFilesDir(), "unzipped");
                String password = "F129915902";

                try {
                    ZipUtils.unzip(zipFile, targetDirectory);
                    Toast.makeText(this, "File unzipped successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to unzip file", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Permission denied, unable to access external storage.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

