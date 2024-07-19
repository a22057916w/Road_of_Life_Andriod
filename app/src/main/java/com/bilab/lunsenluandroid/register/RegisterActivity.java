package com.bilab.lunsenluandroid.register;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    Button btn_next, btn_year, btn_height, btn_weight;
    RadioButton rb_male, rb_female;
    ImageView previous;
    TextView tv_gender, tv_height, tv_weight, tv_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUI();
        setupUI();

        setupOnBackPressedDispatcher();

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

        previous = findViewById(R.id.imv_register_previous);
    }
    private void setupUI() {
        Person person = Person.getInstance();

        if(!person.getHeight().equals(""))
            btn_height.setHint(String.format("%scm", person.getHeight()));
        if(!person.getWeight().equals(""))
            btn_weight.setHint(String.format("%skg", person.getWeight()));
        if(!person.getYear().equals(""))
            btn_year.setHint(String.format(person.getYear()));

        if(person.getGender().equals(Constant.MALE))
            rb_male.setChecked(true);
        else if(person.getGender().equals(Constant.FEMALE))
            rb_female.setChecked(true);


        // hide unnecessary UI
        getSupportActionBar().hide();
        previous.setVisibility(View.INVISIBLE);

        btn_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YearPickerFragment yearPickerFragment = new YearPickerFragment();
                yearPickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // Add listeners for the work for/break for buttons
        btn_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeightPickerFragment heightPickerFragment = new HeightPickerFragment();
                heightPickerFragment.show(getSupportFragmentManager(), "HeightPicker");
            }
        });

        btn_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightPickerFragment weightPickerFragment = new WeightPickerFragment();
                weightPickerFragment.show(getSupportFragmentManager(), "WeightPicker");
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllSet()) {
                    Intent openRegisterDiseaseIntent = new Intent(RegisterActivity.this, RegisterDiseaseActivity.class);
                    if(Person.getInstance().getGender().equals(Constant.MALE))
                        openRegisterDiseaseIntent.putExtra(Constant.EXTRA_INDEX, 2);
                    else
                        openRegisterDiseaseIntent.putExtra(Constant.EXTRA_INDEX, 0);
                    startActivity(openRegisterDiseaseIntent);
                }
                else {
                    Snackbar snackbar = Snackbar.make(view, "請填寫完整資料", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

        rb_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.getInstance().setGender(Constant.MALE);
                rb_female.setChecked(false);
            }
        });

        rb_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.getInstance().setGender(Constant.FEMALE);
                rb_male.setChecked(false);
            }
        });
    }

    private void setupOnBackPressedDispatcher() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
                System.exit(0);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private boolean isAllSet() {
        Person person = Person.getInstance();
        String height = person.getHeight();
        String weight = person.getWeight();
        String year = person.getYear();
        String gender = person.getGender();

        return !(height.isEmpty() | weight.isEmpty() | year.isEmpty() | gender.isEmpty());
    }

}
