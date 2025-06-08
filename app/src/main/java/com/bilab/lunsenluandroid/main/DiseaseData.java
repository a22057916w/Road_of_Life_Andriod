package com.bilab.lunsenluandroid.main;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.bilab.lunsenluandroid.conf.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DiseaseData {
    private static DiseaseData _instance;
    private final String [] _cancers = {Constant.UTERUS, Constant.OVARY, Constant.BLADDER, Constant.RECTUM, Constant.DKD, Constant.PREEMIE};
    private final String [] _uterus_diseases = {"子宮相關疾病", "月經失調或女性生殖道異常出血", "子宮平滑肌瘤或其他良性腫瘤", "子宮內膜異位症", "貧血症狀", "無上述症狀"};
    private final String [] _ovary_diseases = {"卵巢良性腫瘤", "卵巢或輸卵管非發炎性疾病", "子宮內膜異位症", "子宮平滑肌瘤或其他良性腫瘤", "骨盆腔發炎（子宮、卵巢、輸卵管)", "無上述症狀"};
    private final String [] _bladder_diseases = {"泌尿道系統相關疾病", "腎結石或輸尿管結石", "膀胱發炎或相關疾病", "攝護腺（前列腺）肥大或相關疾病", "慢性腎衰竭", "腎絲球腎炎", "腎水腫", "無上述症狀"};
    private final String [] _rectum_diseases = {"腸和腹膜疾病、胃腸出血", "痔瘡", "胃或十二指腸等消化道潰瘍或功能性障礙", "消化系統良性腫瘤", "無上述症狀"};
    private final String[] _DKD_diseases = {"視網膜之其他疾患", "類脂質代謝疾患", "高血壓性心臟病", "本態性高血壓", "其他形態之慢性缺血性心臟病", "慢性肝病及肝硬化", "侵及皮膚及其他外皮組織之徵候", "痛風", "白內障", "其他蜂窩組織炎及膿瘍", "無上述症狀"};
    private final String[] _preemie_diseases = {"多胎胎妊娠", "骨盆及器官及軟組織異常", "阻礙性分娩", "其他產程創傷", "無上述症狀"};




    private final String [] _uterus_ICD9 = {"621", "626", "218", "617", "285"};
    private final String [] _ovary_ICD9 = {"220", "620", "617", "218", "614"};
    private final String [] _bladder_ICD9 = {"599", "592", "595", "600", "585", "582", "591"};
    private final String [] _rectum_ICD9 = {"578", "455", "532", "211"};
    private final String[] _DKD_ICD9 = {"362", "272", "402", "401", "414", "571", "782", "274", "366", "628"};
    private final String[] _preemie_ICD9 = {"651", "654", "660", "665"};




    private final String [][] _uterus_ICD10 = {{"N840"}, {"N91", "N924"}, {"D250", "D260"}, {"N800"}, {"D461"}};
    private final String [][] _ovary_ICD10 = {{"D270", "D271", "D279"}, {"N830"}, {"N800"}, {"D250", "D260"}, {"N7001", "N7002", "N7003"}};    // D270, D271, D279 -> 左、右、其他位置卵巢良性腫瘤
    private final String [][] _bladder_ICD10 = {{"N390", "N23"}, {"N200"}, {"N3000", "N3001", "N320"}, {"N400", "N401", "N410"}, {"N184", "N185", "N186", "N189"}, {"N032"}, {"N1330"}};
    private final String [][] _rectum_ICD10 = {{"K920", "K561", "K5710", "K5900", "K602", "K610", "K611", "K67", "K660", "K620", "K621"}, {"K640", "K641", "K642", "K643"}, {"K260", "K5660", "K3183", "K5900", "K620", "K621"}, {"D130"}};
    private final String[][] _DKD_ICD10 = {
            {"E11311", "E11319", "E11321", "E11329", "E11331", "E11339", "E11341", "E11349", "E11351", "E11359"},   // 362
            {"E780"},
            {"I119"},
            {"I10"},
            {"I2510", "I25750", "I25751", "I25758", "I25759", "I25760", "I25761", "I25768", "I25769", "I25811", "I25812"},  // 414
            {"K700"},
            {"R200", "R201", "R202", "R203", "R208", "R209"},   // 782
            {"M1000"},  // icd9-274 has total 232 icd10 code (need database)
            {"H26001", "H26002", "H26003", "H26009"},   // 366
            {"K122", "L0201", "L03211", "L03212"}

    };    private final String[][] _preemie_ICD10 = {{"O30009", "O30019", "O30099"}, {"O3400"}, {"O649XX0"}, {"O7100"}};




    private final Map<String, Double> _uterus_ICD9_OR = new HashMap<>(Map.of(
            "621", 14.88213886,
            "626", 4.806825945,
            "218", 4.254041372,
            "617", 3.84327046,
            "285",3.144849537
    ));
    private final Map<String, Double> _ovary_ICD9_OR = new HashMap<>(Map.of(
            "220", 12.89244308,
            "620", 7.899896036,
            "617", 4.064415584,
            "218", 2.529506197,
            "614", 2.170676786
    ));
    private final Map<String, Double> _bladder_ICD9_OR = new HashMap<>(Map.of(
            "599", 11.10875633,
            "592", 5.25472384,
            "595", 3.554870883,
            "600", 3.217163913,
            "585", 3.177317732,
            "582", 2.274417518,
            "591", 4.961730141
    ));
    private final Map<String, Double> _rectum_ICD9_OR = new HashMap<>(Map.of(
            "578", 3.803068867,
            "455", 3.476618971,
            "532", 2.733588054,
            "211", 2.595223918
    ));
    private final Map<String, Double> _DKD_ICD9_OR = new HashMap<>(Map.of(
            "362", 7.146205206,
            "272", 4.360902339,
            "402", 4.067713144,
            "401", 3.469819913,
            "414", 3.022614885,
            "571", 2.661819534,
            "782", 2.566195556,
            "274", 2.470995713,
            "366", 2.158486901,
            "682", 2.081902885
    ));
    private final Map<String, Double> _preemie_ICD9_OR = new HashMap<>(Map.of(
            "651", 47.08643,
            "654", 20.07121,
            "660", 13.78691,
            "665", 10.54598
    ));





    private Map<String, Map<String, Double>> _wCancerDiseases;
    private Map<String, Double> _bCancers;

    public static synchronized DiseaseData getInstance() {
        if(_instance == null)
            _instance = new DiseaseData();
        return _instance;
    }

    public DiseaseData() {
    }

    public ArrayList<String> getCancerDiseaseList(String cancer) {
        if(cancer.equals(Constant.UTERUS))
            return new ArrayList<>(List.of(_uterus_diseases));
        if(cancer.equals(Constant.OVARY))
            return new ArrayList<>(List.of(_ovary_diseases));
        if(cancer.equals(Constant.BLADDER))
            return new ArrayList<>(List.of(_bladder_diseases));
        if(cancer.equals(Constant.RECTUM))
            return new ArrayList<>(List.of(_rectum_diseases));
        if(cancer.equals(Constant.DKD))
            return new ArrayList<>(List.of(_DKD_diseases));
        if(cancer.equals(Constant.PREEMIE))
            return new ArrayList<>(List.of(_preemie_diseases));

        return null;
    }

    public String getCancerICD9(String cancer, String name) {
        if(cancer.equals(Constant.UTERUS)) {
            for(int i = 0; i < _uterus_diseases.length - 1; i++)
                if(_uterus_diseases[i].equals(name))
                    return _uterus_ICD9[i];
        }
        if(cancer.equals(Constant.OVARY)) {
            for(int i = 0; i < _ovary_diseases.length - 1; i++)
                if(_ovary_diseases[i].equals(name))
                    return _ovary_ICD9[i];
        }
        if(cancer.equals(Constant.BLADDER)) {
            for(int i = 0; i < _bladder_diseases.length - 1; i++)
                if(_bladder_diseases[i].equals(name))
                    return _bladder_ICD9[i];
        }
        if(cancer.equals(Constant.RECTUM)) {
            for(int i = 0; i < _rectum_diseases.length - 1; i++)
                if(_rectum_diseases[i].equals(name))
                    return _rectum_ICD9[i];
        }
        if(cancer.equals(Constant.DKD)) {
            for (int i = 0; i < _DKD_diseases.length - 1; i++)
                if (_DKD_diseases[i].equals(name))
                    return _DKD_ICD9[i];
        }
        if(cancer.equals(Constant.PREEMIE)) {
            for(int i = 0; i < _preemie_diseases.length - 1; i++)
                if(_preemie_diseases[i].equals(name))
                    return _preemie_ICD9[i];
        }
        // 無上述症狀返回-1
        return "-1";
    }

    public final String[] getCancerICD9(String cancer) {
        if(cancer.equals(Constant.UTERUS)) {
            return _uterus_ICD9;
        }
        if(cancer.equals(Constant.OVARY)) {
            return _ovary_ICD9;
        }
        if(cancer.equals(Constant.BLADDER)) {
            return _bladder_ICD9;
        }
        if(cancer.equals(Constant.RECTUM)) {
            return _rectum_ICD9;
        }
        if(cancer.equals(Constant.DKD)) {
            return _DKD_ICD9;
        }
        if(cancer.equals(Constant.PREEMIE)) {
            return _preemie_ICD9;
        }
        // 無上述症狀返回0
        return new String[]{"-1"};
    }

    public ArrayList<String> getCancerICD10(String cancer, String name) {
        if(cancer.equals(Constant.UTERUS)) {
            for(int i = 0; i < _uterus_diseases.length - 1; i++)
                if(_uterus_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_uterus_ICD10[i]));
        }
        if(cancer.equals(Constant.OVARY)) {
            for(int i = 0; i < _ovary_diseases.length - 1; i++)
                if(_ovary_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_ovary_ICD10[i]));
        }
        if(cancer.equals(Constant.BLADDER)) {
            for(int i = 0; i < _bladder_diseases.length - 1; i++)
                if(_bladder_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_bladder_ICD10[i]));
        }
        if(cancer.equals(Constant.RECTUM)) {
            for(int i = 0; i < _rectum_diseases.length - 1; i++)
                if(_rectum_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_rectum_ICD10[i]));
        }
        if(cancer.equals(Constant.DKD)) {
            for(int i = 0; i < _DKD_diseases.length - 1; i++)
                if(_DKD_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_DKD_ICD10[i]));
        }
        if(cancer.equals(Constant.PREEMIE)) {
            for(int i = 0; i < _preemie_diseases.length - 1; i++)
                if(_preemie_diseases[i].equals(name))
                    return new ArrayList<>(List.of(_preemie_ICD10[i]));
        }
        // 無上述症狀返回-1
        return new ArrayList<>(List.of("-1"));
    }

    public final String[][] getCancerICD10(String cancer) {
        if(cancer.equals(Constant.UTERUS)) {
            return _uterus_ICD10;
        }
        if(cancer.equals(Constant.OVARY)) {
            return _ovary_ICD10;
        }
        if(cancer.equals(Constant.BLADDER)) {
            return _bladder_ICD10;
        }
        if(cancer.equals(Constant.RECTUM)) {
            return _rectum_ICD10;
        }
        if(cancer.equals(Constant.DKD)) {
            return _DKD_ICD10;
        }
        if(cancer.equals(Constant.PREEMIE)) {
            return _preemie_ICD10;
        }
        // 無上述症狀返回0
        return new String[][]{new String[]{"0"}};
    }

    public final Double getICD9OR(String cancer, String icd9) {
        if(cancer.equals(Constant.UTERUS)) {
            return _uterus_ICD9_OR.get(icd9);
        }
        if(cancer.equals(Constant.OVARY)) {
            return _ovary_ICD9_OR.get(icd9);
        }
        if(cancer.equals(Constant.BLADDER)) {
            return _bladder_ICD9_OR.get(icd9);
        }
        if(cancer.equals(Constant.RECTUM)) {
            return _rectum_ICD9_OR.get(icd9);
        }
        if(cancer.equals(Constant.DKD)) {
            return _DKD_ICD9_OR.get(icd9);
        }
        if(cancer.equals(Constant.PREEMIE)) {
            return _preemie_ICD9_OR.get(icd9);
        }
        // 無上述症狀返回-1
        return -1.0D;
    }

    public String[] getCancers() {
        return _cancers;
    }

    public Map<String, Double> getWDiseases(String type) {
        return _wCancerDiseases.get(type);
    }
    public Double getBCancer(String type) {
        return _bCancers.get(type);
    }

    public void setWCancerDiseases(Map<String, Map<String, Double>> wCancerDisease) {
        _wCancerDiseases = wCancerDisease;
    }

    public void setBCancers(Map<String, Double> bCancers) {
        _bCancers = bCancers;
    }
}
