package com.akh.votingapp.View.Authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.UserRepo;
import com.google.firebase.auth.FirebaseUser;

public class SignUpViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        userMutableLiveData = userRepo.getUserMutableLiveData();
    }
    public void signUp(String email, String password, String username){
        userRepo.signUp(email, password, username);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
