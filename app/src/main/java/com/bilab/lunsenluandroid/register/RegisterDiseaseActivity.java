package com.bilab.lunsenluandroid.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.main.Disease;
import com.bilab.lunsenluandroid.main.DiseaseData;
import com.bilab.lunsenluandroid.main.MainActivity;
import com.bilab.lunsenluandroid.main.category.DiseaseSelectionAdapter;
import com.bilab.lunsenluandroid.main.category.DiseaseSelectionModel;
import com.bilab.lunsenluandroid.main.category.CheckBoxListener;
import com.bilab.lunsenluandroid.conf.Constant;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

public class RegisterDiseaseActivity extends AppCompatActivity implements CheckBoxListener {
    private RecyclerView rv_disease;

    private TextView tv_title;
    private Button btn_next, btn_skip;
    private ImageView imv_back_arrow;

    private String cancer;
    private int cancer_icon;

    private DiseaseSelectionAdapter rvAdapter;

    private final String [] category = {Constant.BLADDER, Constant.RECTUM, Constant.UTERUS, Constant.OVARY};
    private final Integer [] ctg_icon = {R.drawable.ic_bladder, R.drawable.ic_rectum, R.drawable.ic_uterus, R.drawable.ic_ovary};
    private int ctg_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        Intent receiverIntent = getIntent();
        if (receiverIntent == null || receiverIntent.getComponent() == null) {
            Log.d("RegisterDiseaseActivity", "Do not receive any Intent.");
            throw new NullPointerException();
        }


        ctg_index = receiverIntent.getIntExtra(Constant.EXTRA_INDEX, -1);
        cancer = category[ctg_index];
        cancer_icon = ctg_icon[ctg_index];

        registerUI();
        setupUI();

    }

    private void registerUI() {
        rv_disease = findViewById(R.id.rv_disease_selection);
        tv_title = findViewById(R.id.tv_cancer_related_disease);
        btn_next = findViewById(R.id.btn_confirm);
        btn_skip = findViewById(R.id.btn_skip);
        imv_back_arrow = findViewById(R.id.imv_arrow_white);
    }

    private void setupUI() {
        Objects.requireNonNull(getSupportActionBar()).hide();

        setupButton();
        setText();
        setupRv();
    }

    private void setText() {
        if(cancer.equals(Constant.UTERUS))
            tv_title.setText(R.string.uterus_diseases);
        if(cancer.equals(Constant.OVARY))
            tv_title.setText(R.string.ovary_diseases);
        if(cancer.equals(Constant.BLADDER))
            tv_title.setText(R.string.bladder_disease);
        if(cancer.equals(Constant.RECTUM))
            tv_title.setText(R.string.return_diseases);
    }

    private void setupButton() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // user must check one box to next page
                if(noItemSelected()) {
                    Snackbar snackbar = Snackbar.make(view, "請勾選一項", Snackbar.LENGTH_SHORT);
                    snackbar.show();

                    return;
                }
                if(ctg_index == category.length - 1) {
                    Intent openMainIntent = new Intent(RegisterDiseaseActivity.this, MainActivity.class);
                    startActivity(openMainIntent);
                    finish();
                }

                Intent restartIntent = new Intent(RegisterDiseaseActivity.this, RegisterDiseaseActivity.class);
                restartIntent.putExtra(Constant.EXTRA_INDEX, ++ctg_index);
                startActivity(restartIntent);
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainIntent = new Intent(RegisterDiseaseActivity.this, MainActivity.class);
                startActivity(openMainIntent);
                finish();
            }
        });

        imv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onAdapterButtonClick(int position) {
        ArrayList<DiseaseSelectionModel> list = rvAdapter.getDiseaseSelectionModelArrayList();
        String diseaseName = list.get(position).getDiseaseName();

        Person person = Person.getInstance();

        // if 無上述症狀 is selected
        if(diseaseName.equals("無上述症狀")) {
            resetAllChb();      // reset other chb
            person.clearDisease(cancer);    // clear all the disease related to the cancer

        }
        // if other disease is selected
        else {
            // if 無上述症狀 is already selected
            if(person.findDisease(new Disease(cancer, "無上述症狀")) != Constant.npos) {
                resetNoneChb();     // reset 無上述症狀 chb
                person.updateDisease(new Disease(cancer, "無上述症狀"));
            }
        }

        // update the selected disease(include 無上述症狀) to the person's record
        person.updateDisease(new Disease(cancer, diseaseName));
    }

    private void resetNoneChb() {
        CheckBox chb = rv_disease.findViewHolderForAdapterPosition(rvAdapter.getItemCount() - 1). itemView.findViewById(R.id.chb_disease_selection);
        chb.setChecked(false);
    }

    private void resetAllChb() {
        for(int i = 0; i < rvAdapter.getItemCount() - 1; i++) {
            CheckBox chb = rv_disease.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.chb_disease_selection);
            chb.setChecked(false);
        }
    }

    private boolean noItemSelected() {
        boolean isChecked = false;
        for(int i = 0; i < rvAdapter.getItemCount(); i++) {
            CheckBox chb = rv_disease.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.chb_disease_selection);
            isChecked = chb.isChecked();
            if(isChecked)
                break;
        }
        return !isChecked;
    }

    private void setupRv() {
        String [] diseases = DiseaseData.getInstance().getCancerDiseaseList(cancer);

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<>();

        Person person = Person.getInstance();
        for (String disease : diseases) {
            boolean checked = person.findDisease(new Disease(cancer, disease)) != Constant.npos;
            diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(disease, cancer_icon, checked));
        }

        rvAdapter = new DiseaseSelectionAdapter(this, diseaseSelectionModelArrayList);
        rvAdapter.setCheckBoxListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease.setLayoutManager(linearLayoutManager);
        rv_disease.setAdapter((rvAdapter));
    }
}