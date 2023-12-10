package com.bilab.lunsenluandroid;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bilab.lunsenluandroid.util.Constant;
import com.google.android.material.internal.ManufacturerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseViewModel extends ViewModel {
    private static DiseaseViewModel instance;
    private String [] _uterus_diseases = {"子宮相關疾病", "月經失調或女性生殖道異常出血", "子宮平滑肌瘤或其他良性腫瘤", "子宮內膜異位症", "貧血症狀"};
    private String [] _ovary_diseases = {"卵巢良性腫瘤", "卵巢或輸卵管非發炎性疾病", "子宮內膜異位症", "子宮平滑肌瘤或其他良性腫瘤", "骨盆腔發炎（子宮、卵巢、輸卵管"};
    private String [] _bladder_diseases = {"泌尿道系統相關疾病", "腎結石或輸尿管結石", "膀胱發炎或相關疾病", "攝護腺（前列腺）肥大或相關疾病", "慢性腎衰竭", "腎絲球腎炎", "腎水腫"};
    private String [] _rectum_diseases = {"腸和腹膜疾病、胃腸出血", "痔瘡", "胃或十二指腸等消化道潰瘍或功能性障礙", "消化系統良性腫瘤"};

    private MutableLiveData<Map<String, Boolean>> _uterus_disease_map = new MutableLiveData<>();
    private MutableLiveData<Map<String, Boolean>> _ovary_disease_map = new MutableLiveData<>();
    private MutableLiveData<Map<String, Boolean>> _bladder_disease_map = new MutableLiveData<>();
    private MutableLiveData<Map<String, Boolean>> _rectum_disease_map = new MutableLiveData<>();
//    private MutableLiveData<String> mText;

    public DiseaseViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is dashboard fragment");

        // set each _cancer_disease_map with (disease, false)
        initMap();
    }

    public static DiseaseViewModel getInstance() {
        if(instance == null)
            instance = new DiseaseViewModel();
        return instance;
    }

    public void updateDiseaseMap(String cancer, Map<String, Boolean> map) {
        if(cancer.equals(Constant.UTERUS))
            _uterus_disease_map.setValue(map);
        Map<String, Boolean> smap = _uterus_disease_map.getValue();
        for(Map.Entry entry : smap.entrySet()) {
            Log.d("3333", entry.getKey() + ": " + entry.getValue());
        }
    }

    // ============================= Getter ==================================
    public LiveData<Map<String, Boolean>> getUterusDiseaseMap() {
        return _uterus_disease_map;
    }

    public MutableLiveData<Map<String, Boolean>> getOvaryDiseaseMap() {
        return _ovary_disease_map;
    }

    public MutableLiveData<Map<String, Boolean>> getRectumDiseaseMap() {
        return _rectum_disease_map;
    }

    public MutableLiveData<Map<String, Boolean>> getBladderDiseaseMap() {
        return _bladder_disease_map;
    }

    private void initMap() {
        ArrayList<String []> cancers = new ArrayList<>(Arrays.asList(_uterus_diseases, _ovary_diseases, _bladder_diseases, _rectum_diseases));
        ArrayList<Map<String, Boolean>> maps = new ArrayList<>();

        // store each cancer disease to distinct map from map[0] to map[3]
        int i = 0;
        for(String [] cancer_diseases : cancers) {
            maps.add(new HashMap<>());
            for (String disease : cancer_diseases)
                maps.get(i).put(disease, false);
            i++;
        }

        // set the (MutableLiveData) caner disease map
        _uterus_disease_map.setValue(maps.get(0));
        _ovary_disease_map.setValue(maps.get(1));
        _bladder_disease_map.setValue(maps.get(2));
        _rectum_disease_map.setValue(maps.get(3));

        maps.clear();
    }
}