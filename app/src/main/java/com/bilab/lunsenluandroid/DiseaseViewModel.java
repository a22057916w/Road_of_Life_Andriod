package com.bilab.lunsenluandroid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiseaseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DiseaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}