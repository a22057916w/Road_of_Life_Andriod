package com.bilab.lunsenluandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.bilab.lunsenluandroid.Adapter.RecycleViewDiseaseSelection;
import com.bilab.lunsenluandroid.model.DiseaseSelectionModel;

import java.util.ArrayList;

public class DiseaseSelectionActivity extends AppCompatActivity {

    RecyclerView rv_disease_selection;
    TextView tv_disease_related_disease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerUI();
        setupUI();

        ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList = new ArrayList<DiseaseSelectionModel>();
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("胃腸出血", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("痔瘡", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("功能性消化不良症", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("十二指腸潰瘍", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("消化性潰瘍", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("功能性胃障礙", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腹部及骨盆疾患", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腸道和腹膜疾病", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("靜脈和淋巴系疾病及其他循環系統疾病", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他消化系統疾患", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("食道，胃和十二指腸疾病", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腸道疾病", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他消化系統良性腫瘤", R.drawable.rectum));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.rectum));

//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿道感染", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎及輸尿管結石", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("膀胱炎", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("攝護腺（前列腺）增生", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("慢性腎衰竭", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他排尿系統疾患", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("停經及停經後之疾患", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿生殖器官惡性腫瘤", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他泌尿系統疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他男性生殖器官疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎炎，腎病綜合徵和腎病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿生殖器官惡性腫瘤", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎水腫", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("慢性腎絲球腎炎", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他膀胱疾患", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("痔瘡", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("骨質疏鬆", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他女性生殖器官疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("攝護腺（前列腺）炎性疾病", R.drawable.bladder));





//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("卵巢良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("卵巢、輸卵管及闊韌帶之非炎性疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮內膜異位症", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮肌瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腹部及骨盆疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("卵巢、輸卵管、骨盆蜂窩組織及腹膜之炎症", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.pregnant));




        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("其他子宮疾患", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("月經失調及生殖道異常出血", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("子宮肌瘤", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("其他子宮良性腫瘤", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("子宮內膜異位症", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("停經及停經後之疾患", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("貧血", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("泌尿生殖器官惡性腫瘤", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("女性生殖器的其他疾病", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("其他良性腫瘤", R.drawable.pregnant));
        diseaseSelectionModelArrayList.add(new DiseaseSelectionModel("血液和造血器官疾病", R.drawable.pregnant));






        RecycleViewDiseaseSelection recycleViewDiseaseSelection = new RecycleViewDiseaseSelection(this, diseaseSelectionModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_disease_selection.setLayoutManager(linearLayoutManager);
        rv_disease_selection.setAdapter((recycleViewDiseaseSelection));

    }



    private void registerUI() {
        setContentView(R.layout.activity_disease_selection);

        rv_disease_selection = findViewById(R.id.rv_disease_selection);
        tv_disease_related_disease = findViewById(R.id.tv_cancer_related_disease);
    }

    private void setupUI() {
        getSupportActionBar().hide();

        setupRV();
    }

    private void setupRV() {
    }
}