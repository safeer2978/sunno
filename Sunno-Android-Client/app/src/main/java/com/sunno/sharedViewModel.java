package com.sunno;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class sharedViewModel extends ViewModel {
    private MutableLiveData<String> text = new MutableLiveData<>();

    public void setText(String input){
        text.setValue(input);
    }
    public LiveData<String> getText(){

        return text;
    }
}
