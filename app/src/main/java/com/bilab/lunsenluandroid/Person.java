package com.bilab.lunsenluandroid;

import com.bilab.lunsenluandroid.util.Constant;

import java.util.ArrayList;

public class Person {
    private static Person instance;

    private String name;
    private String weight;
    private int year;

    private ArrayList<Disease> _diseases;
    public static synchronized Person getInstance() {
        if(instance == null)
            instance = new Person();
        return instance;
    }

    public Person() {
        _diseases = new ArrayList<>();
    };

    public void updateDisease(Disease disease) {
        int pos = findDisease(disease);
        if(pos != 0)
            _diseases.remove(pos);
        else
            _diseases.add(disease);
    }

    public int findDisease(Disease disease) {
        if(_diseases.size() < 1)
            return Constant.npos;

        int pos = -1;
        for(Disease dis : _diseases) {
            pos++;
            if(dis.getName().equals(disease.getName()) && dis.getType().equals(disease.getType()))
                break;
        }

        return pos;
    }
}
