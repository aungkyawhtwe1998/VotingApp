package com.akh.votingapp.View.CEC;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.Model.CECRepo;

import java.util.ArrayList;

public class CECViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<CEC>> cecMutableLiveData;
    private CECRepo cecRepo;
    public CECViewModel(@NonNull Application application) {
        super(application);
        cecRepo = new CECRepo(application);
        cecMutableLiveData = cecRepo.loadCECData();

    }

/*    public void init(Application context){
        if(cec!=null){
            return;
        }
        cec = CECRepo.getInstance(context).getCECData();
    }*/

    public MutableLiveData<ArrayList<CEC>> getCecMutableLiveData() {
        return cecMutableLiveData;
    }

}