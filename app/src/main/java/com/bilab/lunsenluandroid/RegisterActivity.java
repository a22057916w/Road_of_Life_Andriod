package com.bilab.lunsenluandroid;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bilab.lunsenluandroid.util.Constant;

public class RegisterActivity extends AppCompatActivity {
    Button btn_next, btn_year, btn_height, btn_weight;
    RadioButton rb_male, rb_female;
    ImageView previous;
    TextView register_error, tv_gender, tv_height, tv_weight, tv_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUI();
        setupUI();
    }

    private void registerUI() {
        btn_height = findViewById(R.id.btn_height);
        btn_weight = findViewById(R.id.btn_weight);
        btn_year = findViewById(R.id.btn_year);
        btn_next = findViewById(R.id.btn_next);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_gender = findViewById(R.id.tv_gender);
        tv_year = findViewById(R.id.tv_year);

        previous =   findViewById(R.id.register_previous);
        register_error =  findViewById(R.id.register_error);
    }
    private void setupUI() {
        Person person = Person.getInstance();

        getSupportActionBar().hide();
        btn_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YearPickerFragment yearPickerFragment = new YearPickerFragment();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                yearPickerFragment.show(supportFragmentManager, "datePicker");
            }
        });

        // Add listeners for the work for/break for buttons
        btn_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeightPickerFragment newFragment = new HeightPickerFragment();
                newFragment.show(getSupportFragmentManager(), "HeightPicker");
                // Bring up the picker fragment
//                DialogFragment newFragment = TimePickerFragment.newInstance(R.string.work_title);
//                newFragment.show(getSupportFragmentManager(), "WorkTimePicker");
            }
        });

        btn_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightPickerFragment newFragment = new WeightPickerFragment();
                newFragment.show(getSupportFragmentManager(), "WeightPicker");
                // Bring up the picker fragment
//                DialogFragment newFragment = TimePickerFragment.newInstance(R.string.work_title);
//                newFragment.show(getSupportFragmentManager(), "WorkTimePicker");
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openRegisterDiseaseIntent = new Intent(RegisterActivity.this, RegisterDiseaseActivity.class);
                openRegisterDiseaseIntent.putExtra(Constant.EXTRA_INDEX, 0);
                startActivity(openRegisterDiseaseIntent);
            }
        });

        rb_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.getInstance().setGender(Constant.MALE);
                rb_female.setChecked(false);
                Log.d("88888",Person.getInstance().getGender());
            }
        });

        rb_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.getInstance().setGender(Constant.FEMALE);
                rb_male.setChecked(false);
                Log.d("88888",Person.getInstance().getGender());
            }
        });
    }

}
