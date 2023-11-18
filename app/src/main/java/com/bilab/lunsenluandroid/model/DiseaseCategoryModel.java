package com.bilab.lunsenluandroid.model;

public class DiseaseCategoryModel {

    private String disease_name;
    private String disease_amount;
    private int disease_image;

    public DiseaseCategoryModel(String disease_name, String disease_amount, int disease_image) {
        this.disease_name = disease_name;
        this.disease_amount = disease_amount;
        this.disease_image = disease_image;
    }

    // Getter
    public String getDiseaseName() { return disease_name; }
    public String getDiseaseAmount() { return disease_amount; }
    public int getDiseaseImage() { return disease_image; }
}
