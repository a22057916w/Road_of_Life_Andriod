package com.bilab.lunsenluandroid.main;

public class Disease {
    private final String type;
    private final String name;

    public Disease(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
}
