package com.bilab.lunsenluandroid;

import android.util.Log;

import com.bilab.lunsenluandroid.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DiseaseDataSingleton {
    private static DiseaseDataSingleton _instance;
    private final String [] _uterus_diseases = {"子宮相關疾病", "月經失調或女性生殖道異常出血", "子宮平滑肌瘤或其他良性腫瘤", "子宮內膜異位症", "貧血症狀", "無上述症狀"};
    private final String [] _ovary_diseases = {"卵巢良性腫瘤", "卵巢或輸卵管非發炎性疾病", "子宮內膜異位症", "子宮平滑肌瘤或其他良性腫瘤", "骨盆腔發炎（子宮、卵巢、輸卵管", "無上述症狀"};
    private final String [] _bladder_diseases = {"泌尿道系統相關疾病", "腎結石或輸尿管結石", "膀胱發炎或相關疾病", "攝護腺（前列腺）肥大或相關疾病", "慢性腎衰竭", "腎絲球腎炎", "腎水腫", "無上述症狀"};
    private final String [] _rectum_diseases = {"腸和腹膜疾病、胃腸出血", "痔瘡", "胃或十二指腸等消化道潰瘍或功能性障礙", "消化系統良性腫瘤", "無上述症狀"};

//    private Map<String, Boolean> _uterus_map;
//    private Map<String, Boolean> _ovary_map;
//    private Map<String, Boolean> _bladder_map;
//    private Map<String, Boolean> _rectum_map;

    public static synchronized DiseaseDataSingleton getInstance() {
        if(_instance == null)
            _instance = new DiseaseDataSingleton();
        return _instance;
    }

    public DiseaseDataSingleton() {
//        ArrayList<String []> cancers = new ArrayList<>(Arrays.asList(_uterus_diseases, _ovary_diseases, _bladder_diseases, _rectum_diseases));
//        ArrayList<Map<String, Boolean>> maps = new ArrayList<>();

        // store each cancer disease to distinct map from map[0] to map[3]
//        int i = 0;
//        for(String [] cancer_diseases : cancers) {
//            maps.add(new HashMap<>());
//            for (String disease : cancer_diseases)
//                maps.get(i).put(disease, false);
//            i++;
//        }
//
//        _uterus_map = maps.get(0);
//        _ovary_map = maps.get(1);
//        _bladder_map = maps.get(2);
//        _rectum_map = maps.get(3);
    }

    public String [] getCancerDiseaseList(String cancer) {
        if(cancer.equals(Constant.UTERUS))
            return _uterus_diseases;
        if(cancer.equals(Constant.OVARY))
            return _ovary_diseases;
        if(cancer.equals(Constant.BLADDER))
            return _bladder_diseases;
        if(cancer.equals(Constant.RECTUM))
            return _rectum_diseases;
        return null;
    }
//    public Map<String, Boolean> getCancerDiseases(String cancer) {
//        if(cancer.equals(Constant.UTERUS))
//            return _uterus_map;
//        if(cancer.equals(Constant.OVARY))
//            return _ovary_map;
//        if(cancer.equals(Constant.BLADDER))
//            return _bladder_map;
//        if(cancer.equals(Constant.RECTUM))
//            return _rectum_map;
//        return null;
//    }
//
//    public void update(String cancer, Map<String, Boolean> map) {
//        if(cancer.equals(Constant.UTERUS)) {
//            _uterus_map.clear();
//            _uterus_map.putAll(map);
//            for(var entry : _uterus_map.entrySet())
//                Log.d("3333", entry.getKey() + ": " + entry.getValue());
//        }
//        if(cancer.equals(Constant.OVARY))
//            _ovary_map = map;
//        if(cancer.equals(Constant.BLADDER))
//            _bladder_map = map;
//        if(cancer.equals(Constant.RECTUM))
//            _rectum_map = map;
//        throw new RuntimeException();
//    }
//
//    public void updateUterus(String disease) {
//        boolean check = _uterus_map.get(disease);
//        _uterus_map.replace(disease, !check);
//    }
}
