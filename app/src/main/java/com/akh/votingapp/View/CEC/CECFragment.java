package com.akh.votingapp.View.CEC;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CECFragment extends Fragment implements CecItemListener{

    private CECViewModel cecViewModel;
    private RecyclerView recyclerView;
    private CECAdapter cecAdapter;
    int votedPersonType;
    private RadioGroup rdbGroup;
    private RadioButton rdb_cec, rdb_internal, rdb_budget;
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
                    for(int i=0; i<cecList.size(); i++){
                        if(cecList.get(i).getCecType()==votedPersonType){
                            cecAdapter = new CECAdapter(cecList,getContext(),CECFragment.this);
                            recyclerView.setAdapter(cecAdapter);
                            cecAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?


    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cec, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        rdbGroup = root.findViewById(R.id.rdb_cec_group);
        recyclerView = root.findViewById(R.id.recycler_cec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rdbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdb_ceo:
                        votedPersonType=0;
                        break;
                    case R.id.rdb_internal:
                        votedPersonType = 1;
                        break;
                    case R.id.rdb_finance:
                        votedPersonType = 2;
                        break;
                    default:

                }
                Toast.makeText(getContext(), "radio button selected " + votedPersonType, Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onItemClick(CEC cec, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure to vote:"+cec.getName())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String votedPersonID = cec.getId();
                        String votedDate = DateFormat.getDateTimeInstance().format(new Date());
                        cecViewModel.insertVote(votedPersonType,votedPersonID,votedDate);
                        Toast.makeText(getContext(),"You have voted to:"+cec.getName(),Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
//        Navigation.findNavController(getView()).navigate(R.id.action_nav_cec_to_insert_CEC_Fragment);
//        Intent intent = new Intent(getContext(), Insert_CEC_Fragment.class);
//        intent.putExtra("isViewOrUpdate",true);
//        intent.putExtra("cecObj",cec);
//        startActivityForResult(intent,REQUEST_CODE_UPDATE_NOTE);
    }

}