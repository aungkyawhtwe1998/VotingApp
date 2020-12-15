package com.akh.votingapp.View.CEC;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CECViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CECViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is CEC fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}