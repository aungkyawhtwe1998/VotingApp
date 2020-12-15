package com.akh.votingapp.View.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.akh.votingapp.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private EditText edt_email,edt_password;
    private Button btn_login;
    private LoginViewModel loginViewModel;
    private TextView txt_goToSignUp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null && firebaseUser.isEmailVerified()){
                    // TODO: 12/15/20 write navigation process
                    Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_mainActivity);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        edt_email = view.findViewById(R.id.edt_email_login);
        edt_password = view.findViewById(R.id.edt_pass_login);
        btn_login = view.findViewById(R.id.btn_login);
        txt_goToSignUp = view.findViewById(R.id.txt_goto_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                if(email.length()>0 && password.length()>0){
                    loginViewModel.login(email,password);
                }
            }
        });

        txt_goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 12/15/20 write nav process
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
        return view;
    }
}
