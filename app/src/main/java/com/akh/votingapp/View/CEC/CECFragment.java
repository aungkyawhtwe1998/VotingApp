package com.akh.votingapp.View.CEC;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akh.votingapp.Adapter.CECAdapter;
import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.R;

import java.util.ArrayList;

public class CECFragment extends Fragment {

    private CECViewModel cecViewModel;
    private RecyclerView recyclerView;
    private CECAdapter cecAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cecViewModel = new ViewModelProvider(this).get(CECViewModel.class);
        cecViewModel.getCecMutableLiveData().observe(this, new Observer<ArrayList<CEC>>() {
            @Override
            public void onChanged(ArrayList<CEC> cecList) {
                if(cecList!=null){
                    cecAdapter = new CECAdapter(cecList);
                    recyclerView.setAdapter(cecAdapter);
                    cecAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cec, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        recyclerView = root.findViewById(R.id.recycler_cec);
      /*  cecViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        cecViewModel = new ViewModelProvider(this).get(CECViewModel.class);
       // cecViewModel.init(this.getContext());
        //cecAdapter = new CECAdapter(cecViewModel.getCecMutableliveData().getValue());
        return root;
    }

//    @Override
//    public void onCECDataLoaded() {
//        cecViewModel.getCec().observe(this, new Observer<ArrayList<CEC>>() {
//            @Override
//            public void onChanged(ArrayList<CEC> cecs) {
//                cecAdapter.notifyDataSetChanged();
//            }
//        });
//    }
}