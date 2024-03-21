package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;

import java.util.Objects;

public class GeneRegisterActivity extends AppCompatActivity {
    Button btn_next, btn_skip;
    ImageView imv_previous;
    EditText edt_OTX1, edt_ZNF154, edt_ZIC4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gene_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        registerUI();
        
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneRegisterActivity.this, DNAmChartActivity.class);
                // put edt values
                intent.putExtra(Constant.OTX1, Double.parseDouble(edt_OTX1.getText().toString()));
                intent.putExtra(Constant.ZNF154, Double.parseDouble(edt_ZNF154.getText().toString()));
                intent.putExtra(Constant.ZIC4, Double.parseDouble(edt_ZIC4.getText().toString()));

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

        imv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void registerUI() {
        btn_next = findViewById(R.id.btn_next);
        btn_skip = findViewById(R.id.btn_skip);

        edt_OTX1 = findViewById(R.id.edt_OTX1);
        edt_ZNF154 = findViewById(R.id.edt_ZNF154);
        edt_ZIC4 = findViewById(R.id.edt_ZIC4);

        imv_previous = findViewById(R.id.imv_previous);
    }
}