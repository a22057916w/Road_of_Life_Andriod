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
        detailDiseaseModelArrayList.add(new DetailDiseaseModel("胃腸出血", R.drawable.bladder));

//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("慢性腎衰竭", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎水腫", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("慢性腎絲球腎炎", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎及輸尿管結石", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("腎炎，腎病綜合徵和腎病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("膀胱炎", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他膀胱疾患", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿道感染", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿生殖器官惡性腫瘤", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("侵及泌尿系統之徵候", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他泌尿系統疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("攝護腺（前列腺）增生", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("攝護腺（前列腺）炎性疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("停經及停經後之疾患", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他女生殖器官疾病", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("痔瘡", R.drawable.bladder));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("骨質疏鬆症", R.drawable.bladder));


//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("卵巢良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("卵巢、輸卵管及闊韌帶之非炎性疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮內膜異位症", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.pregnant));


//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他子宮疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮異常出血", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮內膜異位症", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("子宮肌瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他子宮良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("其他良性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("泌尿生殖器官惡性腫瘤", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("女性生殖器的其他疾病", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("停經及停經後之疾患", R.drawable.pregnant));
//        detailDiseaseModelArrayList.add(new DetailDiseaseModel("貧血", R.drawable.pregnant));






        RecycleViewDetailDisease recycleViewDetailDisease = new RecycleViewDetailDisease(this, detailDiseaseModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        detailDiseaseRV.setLayoutManager(linearLayoutManager);
        detailDiseaseRV.setAdapter((recycleViewDetailDisease));

    }

}