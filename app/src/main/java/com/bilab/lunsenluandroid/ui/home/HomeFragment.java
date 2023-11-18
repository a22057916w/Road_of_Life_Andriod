package com.bilab.lunsenluandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.Adapter.RecycleViewHome;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.HomeModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel pathViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pathViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rv_disease = root.findViewById(R.id.rv_disease_home);

        // Creating new array list and added data to it
        ArrayList<HomeModel> homeModelArrayList = new ArrayList<HomeModel>();
        homeModelArrayList.add(new HomeModel("肺癌", "近數十年來臺灣地區肺癌病人有顯著增加的趨勢，而目前肺癌已是國人因為癌症死亡最常見的原因之一","80%", R.drawable.lungs));
        homeModelArrayList.add(new HomeModel("肝癌", "每年約13,000人死於慢性肝病、肝硬化及肝癌，慢性肝病及肝硬化為全國主要死因的第9位，肝癌則為全國主要癌症死因的第2位", "20%", R.drawable.liver));
        homeModelArrayList.add(new HomeModel("前列腺癌", "前列腺癌是好發於老年男性的惡性腫瘤，而其死亡率在65歲以後也有陡然上升的趨勢", "8%", R.drawable.prostate));
        homeModelArrayList.add(new HomeModel("直腸癌", "目前大腸直腸癌為台灣所有癌症死亡率第三名，更為癌症發生率第一位", "5%", R.drawable.rectum));
        homeModelArrayList.add(new HomeModel("乳癌", "目前乳癌發生率為國內女性好發癌症的第一位，死亡率則為第四位", "60%", R.drawable.breast));

        // Initializing adapter class and passing arraylist to it.
        RecycleViewHome recycleViewHome = new RecycleViewHome(this.getActivity(), homeModelArrayList);
        // Creating vertical list to provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        // Setting LayoutManager and adapter to recycler view.
        rv_disease.setLayoutManager(linearLayoutManager);
        rv_disease.setAdapter(recycleViewHome);

        return root;
    }

}