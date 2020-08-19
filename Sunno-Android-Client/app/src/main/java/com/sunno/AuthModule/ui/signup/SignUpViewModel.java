package com.sunno.AuthModule.ui.signup;

import androidx.lifecycle.ViewModel;

import com.sunno.AuthModule.data.LoginRepository;
import com.sunno.AuthModule.data.Result;
import com.sunno.AuthModule.data.model.User;
import com.sunno.AuthModule.network.request.SignUpRequest;

import com.sunno.R;

public class SignUpViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    /*
    new SignUpRequest(email,
                    password,
                    phone_no,
                    first_name,
                    last_name,
                    age,
                    gender);
                    */
    private LoginRepository loginRepository;

    SignUpViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    SignUpViewModel(){}

    Boolean signUp(SignUpRequest request){
        return loginRepository.signUp(request);
    }
}