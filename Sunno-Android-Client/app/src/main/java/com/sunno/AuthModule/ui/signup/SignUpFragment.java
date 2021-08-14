package com.sunno.AuthModule.ui.signup;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sunno.AuthModule.network.request.SignUpRequest;
import com.sunno.AuthModule.ui.login.LoginViewModel;
import com.sunno.AuthModule.ui.login.LoginViewModelFactory;
import com.sunno.R;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText usernameEditText = view.findViewById(R.id.sign_email);
/*        final EditText passwordEditText = view.findViewById(R.id.sign_password);
        final EditText phoneEditText = view.findViewById(R.id.sign_phone);
        final EditText firstNameEditText = view.findViewById(R.id.sign_first);
        final EditText lastNameEditText = view.findViewById(R.id.sign_last);
        final EditText ageEditText = view.findViewById(R.id.sign_age);
        final EditText genderEditText = view.findViewById(R.id.sign_gender);*/
        Button signUpButton  = view.findViewById(R.id.sign_UP_btn);

        mViewModel = ViewModelProviders.of(this, new SignUpViewModelFactory(this.getActivity().getApplication())).get(SignUpViewModel.class);






        /*signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);
                final SignUpRequest request = new SignUpRequest(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        Integer.valueOf(ageEditText.getText().toString()),
                        genderEditText.getText().toString());
                boolean status = mViewModel.signUp(request);
                Toast.makeText(getContext(), "status: "+status,Toast.LENGTH_LONG).show();
            }
        });*/


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
    }

}