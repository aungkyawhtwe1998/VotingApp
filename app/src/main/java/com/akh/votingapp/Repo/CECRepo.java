package com.akh.votingapp.Repo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.akh.votingapp.Model.CEC;
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
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;

public class CECRepo {
    static CECRepo instance;
    private Application application;
    private ArrayList<CEC> cec;
    private MutableLiveData<ArrayList<CEC>> cecMutableLiveData;
    private DatabaseReference db;
    FirebaseUser fuser;

    private StorageTask uploadTask;


    public CECRepo(Application context){
        db=FirebaseDatabase.getInstance().getReference();
        cecMutableLiveData = new MutableLiveData<ArrayList<CEC>>();
        cec=new ArrayList<>();
        this.application = context;
        fuser = FirebaseAuth.getInstance().getCurrentUser();

    }

/*
    public CECRepo(Application application){
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
        cecMutableLiveData = new MutableLiveData<>();

    }*/

    public ArrayList<CEC> getCec() {
        return cec;
    }

    /*public MutableLiveData<ArrayList<CEC>> getCecMutableLiveData() {
        loadCECData();
        return cecMutableLiveData;
    }*/

    public MutableLiveData<ArrayList<CEC>> loadCECData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("CEC");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        cec.add(snap.getValue(CEC.class));
                    }
                    cecMutableLiveData.setValue(cec);
                    Log.d("CECRepo", "SnapShotTesting: "+cec.get(0).name);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return cecMutableLiveData;
    }


    public void insertCEC(CEC cec){
        db = FirebaseDatabase.getInstance().getReference("CEC");
        String id = db.push().getKey();
        /*HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id",id);
        hashMap.put("name", cec.getName());
        hashMap.put("department",cec.getDepartment());
        hashMap.put("position",cec.getPosition());
        hashMap.put("email",cec.getEmail());
        hashMap.put("cec_position", cec.getCec_position());
        hashMap.put("description",cec.getDescription());
        hashMap.put("imageUrl",cec.getImgUrl());*/
        db.child(id).setValue(cec).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(application.getApplicationContext(),"Saved CEC",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(application.getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
