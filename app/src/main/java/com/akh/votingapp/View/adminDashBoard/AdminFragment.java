package com.akh.votingapp.View.adminDashBoard;

import android.os.Bundle;
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
import androidx.navigation.Navigation;

import com.akh.votingapp.R;

public class AdminFragment extends Fragment {
    private AdminViewModel adminViewModel;
    private CardView cec_insert_card, info_insert_card;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        View view = inflater.inflate(R.layout.fragment_admin,container,false);
        cec_insert_card = view.findViewById(R.id.cec_card);
        info_insert_card = view.findViewById(R.id.info_card);
        final TextView textView = view.findViewById(R.id.txt_admin);
        adminViewModel.getMText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        cec_insert_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_nav_admin_to_insert_CEC_Fragment);
            }
        });
        return view;

    }
}
