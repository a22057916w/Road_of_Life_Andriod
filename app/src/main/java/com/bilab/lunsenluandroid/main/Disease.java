package com.bilab.lunsenluandroid.main;

import android.util.Log;

import java.util.ArrayList;

public class Disease {
    private final String _type;
    private final String _name;

    private ArrayList<String> _icd;

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
    public ArrayList<String> getICD() { return _icd; }

    public void loadICD() {
        DiseaseData diseaseData = DiseaseData.getInstance();
        _icd = diseaseData.getCancerICD10(_type, _name);
//        Log.d("icd", _icd.toString());
    }
}
