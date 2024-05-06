package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.conf.Person;

public class PersonDataActivity extends AppCompatActivity {

    Button btn_confirm, btn_year, btn_height, btn_weight;
    RadioButton rb_male, rb_female;
    ImageView previous;
    TextView tv_gender, tv_height, tv_weight, tv_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);

        registerUI();
        setupUI();

    }

    private void registerUI() {
        btn_height = findViewById(R.id.btn_height);
        btn_weight = findViewById(R.id.btn_weight);
        btn_year = findViewById(R.id.btn_year);
        btn_confirm = findViewById(R.id.btn_confirm);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_gender = findViewById(R.id.tv_gender);
        tv_year = findViewById(R.id.tv_year);

        previous = findViewById(R.id.imv_register_previous);
    }
    private void setupUI() {
        getSupportActionBar().hide();

        // hide the previous arrow
        previous.setVisibility(View.INVISIBLE);

        // disable button function
        rb_female.setClickable(false);
        rb_male.setClickable(false);

        Person person = Person.getInstance();
        btn_height.setText(person.getHeight());
        btn_weight.setText(person.getWeight());
        btn_year.setText(person.getYear());
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(person.getGender().equals(Constant.MALE)) {
            rb_male.setChecked(true);
            rb_female.setChecked(false);
        }
        else {
            rb_male.setChecked(false);
            rb_female.setChecked(true);
        }
    }
}