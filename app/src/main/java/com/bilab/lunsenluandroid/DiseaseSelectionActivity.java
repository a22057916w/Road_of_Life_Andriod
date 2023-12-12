package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bilab.lunsenluandroid.Adapter.DiseaseSelectionRvAdapter;
import com.bilab.lunsenluandroid.model.DiseaseSelectionModel;
import com.bilab.lunsenluandroid.ui.disease.CheckBoxListener;
import com.bilab.lunsenluandroid.util.Constant;

import java.util.ArrayList;
import java.util.Objects;

public class DiseaseSelectionActivity extends AppCompatActivity implements CheckBoxListener {

    private RecyclerView rv_disease_selection;
    private DiseaseSelectionRvAdapter diseaseSelectionRvAdapter;
    private TextView tv_disease_related_disease;
    private Button btn_confirm;
    private String cancer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        Intent receiverIntent = getIntent();
        if (receiverIntent == null || receiverIntent.getComponent() == null) {
            Log.d("DiseaseSelectionActivity", "Do not receive Intent.");
            throw new NullPointerException();
        }
        cancer = receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);

        registerUI();
        setupUI();

    }

    private void registerUI() {
        rv_disease_selection = findViewById(R.id.rv_disease_selection);
        tv_disease_related_disease = findViewById(R.id.tv_cancer_related_disease);
        btn_confirm = findViewById(R.id.btn_confirm);
    }

    private void setupUI() {
        Objects.requireNonNull(getSupportActionBar()).hide();

        setupButton();
        setupRv();
    }

    private void setupButton() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();    // normally called after the default back key pressed on the device
            }
        });
    }
    @Override
    public void onAdapterButtonClick(int position) {
        ArrayList<DiseaseSelectionModel> list = diseaseSelectionRvAdapter.getDiseaseSelectionModelArrayList();
        String diseaseName = list.get(position).getDiseaseName();

        Person person = Person.getInstance();
        person.updateDisease(new Disease(cancer, diseaseName));
    }

    private void setupRv() {
        String [] diseases = DiseaseDataSingleton.getInstance().getCancerDiseaseList(cancer);

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<>();

        Person person = Person.getInstance();
        for (String disease : diseases) {
            boolean checked = person.findDisease(new Disease(cancer, disease)) != Constant.npos;
            diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(disease, R.drawable.ic_uterus, checked));
        }

        diseaseSelectionRvAdapter = new DiseaseSelectionRvAdapter(this, diseaseSelectionModelArrayList);
        diseaseSelectionRvAdapter.setCheckBoxListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease_selection.setLayoutManager(linearLayoutManager);
        rv_disease_selection.setAdapter((diseaseSelectionRvAdapter));
    }

}