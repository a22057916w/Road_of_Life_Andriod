package com.bilab.lunsenluandroid.main;

import java.util.ArrayList;

public class Disease {
    private final String _type;
    private final String _name;

    private Integer _icd9;
    private ArrayList<String> _icd10;

    public Disease(String type, String name) {
        this._type = type;
        this._name = name;
        loadICD();
    }

    public String getName() {
        return _name;
    }
    public String getType() {
        return _type;
    }
    public Integer getICD9() { return _icd9; }
    public ArrayList<String> getICD10() { return _icd10; }

    public void loadICD() {
        DiseaseData diseaseData = DiseaseData.getInstance();
        _icd10 = diseaseData.getCancerICD10(_type, _name);
        _icd9 = diseaseData.getCancerICD9(_type, _name);
//        Log.d("icd", _icd.toString());
    }

}
