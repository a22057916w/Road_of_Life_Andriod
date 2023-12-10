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
    private DiseaseViewModel diseaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        receiverIntent = getIntent();
        if (receiverIntent == null || receiverIntent.getComponent() == null) {
            Log.d("DiseaseSelectionActivity", "Do not receive Intent.");
            throw new NullPointerException();
        }

        diseaseViewModel = new ViewModelProvider(this).get(DiseaseViewModel.class);

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
        Map<String, Boolean> map = new HashMap<>();

        for(int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getDiseaseName(), false);
        }
        map.replace(list.get(position).getDiseaseName(), true);
        Log.d("7777", list.get(position).getDiseaseName() + position + ": " + map.get(list.get(position).getDiseaseName()));
        diseaseViewModel.updateDiseaseMap(Constant.UTERUS, map);
        Log.d("5566", "Button clicked at position: " + position);
    }

    private void setupRv() {
        ArrayList<String> related_disease = getRelatedDisease();
//        related_disease.add(getString(R.string.none_of_above_disease));

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<DiseaseSelectionModel>();
        diseaseViewModel.getUterusDiseaseMap().observe(this, new Observer<Map<String, Boolean>>() {
            @Override
            public void onChanged(Map<String, Boolean> stringBooleanMap) {
                for(int i = 0; i < related_disease.size(); i++) {
                    diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(related_disease.get(i), R.drawable.ic_uterus, stringBooleanMap.get(related_disease.get(i))));
                }
            }
        });

//        LiveData<Map<String, Boolean>> map = diseaseViewModel.getUterusDiseaseMap();
//        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<DiseaseSelectionModel>();
//        for(int i = 0; i < related_disease.size(); i++) {
//            Log.d("1234", related_disease.get(i) + ": " + map.getValue().get(related_disease.get(i)));
//            diseaseSelectionModelArrayList.add(new DiseaseSelectionModel(related_disease.get(i), R.drawable.ic_uterus, map.getValue().get(related_disease.get(i))));
//        }

        diseaseSelectionRvAdapter = new DiseaseSelectionRvAdapter(this, diseaseSelectionModelArrayList);
        diseaseSelectionRvAdapter.setCheckBoxListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease_selection.setLayoutManager(linearLayoutManager);
        rv_disease_selection.setAdapter((diseaseSelectionRvAdapter));
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