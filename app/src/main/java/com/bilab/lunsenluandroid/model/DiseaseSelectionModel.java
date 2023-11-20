package com.bilab.lunsenluandroid.model;

public class DiseaseSelectionModel {
    private String disease_name;
    private int disease_image;

    // Constructor
    public DiseaseSelectionModel(String disease_name, int disease_image) {
        this.disease_name = disease_name;
        this.disease_image = disease_image;
    }

    // Getter
    public String getDisease_name() { return disease_name; }

    public int getDisease_image() { return disease_image; }
}
