package com.bilab.lunsenluandroid;

import android.util.Log;

import com.bilab.lunsenluandroid.util.Constant;

import java.util.ArrayList;

public class Person {
    private static Person _instance;

    private String name;
    private String weight;
    private int year;

    private ArrayList<Disease> _diseases;
    public static synchronized Person getInstance() {
        if(_instance == null)
            _instance = new Person();
        return _instance;
    }

    public Person() {
        _diseases = new ArrayList<>();
    };

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
}
