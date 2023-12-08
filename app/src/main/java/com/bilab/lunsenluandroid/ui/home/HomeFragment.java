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
import com.bilab.lunsenluandroid.model.DiseaseHomeModel;

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
        ArrayList<DiseaseHomeModel> diseaseHomeModelArrayList = new ArrayList<DiseaseHomeModel>();
        diseaseHomeModelArrayList.add(new DiseaseHomeModel("子宮癌", "子宮頸癌發生大部分是經由性行為感染人類乳突病毒所致，有性經驗的婦女感染人類乳突病毒很常見。","80%", R.drawable.ic_uterus));
        diseaseHomeModelArrayList.add(new DiseaseHomeModel("卵巢癌", "卵巢癌雖不比子宮癌來得普遍，但卻是婦科癌症死亡原因的首位。", "20%", R.drawable.ic_ovary));
        diseaseHomeModelArrayList.add(new DiseaseHomeModel("膀胱癌", "膀胱癌是泌尿系統常見的惡性疾病之一，較常侵犯60歲以上男性，男與女的比例大約是2.7：1。", "8%", R.drawable.ic_bladder));
        diseaseHomeModelArrayList.add(new DiseaseHomeModel("大腸癌", "自95年起大腸癌居全國癌症發生數第1位，每年約有一萬多人診斷大腸癌，並有超過五千人因大腸癌死亡，大腸癌對國人健康的影響甚鉅。", "5%", R.drawable.ic_rectum));

        // Initializing adapter class and passing arraylist to it.
        RecycleViewHome recycleViewHome = new RecycleViewHome(this.getActivity(), diseaseHomeModelArrayList);
        // Creating vertical list to provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        // Setting LayoutManager and adapter to recycler view.
        rv_disease.setLayoutManager(linearLayoutManager);
        rv_disease.setAdapter(recycleViewHome);

        return root;
    }

}