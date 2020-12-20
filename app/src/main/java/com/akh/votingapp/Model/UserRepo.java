package com.akh.votingapp.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.LogoutViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class UserRepo {
    static UserRepo instance;
    private ArrayList<LogoutViewModel> logoutViewModels;
    public static UserRepo getInstance(){
        if(instance == null){
            instance = new UserRepo();
        }return instance;
    }

    public MutableLiveData<ArrayList<LogoutViewModel>> getName(){
        loadNames();
        MutableLiveData<ArrayList<LogoutViewModel>> name = new MutableLiveData<>();
        name.setValue(logoutViewModels);
        return name;
    }

    private void loadNames(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snp : snapshot.getChildren()){
                    logoutViewModels.add(snapshot.getValue(LogoutViewModel.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
