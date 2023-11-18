package com.bilab.lunsenluandroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RegisterActivity extends AppCompatActivity {

    EditText email,password_check;
    Button btn_next, birthday_btn, name_btn, weight_btn;
    ImageView previous;
    TextView register_error, birthday_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        name_btn =  (Button) findViewById(R.id.logout_name);


        weight_btn =  (Button) findViewById(R.id.logout_phone);

        email =  (EditText) findViewById(R.id.logout_email);

        birthday_btn =  (Button) findViewById(R.id.logout_birthday);
        birthday_tv = (TextView) findViewById(R.id.birthday_textview);

//        password_check =  (EditText) findViewById(R.id.logout_password_check);
        btn_next =  (Button) findViewById(R.id.btn_next);
        previous =  (ImageView) findViewById(R.id.register_previous);
        register_error = (TextView) findViewById(R.id.register_error);


        birthday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                datePickerFragment.show(supportFragmentManager, "datePicker");
            }
        });

        // Add listeners for the work for/break for buttons
        name_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeightPickerFragment newFragment = new HeightPickerFragment();
                newFragment.show(getSupportFragmentManager(), "HeightPicker");
                // Bring up the picker fragment
//                DialogFragment newFragment = TimePickerFragment.newInstance(R.string.work_title);
//                newFragment.show(getSupportFragmentManager(), "WorkTimePicker");
            }
        });

        weight_btn.setOnClickListener(new View.OnClickListener() {
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
                Intent openMainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(openMainIntent);
                finish();
            }
        });
    }
}
