package com.bilab.lunsenluandroid.ui.disease;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.Adapter.RecycleViewDiseaseCategory;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.DiseaseCategoryModel;

import java.util.ArrayList;

public class DiseaseFragment extends Fragment {

    private DiseaseViewModel diseaseViewModel;

    private RecyclerView rv_disease;
    private SearchView searchView;

    private TextView uterus_tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diseaseViewModel =
                ViewModelProviders.of(this).get(DiseaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_disease, container, false);
//
//        searchView = root.findViewById(R.id.searchView);
//        // set default text
//        searchView.setQuery("請輸入病症或器官", false);
//

        registerUI(root);
        setupUI();

        return root;
    }

    private void registerUI(View root) {
        rv_disease = root.findViewById(R.id.rv_disease_category);
    }

    private void setupUI() {
        setupRV();
    }

    private void setupRV() {
        ArrayList<DiseaseCategoryModel> diseaseCategoryModelArrayList = new ArrayList<>();
        diseaseCategoryModelArrayList.add(new DiseaseCategoryModel("子宮相關疾病", "5個疾病資料", R.drawable.ic_uterus));
        diseaseCategoryModelArrayList.add(new DiseaseCategoryModel("卵巢相關疾病", "5個疾病資料", R.drawable.ic_ovary));
        diseaseCategoryModelArrayList.add(new DiseaseCategoryModel("膀胱相關疾病", "7個疾病資料", R.drawable.ic_bladder));
        diseaseCategoryModelArrayList.add(new DiseaseCategoryModel("大腸相關疾病", "4個疾病資料", R.drawable.ic_rectum));

        // Initializing adapter class and passing arraylist to it.
        RecycleViewDiseaseCategory recycleViewDiseaseCategory = new RecycleViewDiseaseCategory(this.getActivity(), diseaseCategoryModelArrayList);
        // Creating vertical list to provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        // Setting LayoutManager and adapter to recycler view.
        rv_disease.setLayoutManager(linearLayoutManager);
        rv_disease.setAdapter(recycleViewDiseaseCategory);
    }

}