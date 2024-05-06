package com.bilab.lunsenluandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.main.MainActivity;
import com.bilab.lunsenluandroid.main.setting.SettingFragment;
import com.bilab.lunsenluandroid.register.RegisterActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    ImageView imv_previous;
    Button btn_agree, btn_disagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_privacy_policy);
        getSupportActionBar().hide();

        registerUI();
        setListeners();

        Intent intent = getIntent();
        // Check if the Intent is not null and has a starting activity
        if (intent != null && intent.getComponent() != null) {
            String starterActivityName = intent.getStringExtra(Constant.EXTRA_STARTER_ACTIVITY_NAME);

            if(starterActivityName.equals(LoadingActivity.class.getName())) {
                imv_previous.setVisibility(View.INVISIBLE);
            }

            if(starterActivityName.equals(MainActivity.class.getName())) {
                btn_agree.setVisibility(View.GONE);
                btn_disagree.setVisibility(View.GONE);
            }
        }

    }

    private void registerUI() {
        imv_previous =  (ImageView) findViewById(R.id.privacy_policy_previous);
        btn_disagree =  (Button) findViewById(R.id.privacy_policy_disagree);
        btn_agree =  (Button) findViewById(R.id.privacy_policy_agree);
    }

    private void setListeners() {
        imv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                final Bundle bundle = new Bundle();
                intent.setClass(PrivacyPolicyActivity.this, RegisterActivity.class);
                startActivity(intent);
                PrivacyPolicyActivity.this.finish();
            }
        });

        btn_disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}