package com.bilab.lunsenluandroid.main;

import java.util.ArrayList;

public class Disease {
    private final String _type;
    private final String _name;

    private String _icd9;
    private ArrayList<String> _icd10;
    private Double _oddsRatio; // for single icd9

    public Disease(String type, String name) {
        this._type = type;
        this._name = name;
        loadICD();
        loadOR();
    }

    public String getName() {
        return _name;
    }
    public String getType() {
        return _type;
    }
    public String getICD9() { return _icd9; }
    public ArrayList<String> getICD10() { return _icd10; }

    private void loadICD() {
        DiseaseData diseaseData = DiseaseData.getInstance();
        _icd10 = diseaseData.getCancerICD10(_type, _name);
        _icd9 = diseaseData.getCancerICD9(_type, _name);
//        Log.d("icd", _icd.toString());
    }

    private void loadOR() {
        DiseaseData diseaseData = DiseaseData.getInstance();
        _oddsRatio = diseaseData.getICD9OR(_type, _icd9);
    }
}
