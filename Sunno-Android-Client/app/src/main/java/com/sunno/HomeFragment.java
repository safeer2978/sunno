package com.sunno;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    @Nullable
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<String> itemsArrayList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=getActivity();
        view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=view.findViewById(R.id.parent_RV);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemsArrayList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter= new ParentRecyclerAdapter(itemsArrayList,context);

        String [] items= { "abc","def","ghi","jkl","mno","pqr"};
        for(int i=0;i<items.length;i++){
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
