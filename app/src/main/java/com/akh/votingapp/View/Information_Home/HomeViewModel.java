package com.akh.votingapp.View.Information_Home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.akh.votingapp.Model.Information;
import com.akh.votingapp.Repo.InformationRepo;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Information>> infoMutableLiveData;
    private InformationRepo infoRepo;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        infoRepo = new InformationRepo(application);
        infoMutableLiveData = infoRepo.loadInformation();

    }
    public MutableLiveData<ArrayList<Information>> getInfoMutableLiveData() {
        return infoMutableLiveData;
    }

}