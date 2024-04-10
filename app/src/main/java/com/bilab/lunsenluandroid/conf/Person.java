package com.bilab.lunsenluandroid.conf;

import android.util.Log;

import com.bilab.lunsenluandroid.main.Disease;

import java.util.ArrayList;

public class Person {
    private static Person _instance;

    private String _name;
    private String _height, _weight, _year, _gender;
    private ArrayList<Disease> _diseases;
    public static synchronized Person getInstance() {
        if(_instance == null)
            _instance = new Person();
        return _instance;
    }

    public Person() {
        _height = "";
        _weight = "";
        _year = "";
        _diseases = new ArrayList<>();
    };

    public String getHeight() {
        return _height;
    }
    public String getWeight() {
        return _weight;
    }
    public String getYear() {
        return _year;
    }
    public String getGender() {
        return _gender;
    }
    public ArrayList<Disease> getAllDisease() { return _diseases; }

    public void setHeight(String height) {
        _height = height;
    }
    public void setWeight(String weight) {
        _weight = weight;
    }
    public void setYear(String year) {
        _year = year;
    }
    public void setGender(String gender) {
        _gender = gender;
    }

    public void updateDisease(Disease disease) {
        int pos = hasDisease(disease);

        if(pos != Constant.npos)
            _diseases.remove(pos);
        else
            _diseases.add(disease);
        Log.d("Person", "Size: " + _diseases.size());
    }

    public void addDisease(Disease disease) {
        int pos = hasDisease(disease);

        if(pos != Constant.npos)
            return;
        else {
            _diseases.add(disease);
            removeDisease(disease.getType(), "無上述症狀");
        }
    }

    public int hasDisease(Disease disease) {
        if(_diseases.size() < 1)
            return Constant.npos;

        int pos = -1;
        for(Disease dis : _diseases) {
            pos++;
            if(dis.getName().equals(disease.getName()) && dis.getType().equals(disease.getType()))
                return pos;
        }
        return Constant.npos;
    }

    public void removeDisease(String type, String name) {
        for(int i = 0; i < _diseases.size(); i++)
            if(_diseases.get(i).getType().equals(type) && _diseases.get(i).getName().equals(name)) {
                _diseases.remove(i);
                break;
            }
    }

    public void clearDisease(String cancer) {
        for(var it = _diseases.iterator(); it.hasNext();)
            if(it.next().getType().equals(cancer))
                it.remove();
    }

    public boolean isHealth() {
        int count = 0;
        for(var disease: _diseases)
            if(disease.getName().equals(Constant.NO_ABOVE_DISEASE))
                count++;
        return _diseases.size() == count;
    }

    public ArrayList<String> getDiseaseNames(String type) {
        ArrayList<String> diseases = new ArrayList<>();
        for(int i = 0; i < _diseases.size(); i++)
            if(_diseases.get(i).getType().equals(type))
                diseases.add(_diseases.get(i).getName());

        return diseases;
    }

    public ArrayList<Integer> getDiseaseICD9(String type) {
        ArrayList<Integer> icd9s = new ArrayList<>();
        for(int i = 0; i < _diseases.size(); i++)
            if(_diseases.get(i).getType().equals(type))
                icd9s.add(_diseases.get(i).getICD9());

        return icd9s;
    }
}
