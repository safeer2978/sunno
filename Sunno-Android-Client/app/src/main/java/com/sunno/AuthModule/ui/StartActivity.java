package com.sunno.AuthModule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunno.AuthModule.ui.login.LoginFragment;
import com.sunno.AuthModule.ui.signup.SignUpFragment;
import com.sunno.R;

public class StartActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        frameLayout = findViewById(R.id.start_frame);
        change = findViewById(R.id.change);

    }


    public void OnClick(View view){
        Fragment fragment;

        //TODO: Change this logic, im feeling lazy
        if(change.getText().toString().equals("sign up")){
            fragment = new SignUpFragment();
            change.setText("back");
        }else{
            fragment = new LoginFragment();
            change.setText("sign up");
        }

        FragmentManager fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.start_frame,fragment);
        transaction.commit();
    }

}