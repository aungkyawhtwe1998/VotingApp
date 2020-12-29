package com.akh.votingapp.View.adminDashBoard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.Model.CECRepo;

import java.util.ArrayList;

public class Insert_CEC_ViewModel extends AndroidViewModel {
    private CECRepo cecRepo;
    private MutableLiveData<ArrayList<CEC>> cecMutableLiveData;

    public Insert_CEC_ViewModel(@NonNull Application application) {
        super(application);
        cecRepo = new CECRepo(application);
        //cecMutableLiveData = cecRepo.getCECData();
    }
    public void insertCECData(CEC cec){
        cecRepo.insertCEC(cec);
    }

   /* public MutableLiveData<ArrayList<CEC>> getCecMutableLiveData() {
        return cecMutableLiveData;
    }*/

}
