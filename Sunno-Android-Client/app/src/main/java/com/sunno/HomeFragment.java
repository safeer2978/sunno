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
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<String> itemsArrayList;
    ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener;

    HomeFragment(ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener){
        this.onCategoryClickListener = onCategoryClickListener;
    }

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
        List<OuterListObject> outerListObjectList = new ArrayList<>();

        OuterListObject object1 = new OuterListObject("Alternative");
        List<InnerListObject> innerList = new ArrayList<>();
        String [] names={"madhul","Safeer","Daksh","Ritvik","Daryl","Shreyam","Pinsu"};
        for(String name: names){
            innerList.add(new InnerListObject(name, 1));
        }
        object1.setInnerListObjectList(innerList);
        outerListObjectList.add(object1);

        innerList = new ArrayList<>();
        OuterListObject object2 = new OuterListObject("EDM");
        String [] names2={"mal","Sar","Dsh","Riik","Dyl","Sham","Psu"};
        for(String name: names2){
            innerList.add(new InnerListObject(name, 2));
        }
        object2.setInnerListObjectList(innerList);
        outerListObjectList.add(object2);


        adapter= new ParentRecyclerAdapter(outerListObjectList,context, onCategoryClickListener);

        String [] items= { "abc","def","ghi","jkl","mno","pqr"};
        for(int i=0;i<items.length;i++){
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
