package com.bilab.lunsenluandroid;

import com.bilab.lunsenluandroid.util.Constant;

public class DiseaseData {
    private static DiseaseData _instance;
    private final String [] _uterus_diseases = {"子宮相關疾病", "月經失調或女性生殖道異常出血", "子宮平滑肌瘤或其他良性腫瘤", "子宮內膜異位症", "貧血症狀", "無上述症狀"};
    private final String [] _ovary_diseases = {"卵巢良性腫瘤", "卵巢或輸卵管非發炎性疾病", "子宮內膜異位症", "子宮平滑肌瘤或其他良性腫瘤", "骨盆腔發炎（子宮、卵巢、輸卵管", "無上述症狀"};
    private final String [] _bladder_diseases = {"泌尿道系統相關疾病", "腎結石或輸尿管結石", "膀胱發炎或相關疾病", "攝護腺（前列腺）肥大或相關疾病", "慢性腎衰竭", "腎絲球腎炎", "腎水腫", "無上述症狀"};
    private final String [] _rectum_diseases = {"腸和腹膜疾病、胃腸出血", "痔瘡", "胃或十二指腸等消化道潰瘍或功能性障礙", "消化系統良性腫瘤", "無上述症狀"};


    public static synchronized DiseaseData getInstance() {
        if(_instance == null)
            _instance = new DiseaseData();
        return _instance;
    }

    public DiseaseData() {
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
