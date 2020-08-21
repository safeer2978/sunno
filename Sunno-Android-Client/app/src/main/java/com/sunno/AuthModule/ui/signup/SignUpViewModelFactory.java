package com.sunno.AuthModule.ui.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sunno.AuthModule.data.LoginDataSource;
import com.sunno.AuthModule.data.LoginRepository;
import com.sunno.AuthModule.ui.login.LoginViewModel;

public class SignUpViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    SignUpViewModelFactory(Application application){
        mApplication = application;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            return (T) new SignUpViewModel(LoginRepository.getInstance(new LoginDataSource(mApplication)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}