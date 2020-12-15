package com.akh.votingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.akh.votingapp.Model.UserRepo;
import com.google.firebase.auth.FirebaseUser;

public class LogoutViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logedOutMutableLiveData;

    public LogoutViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        userMutableLiveData = userRepo.getUserMutableLiveData();
        logedOutMutableLiveData = userRepo.getLoggedOutMutableLiveData();

    }

    public void signOut(){
        userRepo.signOut();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLogedOutMutableLiveData() {
        return logedOutMutableLiveData;
    }}
