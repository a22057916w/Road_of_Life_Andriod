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
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("胃腸出血", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("痔瘡", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("功能性消化不良症", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("十二指腸潰瘍", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("消化性潰瘍", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("功能性胃障礙", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腹部及骨盆疾患", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腸道和腹膜疾病", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("靜脈和淋巴系疾病及其他循環系統疾病", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他消化系統疾患", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("食道，胃和十二指腸疾病", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他腸道疾病", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他消化系統良性腫瘤", R.drawable.rectum));
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.rectum));

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




//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他子宮疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("月經失調及生殖道異常出血", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮肌瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他子宮良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮內膜異位症", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("停經及停經後之疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("貧血", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿生殖器官惡性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("女性生殖器的其他疾病", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("血液和造血器官疾病", R.drawable.pregnant));






        RecycleViewDetailDisease recycleViewDetailDisease = new RecycleViewDetailDisease(this, detailDiseaseModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        detailDiseaseRV.setLayoutManager(linearLayoutManager);
        detailDiseaseRV.setAdapter((recycleViewDetailDisease));

    }

}