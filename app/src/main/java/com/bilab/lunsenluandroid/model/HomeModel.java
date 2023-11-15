package com.bilab.lunsenluandroid.model;

public class HomeModel {

    private String disease_name;
    private String disease_description;
    private String risk_percentage;
    private int disease_image;

    // Constructor
    public HomeModel(String disease_name, String disease_description, String risk_percentage, int disease_image) {
        this.disease_name = disease_name;
        this.disease_description = disease_description;
        this.disease_image = disease_image;
        this.risk_percentage = risk_percentage;
    }

    // Getter and Setter
    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getDisease_description() {
        return disease_description;
    }

    public void setDisease_description(String disease_description) {
        this.disease_description = disease_description;
    }

    public int getDisease_image() {
        return disease_image;
    }

    public String getRisk_percentage() {
        return risk_percentage;
    }

    public void setDisease_image(int disease_image) {
        this.disease_image = disease_image;
    }
}
