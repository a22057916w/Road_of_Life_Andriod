package com.bilab.lunsenluandroid.model;

public class DiseaseSelectionModel extends DiseaseModel{
    private Boolean selected;
    public DiseaseSelectionModel(String disease_name, int disease_image, Boolean selected) {
        super(disease_name, disease_image);
        this.selected = selected;
    }

    public Boolean isSelected() {
        return selected;
    }
}
