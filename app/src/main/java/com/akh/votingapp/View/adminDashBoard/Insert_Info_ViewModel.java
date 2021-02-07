package com.akh.votingapp.View.adminDashBoard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.Information;
import com.akh.votingapp.Repo.InformationRepo;

import java.util.ArrayList;

public class Insert_Info_ViewModel extends AndroidViewModel {
    private InformationRepo infoRepo;
    private MutableLiveData<ArrayList<Information>> infoMutableLiveData;

    public Insert_Info_ViewModel(@NonNull Application application) {
        super(application);
        infoRepo = new InformationRepo(application);
        //cecMutableLiveData = cecRepo.getCECData();
    }
    public void insertInformation(Information info){
        infoRepo.insertInformation(info);
    }

   /* public MutableLiveData<ArrayList<CEC>> getCecMutableLiveData() {
        return cecMutableLiveData;
    }*/

}
