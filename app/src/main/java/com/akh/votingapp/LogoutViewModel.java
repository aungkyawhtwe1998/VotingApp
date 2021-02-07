package com.akh.votingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Repo.AuthRepo;
import com.google.firebase.auth.FirebaseUser;

public class LogoutViewModel extends AndroidViewModel {
    private AuthRepo authRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logedOutMutableLiveData;

    public LogoutViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepo(application);
        userMutableLiveData = authRepo.getUserMutableLiveData();
        logedOutMutableLiveData = authRepo.getLoggedOutMutableLiveData();

    }

    public void signOut(){
        authRepo.signOut();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLogedOutMutableLiveData() {
        return logedOutMutableLiveData;
    }}
