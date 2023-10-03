package com.bilab.lunsenluandroid.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private RecyclerView recycler_view;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        searchView = root.findViewById(R.id.searchView);
        // set default text
        searchView.setQuery("請輸入病症或器官", false);
        return root;
    }
}