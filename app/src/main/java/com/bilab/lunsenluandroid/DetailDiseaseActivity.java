package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bilab.lunsenluandroid.Adapter.RecycleViewDetailDisease;
import com.bilab.lunsenluandroid.model.DetailDiseaseModel;

import java.util.ArrayList;

public class DetailDiseaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diease);
        getSupportActionBar().hide();

        RecyclerView detailDiseaseRV = findViewById(R.id.RVDetailDisease);

        ArrayList<DetailDiseaseModel> detailDiseaseModelArrayList = new ArrayList<DetailDiseaseModel>();
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("月經異常出血", R.drawable.pregnant));

        RecycleViewDetailDisease recycleViewDetailDisease = new RecycleViewDetailDisease(this, detailDiseaseModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        detailDiseaseRV.setLayoutManager(linearLayoutManager);
        detailDiseaseRV.setAdapter((recycleViewDetailDisease));

    }

}