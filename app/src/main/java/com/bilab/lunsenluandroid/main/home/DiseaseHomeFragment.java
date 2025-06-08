package com.bilab.lunsenluandroid.main.home;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.main.Disease;
import com.bilab.lunsenluandroid.main.DiseaseData;
import com.bilab.lunsenluandroid.register.RegisterDiseaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DiseaseHomeFragment extends Fragment {
    private TextView tv_notify;
    private ImageView imv_notify;
    private HomeViewModel pathViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pathViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setupOnBackPressedDispatcher();
        loadConfig();

        RecyclerView rv_disease = root.findViewById(R.id.rv_disease_home);

        Person person = Person.getInstance();
        person.updateRisk();

        String [] cancer = {Constant.UTERUS, Constant.OVARY, Constant.BLADDER, Constant.RECTUM, Constant.DKD, Constant.PREEMIE};
        String [] text = new String[6];
        for(int i = 0 ; i < cancer.length; i++) {
            Double pRisk = person.getRisk(cancer[i]);
            if (pRisk <= 50.0D)
                text[i] = "低風險";
            else if (pRisk <= 75.0D)
                text[i] = "中風險";
            else
                text[i] = "高風險";
        }
//        DiseaseData diseaseData = DiseaseData.getInstance();

        // Creating new array list and added data to it
        ArrayList<DiseaseHomeModel> diseaseHomeModelArrayList = new ArrayList<DiseaseHomeModel>();

        if(person.getGender().equals(Constant.MALE)) {
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("膀胱癌", "膀胱癌是泌尿系統常見的惡性疾病之一，較常侵犯60歲以上男性，男與女的比例大約是2.7：1。", text[2], R.drawable.ic_bladder, Constant.BLADDER));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("大腸癌", "自95年起大腸癌居全國癌症發生數第1位，每年約有一萬多人診斷大腸癌，並有超過五千人因大腸癌死亡，大腸癌對國人健康的影響甚鉅。", text[3], R.drawable.ic_rectum, Constant.RECTUM));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("糖尿病腎病變", "糖尿病腎病變，又稱糖尿病性腎病，是指因糖尿病導致的腎臟功能受損。 它是一種常見的糖尿病併發症，也是慢性腎臟病的主要原因之一。", text[4], R.drawable.ic_kidney_or_dkd, Constant.DKD));
        }
        else {
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("子宮內膜癌", "子宮內膜癌發生大部分是經由性行為感染人類乳突病毒所致，有性經驗的婦女感染人類乳突病毒很常見。", text[0], R.drawable.ic_uterus, Constant.UTERUS));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("卵巢癌", "卵巢癌雖不比子宮癌來得普遍，但卻是婦科癌症死亡原因的首位。", text[1], R.drawable.ic_ovary, Constant.OVARY));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("膀胱癌", "膀胱癌是泌尿系統常見的惡性疾病之一，較常侵犯60歲以上男性，男與女的比例大約是2.7：1。", text[2], R.drawable.ic_bladder, Constant.BLADDER));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("大腸癌", "自95年起大腸癌居全國癌症發生數第1位，每年約有一萬多人診斷大腸癌，並有超過五千人因大腸癌死亡，大腸癌對國人健康的影響甚鉅。", text[3], R.drawable.ic_rectum, Constant.RECTUM));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("糖尿病腎病變", "糖尿病腎病變，又稱糖尿病性腎病，是指因糖尿病導致的腎臟功能受損。 它是一種常見的糖尿病併發症，也是慢性腎臟病的主要原因之一。", text[4], R.drawable.ic_kidney_or_dkd, Constant.DKD));
            diseaseHomeModelArrayList.add(new DiseaseHomeModel("早產", "早產是指胎兒在妊娠滿28週至不足37週之間出生，可能需要特別醫療照護。", text[5], R.drawable.ic_preemie, Constant.PREEMIE));
        }

        // Initializing adapter class and passing arraylist to it.
        DiseaseHomeAdapter diseaseHomeAdapter = new DiseaseHomeAdapter(this.getActivity(), diseaseHomeModelArrayList);
        // Creating vertical list to provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        // Setting LayoutManager and adapter to recycler view.
        rv_disease.setLayoutManager(linearLayoutManager);
        rv_disease.setAdapter(diseaseHomeAdapter);


        // test ICD
        ArrayList<Disease> diseases = person.getAllDiseaseCopy();
        for(var disease : diseases) {
            Log.d("person", "Type: " + disease.getType());
            Log.d("person", "name: " + disease.getName());
            Log.d("person", "ICD10: " + disease.getICD10());
        }

        return root;
    }

    private void setupOnBackPressedDispatcher() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent openRegisterDiseaseIntent = new Intent(getContext(), RegisterDiseaseActivity.class);
                openRegisterDiseaseIntent.putExtra(Constant.EXTRA_INDEX, 3);
                startActivity(openRegisterDiseaseIntent);
                getActivity().finish();
            }
        };

        getActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void loadConfig() {

        // Load properties from assets
        Properties properties = new Properties();
        AssetManager assetManager = getActivity().getAssets();

        try {
            InputStream inputStream = assetManager.open("disease_chart_activity.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ================ Reads model weights and bias ==================
        try {
            // ===================== weights ========================
            String [] cancers = DiseaseData.getInstance().getCancers();

            // Create a map to store (ICD9s, weights) of each cancer
            Map<String, Map<String, Double>> wCancerDiseases = new ArrayMap<>();

            for(int i = 0; i < cancers.length; i++) {
                String propWeights = cancers[i].toLowerCase() + ".model.weights";   // e.g. bladder.model.weights
                String[] weights = properties.getProperty(propWeights, "").replaceAll("[()]", "").split(",\\s*");;

                // Create a map to tuple (ICD9s, weights)
                Map<String, Double> wDiseases = new HashMap<>();

                // Iterate over tuple array and populate the map
                for (int j = 0; j < weights.length; j += 2) {
                    String key = weights[j];
                    double value = Double.parseDouble(weights[j + 1]);
                    wDiseases.put(key, value);
                }
                // Print the map
//                for (Map.Entry<String, Double> entry : wDiseases.entrySet()) {
//                    Log.d("dddd", entry.getKey() + " -> " + entry.getValue());
//                }
                wCancerDiseases.put(cancers[i], wDiseases);
            }
            DiseaseData.getInstance().setWCancerDiseases(wCancerDiseases);

            // Print the map
            for (Map.Entry<String, Map<String, Double>> entry : wCancerDiseases.entrySet()) {
                Log.d("dddd", "Cancer: " + entry.getKey());
                Log.d("dddd", "Diseases:");
                Map<String, Double> innerMap = entry.getValue();
                for (Map.Entry<String, Double> innerEntry : innerMap.entrySet()) {
                    Log.d("dddd",innerEntry.getKey() + ": " + innerEntry.getValue());
                }
            }

            // ====================== bias ==============================
            Map<String, Double> bCancers = new HashMap<>();
            for(int i = 0; i < cancers.length; i++) {
                String propBias = cancers[i].toLowerCase() + ".model.bias";   // e.g. bladder.model.bias
                String bias = properties.getProperty(propBias, "");

                // convert to Double
                if (bias.isEmpty())
                    throw new IllegalArgumentException("Empty value found in bias.");
                bCancers.put(cancers[i], Double.parseDouble(bias));
            }
            DiseaseData.getInstance().setBCancers(bCancers);

        } catch (Exception e) {
            Log.d("dddd", e.toString());
        }
    }

}