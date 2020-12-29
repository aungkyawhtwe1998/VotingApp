package com.akh.votingapp.View.Authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.AuthRepo;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private AuthRepo authRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepo(application);
        userMutableLiveData = authRepo.getUserMutableLiveData();
    }

    public void login(String email, String password){
        authRepo.logIn(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData(){
        return userMutableLiveData;
    }
}
