package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DiseaseSelectionActivity extends AppCompatActivity implements CheckBoxListener {

    private RecyclerView rv_disease_selection;
    private DiseaseSelectionRvAdapter diseaseSelectionRvAdapter;
    private TextView tv_disease_related_disease;
    private Button btn_confirm;
    private Intent receiverIntent;
    private Map<String, Boolean> map;
    private String cancer;
//    private Map<String, Boolean> related_disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        receiverIntent = getIntent();
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
//                for(var entry : map.entrySet()) {
//                    Log.d("7777", cancer + ":  " + entry.getKey() + ": " + entry.getValue());
//                }
//                Log.d("9999", diseaseSelectionRvAdapter.getDiseaseSelectionModelArrayList().size() + ": " + DiseaseDataSingleton.getInstance().getCancerDiseases(cancer).size());
//                DiseaseDataSingleton.getInstance().update(cancer, related_disease);

                onBackPressed();    // normally called after the default back key pressed on the device
            }
        });
    }
    @Override
    public void onAdapterButtonClick(int position) {
        ArrayList<DiseaseSelectionModel> list = diseaseSelectionRvAdapter.getDiseaseSelectionModelArrayList();
        String disease = list.get(position).getDiseaseName();

        DiseaseDataSingleton.getInstance().updateUterus(disease);
//        boolean check = related_disease.get(disease);
//
//        related_disease.replace(disease, !check);

        Log.d("5566", "Button clicked at position: " + position + ", Disease: " + disease);
    }

    private void setupRv() {
//        String cancer = getCancer();
        Map<String, Boolean> related_disease = DiseaseDataSingleton.getInstance().getCancerDiseases(cancer);

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<DiseaseSelectionModel>();
        for(var entry : related_disease.entrySet()) {
            diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(entry.getKey(), R.drawable.ic_uterus, entry.getValue()));
            Log.d("1234", entry.getKey() + ": " + entry.getValue());
        }
        diseaseSelectionRvAdapter = new DiseaseSelectionRvAdapter(this, diseaseSelectionModelArrayList);
        diseaseSelectionRvAdapter.setCheckBoxListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease_selection.setLayoutManager(linearLayoutManager);
        rv_disease_selection.setAdapter((diseaseSelectionRvAdapter));
    }

//    private String getCancer() {
//        String disease_category = receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);
//        if(disease_category == null) {
//            Log.d("DiseaseSelectionActivity", "disease_category is NULL.");
//            throw new NullPointerException();
//        }
//        return disease_category;
//    }
}