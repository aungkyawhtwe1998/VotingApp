package com.akh.votingapp.View.Information_Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.Model.Information;
import com.akh.votingapp.R;
import com.akh.votingapp.View.CEC.CECAdapter;
import com.akh.votingapp.View.CEC.CECViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private InformationAdapter informationAdapter;
    private HomeViewModel informationViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        informationViewModel.getInfoMutableLiveData().observe(this, new Observer<ArrayList<Information>>() {
            @Override
            public void onChanged(ArrayList<Information> infoList) {
                if(infoList!=null){
                    informationAdapter = new InformationAdapter(infoList,getContext());
                    recyclerView.setAdapter(informationAdapter);
                    informationAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        recyclerView = root.findViewById(R.id.rec_info);
      /*  cecViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        return root;
    }


}