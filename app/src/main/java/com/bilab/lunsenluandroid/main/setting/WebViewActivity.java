package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

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
        Log.d("5566", "init url: " + url);
        File cacheDir = getCacheDir();
        Log.d("5566", cacheDir.toString());


        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                url = url.split("blob:")[1];
                Log.d("5566", "url: " + url);
                Log.d("5566", "Uri.parse(url): " + Uri.parse(url));
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                // Set title and description for the download notification
                request.setTitle("File Download");
                request.setDescription("Downloading file...");

                Context mContext = getBaseContext();
                // Set destination directory
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filename.zip");

                // Enqueue the download
                DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                // Inform the user that the download has started
                Toast.makeText(mContext, "Download started", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
