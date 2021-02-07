package com.akh.votingapp.Repo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.Information;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InformationRepo {
    static CECRepo instance;
    private Application application;
    private ArrayList<Information> info;
    private MutableLiveData<ArrayList<Information>> infoMutableLiveData;
    private DatabaseReference db;
    FirebaseUser fuser;

    public InformationRepo(Application context){
        db= FirebaseDatabase.getInstance().getReference();
        infoMutableLiveData = new MutableLiveData<ArrayList<Information>>();
        info=new ArrayList<>();
        this.application = context;
        fuser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public ArrayList<Information> getInfo() {
        return info;
    }

    public MutableLiveData<ArrayList<Information>> loadInformation(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Information");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        info.add(snap.getValue(Information.class));
                    }
                    infoMutableLiveData.setValue(info);
                    Log.d("InfoRepo", "SnapShotTesting: "+info.get(0).getDescription());

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return infoMutableLiveData;
    }


    public void insertInformation(Information info){
        db = FirebaseDatabase.getInstance().getReference("Information");
        String id = db.push().getKey();
        db.child(id).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(application.getApplicationContext(),"Uploaded Information",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(application.getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
