package com.far.farmevents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.far.farmevents.Globals.Functions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    MainActivity mainActivity;
    EditText etUser, etPassword;
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(MainActivity mainActivity) {
        LoginFragment fragment = new LoginFragment();
        fragment.mainActivity = mainActivity;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.tvVersion)).setText(Functions.getAppVersion());
        etUser = view.findViewById(R.id.etUSerName);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        if(!validateInputs()){
            return;
        }
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();

        loginSuccess();
    }

    private void loginSuccess(){
        mainActivity.setFarmList();
    }

    private boolean validateInputs(){
        if(etUser.getText().toString().isEmpty()){
            etUser.setError(mainActivity.getString(R.string.field_cannot_be_empty));
            etUser.requestFocus();
            return false;
        }else if(etPassword.getText().toString().isEmpty()){
            etPassword.setError(mainActivity.getString(R.string.field_cannot_be_empty));
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
}