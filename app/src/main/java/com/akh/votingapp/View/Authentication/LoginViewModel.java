package com.akh.votingapp.View.Authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.UserRepo;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        userMutableLiveData = userRepo.getUserMutableLiveData();
    }

    public void login(String emial, String password){
        userRepo.logIn(emial,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData(){
        return userMutableLiveData;
    }
}
