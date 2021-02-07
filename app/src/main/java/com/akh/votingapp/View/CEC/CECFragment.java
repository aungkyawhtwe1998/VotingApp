package com.akh.votingapp.View.CEC;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.R;
import com.akh.votingapp.View.adminDashBoard.Insert_CEC_Fragment;

import java.util.ArrayList;

public class CECFragment extends Fragment implements CecItemListener{

    private CECViewModel cecViewModel;
    private RecyclerView recyclerView;
    private CECAdapter cecAdapter;
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE =0;
    public static final int REQUEST_CODE_SHOW_NOTE=-1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cecViewModel = new ViewModelProvider(this).get(CECViewModel.class);
        cecViewModel.getCecMutableLiveData().observe(this, new Observer<ArrayList<CEC>>() {
            @Override
            public void onChanged(ArrayList<CEC> cecList) {
                if(cecList!=null){
                    cecAdapter = new CECAdapter(cecList,getContext());
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
//        cecViewModel = new ViewModelProvider(this).get(CECViewModel.class);
       // cecViewModel.init(this.getContext());
        //cecAdapter = new CECAdapter(cecViewModel.getCecMutableliveData().getValue());
        return root;
    }

    @Override
    public void onItemClick(CEC cec, int position) {
        Navigation.findNavController(getView()).navigate(R.id.action_nav_cec_to_insert_CEC_Fragment);

//        Intent intent = new Intent(getContext(), Insert_CEC_Fragment.class);
//        intent.putExtra("isViewOrUpdate",true);
//        intent.putExtra("cecObj",cec);
//        startActivityForResult(intent,REQUEST_CODE_UPDATE_NOTE);
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