package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bilab.lunsenluandroid.R;

import java.io.File;
import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Objects.requireNonNull(getSupportActionBar()).hide();


        WebView webView = findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");

        if (url == null)
            throw new NullPointerException();

            webView.getSettings().setJavaScriptEnabled(true);   // enable JavaScript to load 健康存摺網頁
            webView.loadUrl(url);

        File cacheDir = getCacheDir();
        Log.d("5566", cacheDir.toString());



//        webView.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                // Specify the directory where you want to save the downloaded file
//                String downloadDirectory = Environment.getExternalStorageDirectory().toString();
//                Log.d("5566", downloadDirectory);
//                // Create a file to save the downloaded file
//                File file = new File(downloadDirectory, "HealthPass.json");
//
//                // Start downloading the file
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                request.setDescription("Downloading file");
//                request.setTitle("File Name");
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationUri(Uri.fromFile(file));
//
//                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//                downloadManager.enqueue(request);
//            }
//        });
    }


}
