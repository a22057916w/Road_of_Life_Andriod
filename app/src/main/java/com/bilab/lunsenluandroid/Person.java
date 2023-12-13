package com.bilab.lunsenluandroid;

import android.util.Log;

import com.bilab.lunsenluandroid.util.Constant;

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
        int pos = findDisease(disease);

        if(pos != Constant.npos)
            _diseases.remove(pos);
        else
            _diseases.add(disease);
        Log.d("Person", "Size: " + _diseases.size());
    }

    public int findDisease(Disease disease) {
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

    public void clearDisease(String cancer) {
        for(var it = _diseases.iterator(); it.hasNext();)
            if(it.next().getType().equals(cancer))
                it.remove();
    }

    public boolean hasAnyDisease() {
        int size = _diseases.size();
        for(var disease : _diseases)
            if(disease.getName().equals(Constant.NO_ABOVE_DISEASE))
                size--;
        return size > 0;
    }

    public boolean isHealth() {
        int count = 0;
        for(var disease: _diseases)
            if(disease.getName().equals(Constant.NO_ABOVE_DISEASE))
                count++;
        return _diseases.size() == count;
    }
}
