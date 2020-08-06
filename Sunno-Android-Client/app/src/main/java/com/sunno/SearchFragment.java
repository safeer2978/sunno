package com.sunno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sunno.R;

import java.util.Objects;

public class SearchFragment extends Fragment {
    private EditText editText_prof;

    private sharedViewModel pageViewModel;
    public SearchFragment(){

    }
    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel= new ViewModelProvider(requireActivity()).get(sharedViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText_prof=view.findViewById(R.id.search_ET);

        pageViewModel.getText().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText_prof.setText(s);
            }
        });
    }
}
