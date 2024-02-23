package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bilab.lunsenluandroid.R;

import java.util.Objects;

public class GeneRegisterActivity extends AppCompatActivity {
    Button btn_next, btn_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gene_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_next = findViewById(R.id.btn_next);
        btn_skip = findViewById(R.id.btn_skip);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneRegisterActivity.this, DNAmChartActivity.class);
                startActivity(intent);
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneRegisterActivity.this, DNAmChartActivity.class);
                startActivity(intent);
            }
        });
    }
}