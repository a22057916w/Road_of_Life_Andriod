package com.bilab.lunsenluandroid.model;

public class DiseaseHomeModel extends DiseaseModel {
    private String disease_description;
    private String disease_risk;
    private String type;


    // Constructor
    public DiseaseHomeModel(String disease_name, String disease_description, String disease_risk, int disease_image, String type) {
        super(disease_name, disease_image);
        this.disease_description = disease_description;
        this.disease_risk = disease_risk;
        this.type = type;
    }

    // Getter and Setter
    public final String getDiseaseDescription() {
        return disease_description;
    }
    public final String getDiseaseRisk() {
        return disease_risk;
    }
    public final String getType() { return type; }

}
