package com.bilab.lunsenluandroid.ui.disease;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.DetailDiseaseActivity;
import com.bilab.lunsenluandroid.R;

public class DiseaseFragment extends Fragment {

    private DiseaseViewModel diseaseViewModel;

    private RecyclerView recycler_view;
    private SearchView searchView;

    private TextView uterus_tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diseaseViewModel =
                ViewModelProviders.of(this).get(DiseaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_disease, container, false);

        searchView = root.findViewById(R.id.searchView);
        // set default text
        searchView.setQuery("請輸入病症或器官", false);

        uterus_tv = root.findViewById(R.id.TVUterus);
        uterus_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailDiseaseActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}