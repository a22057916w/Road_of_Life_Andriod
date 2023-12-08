package com.bilab.lunsenluandroid.model;

public class DiseaseModel {
    protected String disease_name;
    protected  int disease_image;

    public DiseaseModel(String disease_name, int disease_image) {
        this.disease_name = disease_name;
        this.disease_image = disease_image;
    }

    public final String getDiseaseName() {
        return disease_name;
    }
    public final int getDiseaseImage() {
        return disease_image;
    }
}
