package com.bilab.lunsenluandroid.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.Adapter.CourseAdapter;
import com.bilab.lunsenluandroid.MyAdapter;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.CourseModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView courseRV = root.findViewById(R.id.idRVCourse);

        // Here, we have created new array list and added data to it
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel("DSA in Java", 4, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("Java Course", 3, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("C++ Course", 4, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("DSA in C++", 4, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("Kotlin for Android", 4, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("Java for Android", 4, R.drawable.baseline_star_24));
        courseModelArrayList.add(new CourseModel("HTML and CSS", 4, R.drawable.baseline_star_24));

        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(this.getActivity(), courseModelArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

//        // 準備資料，塞50個項目到ArrayList裡
//        for(int i = 0; i < 50; i++) {
//            mData.add("項目"+i);
//        }
//
//        // 連結元件
//        recycler_view = (RecyclerView) root.findViewById(R.id.recycler_view);
//        // 設置RecyclerView為列表型態
//        recycler_view.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        // 設置格線
//        recycler_view.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
//
//        // 將資料交給adapter
//        adapter = new MyAdapter(mData);
//        // 設置adapter給recycler_view
//        recycler_view.setAdapter(adapter);

        return root;
    }
}