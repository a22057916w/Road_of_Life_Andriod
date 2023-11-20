package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bilab.lunsenluandroid.Adapter.RecycleViewDiseaseSelection;
import com.bilab.lunsenluandroid.model.DiseaseSelectionModel;
import com.bilab.lunsenluandroid.util.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private RecyclerView rv_disease_selection;
    private TextView tv_disease_related_disease;
    private Intent receiverIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        receiverIntent = getIntent();
        if (receiverIntent == null || receiverIntent.getComponent() == null) {
            Log.d("DiseaseSelectionActivity", "Do not receive Intent.");
            throw new NullPointerException();
        }

        registerUI();
        setupUI();

    }

    private void registerUI() {
        rv_disease_selection = findViewById(R.id.rv_disease_selection);
        tv_disease_related_disease = findViewById(R.id.tv_cancer_related_disease);
    }

    private void setupUI() {
        Objects.requireNonNull(getSupportActionBar()).hide();

        setupRV();
    }

    private void setupRV() {
        ArrayList<String> related_disease = getRelatedDisease();
        related_disease.add(getString(R.string.none_of_above_disease));

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<DiseaseSelectionModel>();

        for(int i = 0; i < related_disease.size(); i++)
            diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(related_disease.get(i), R.drawable.ic_uterus));

        RecycleViewDiseaseSelection recycleViewDiseaseSelection = new RecycleViewDiseaseSelection(this, diseaseSelectionModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease_selection.setLayoutManager(linearLayoutManager);
        rv_disease_selection.setAdapter((recycleViewDiseaseSelection));
    }

    private ArrayList<String> getRelatedDisease() {
        String [] uterus_related_disease = {"子宮相關疾病", "月經失調或女性生殖道異常出血", "子宮平滑肌瘤或其他良性腫瘤", "子宮內膜異位症", "貧血症狀"};
        String [] ovary_related_disease = {"卵巢良性腫瘤", "卵巢或輸卵管非發炎性疾病", "子宮內膜異位症", "子宮平滑肌瘤或其他良性腫瘤", "骨盆腔發炎（子宮、卵巢、輸卵管"};
        String [] bladder_related_disease = {"泌尿道系統相關疾病", "腎結石或輸尿管結石", "膀胱發炎或相關疾病", "攝護腺（前列腺）肥大或相關疾病", "慢性腎衰竭", "腎絲球腎炎", "腎水腫"};
        String [] rectum_related_disease = {"腸和腹膜疾病、胃腸出血", "痔瘡", "胃或十二指腸等消化道潰瘍或功能性障礙", "消化系統良性腫瘤"};

        String disease_category = receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);
        if(disease_category == null) {
            Log.d("DiseaseSelectionActivity", "disease_category is NULL.");
            throw new NullPointerException();
        }

        if(disease_category.equals(Constant.UTERUS))
            return new ArrayList<>(List.of(uterus_related_disease));
        if(disease_category.equals(Constant.OVARY))
            return new ArrayList<>(List.of(ovary_related_disease));
        if(disease_category.equals(Constant.BLADDER))
            return new ArrayList<>(List.of(bladder_related_disease));
        if(disease_category.equals(Constant.RECTUM))
            return new ArrayList<>(List.of(rectum_related_disease));

        return new ArrayList<>();
    }
}