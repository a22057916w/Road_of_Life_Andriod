package com.bilab.lunsenluandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    ImageView imv_previous;
    Button btn_agree, btn_disagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        // Check if the Intent is not null and has a starting activity
        if (intent != null && intent.getComponent() != null) {
            String startingActivityClassName = intent.getComponent().getClassName();
            if(startingActivityClassName.equals(LoadingActivity.class.getName()))
                imv_previous.setVisibility(View.INVISIBLE);

        }

        imv_previous =  (ImageView) findViewById(R.id.privacy_policy_previous);
        btn_disagree =  (Button) findViewById(R.id.privacy_policy_disagree);
        btn_agree =  (Button) findViewById(R.id.privacy_policy_agree);

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