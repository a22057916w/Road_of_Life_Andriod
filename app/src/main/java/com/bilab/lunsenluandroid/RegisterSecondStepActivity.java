package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterSecondStepActivity extends AppCompatActivity {
    EditText name,phone,email,password_check;
    Button logout_button ,birthday_ev;
    ImageView previous;
    TextView register_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_second_step);
        name =  (EditText) findViewById(R.id.logout_name);
        phone =  (EditText) findViewById(R.id.logout_phone);
        email =  (EditText) findViewById(R.id.logout_email);
        birthday_ev =  (Button) findViewById(R.id.logout_birthday);
        password_check =  (EditText) findViewById(R.id.logout_password_check);
        logout_button =  (Button) findViewById(R.id.logout_button);
        previous =  (ImageView) findViewById(R.id.register_previous);
        register_error = (TextView) findViewById(R.id.register_error);


        birthday_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                newFragment.show(supportFragmentManager, "datePicker");
            }
        });

//        birthday_ev.setOnClickListener {
//            DatePickerFragment newFragment = new DatePickerFragment();
//            newFragment.show(supportFragmentManager, "datePicker");
//        }

    }


}