package com.bilab.lunsenluandroid.main;

public class Disease {
    private final String _type;
    private final String _name;

    public Disease(String type, String name) {
        this._type = type;
        this._name = name;
    }

    public String getName() {
        return _name;
    }
    public String getType() {
        return _type;
    }
}
