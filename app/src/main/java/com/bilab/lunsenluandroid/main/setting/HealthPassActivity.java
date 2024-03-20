package com.bilab.lunsenluandroid.main.setting;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.main.Disease;
import com.bilab.lunsenluandroid.main.DiseaseData;
import com.bilab.lunsenluandroid.util.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthPassActivity extends AppCompatActivity {
    private Button btn_read, btn_download;
    private ImageView imv_pervious;
    private JSONObject jo_healthPass;

    // ActivityResultContract 會定義產生結果所需的輸入類型，以及結果的輸出類型。而 API 會針對拍照、要求取得權限等基本意圖動作提供預設合約
    // ActivityResultContracts.GetContent(), let user select the file in the file system
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    try {
                        jo_healthPass = JsonUtils.readJsonFileFromUri(getApplicationContext(), uri);
                        checkDisease();
                        Toast.makeText(getApplicationContext(), "上傳成功", Toast.LENGTH_SHORT).show();
                    } catch (IOException | JSONException | NullPointerException e) {
                        Toast.makeText(getApplicationContext(), "尚未選擇檔案", Toast.LENGTH_SHORT).show();
//                        throw new RuntimeException(e);
                    }
                }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_pass);
        Objects.requireNonNull(getSupportActionBar()).hide();

        registerUI();
        setupUI();

//        Intent intent = new Intent(HealthPassActivity.this, WebViewActivity.class);
//        intent.putExtra("url", "https://myhealthbank.nhi.gov.tw/IHKE3000/IHKE3099S01"); //Add your url in "yourUrlHere"
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    private void checkDisease() {       // 比對健康存摺，設定使用者共病
        Person person = Person.getInstance();
        DiseaseData diseasesData = DiseaseData.getInstance();

        String[] cancers = {Constant.UTERUS, Constant.OVARY, Constant.BLADDER, Constant.RECTUM};
        String healthPass = jo_healthPass.toString();

        for (String cancer : cancers) {     // scan through each cancer
            String[][] ICDs = DiseaseData.getInstance().getCancerICD10(cancer);     // get ICDs for the disease

            for (int j = 0; j < ICDs.length; j++) {     // scan through each disease
                for(int k = 0; k < ICDs[j].length; k++) {   // scan the ICDs of each disease
                    if(healthPass.contains(ICDs[j][k])) {   // compare the App's ICD to 健康存摺's
                        String name = diseasesData.getCancerDiseaseList(cancer).get(j);
                        person.addDisease(new Disease(cancer, name));
                    }
                }
            }
        }
    }

    private void registerUI() {
        btn_read = findViewById(R.id.btn_read);
        btn_download = findViewById(R.id.btn_download);

        imv_pervious = findViewById(R.id.imv_previous);
    }

    private void setupUI() {
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

        imv_pervious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}



