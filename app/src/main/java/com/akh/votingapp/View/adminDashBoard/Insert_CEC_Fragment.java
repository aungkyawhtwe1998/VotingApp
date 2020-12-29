package com.akh.votingapp.View.adminDashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.R;
import com.akh.votingapp.View.CEC.CECViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Insert_CEC_Fragment extends Fragment {
    private Spinner spinner;
    private EditText cec_name,cec_department,cec_positionInSchool,cec_email,cec_positionOfCEC,cec_description;
    private ImageView cec_img;
    private FloatingActionButton btnsave;
    private Insert_CEC_ViewModel cecViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cecViewModel = new ViewModelProvider(this).get(Insert_CEC_ViewModel.class);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_cec,container,false);
        spinner = (Spinner)view.findViewById(R.id.spinner_cec_position);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cec_position, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        cec_name = view.findViewById(R.id.edt_cec_name);
        cec_department = view.findViewById(R.id.edt_department);
        cec_img = view.findViewById(R.id.cec_insert_image);
        cec_description = view.findViewById(R.id.edt_description);
        cec_positionInSchool = view.findViewById(R.id.edt_position);
        cec_email = view.findViewById(R.id.edt_cec_email);
        btnsave = view.findViewById(R.id.btn_save_cec);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = cec_name.getText().toString();
                String department = cec_department.getText().toString();
                String positionInSchool = cec_positionInSchool.getText().toString();
                String email = cec_email.getText().toString();
                String positionInCEC = spinner.getSelectedItem().toString();
                String description = cec_description.getText().toString();
                String imgUrl = "default";
                CEC cec = new CEC(name,department,positionInSchool,email,positionInCEC,description,imgUrl);
                cecViewModel.insertCECData(cec);
            }
        });
        return view;
    }
}
