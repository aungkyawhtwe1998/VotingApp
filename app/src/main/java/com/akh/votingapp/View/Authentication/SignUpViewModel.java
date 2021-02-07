package com.akh.votingapp.View.Authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Repo.AuthRepo;
import com.google.firebase.auth.FirebaseUser;

public class SignUpViewModel extends AndroidViewModel {
    private AuthRepo authRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepo(application);
        userMutableLiveData = authRepo.getUserMutableLiveData();
    }
    public void signUp(String email, String password, String username){
        authRepo.signUp(email, password, username);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
