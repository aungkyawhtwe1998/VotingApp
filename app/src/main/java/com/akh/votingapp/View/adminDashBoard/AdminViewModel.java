package com.akh.votingapp.View.adminDashBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public AdminViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Admin dashboard");
    }

    public LiveData<String> getMText() {
        return mText;
    }
}
