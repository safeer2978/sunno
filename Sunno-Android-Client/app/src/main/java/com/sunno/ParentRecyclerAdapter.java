package com.sunno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder> {

    private ArrayList<String> parentArrayList;
    Context context;
    private ArrayList<String> namesArrayList = new ArrayList<>();

    public ParentRecyclerAdapter(ArrayList<String> parentArrayList, Context context) {
        this.parentArrayList = parentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.parent_row_rv,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_name.setText(parentArrayList.get(position));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.childRV.setLayoutManager(layoutManager);
        holder.childRV.setHasFixedSize(true);
        namesArrayList.clear();
        String [] names={"madhul","Safeer","Daksh","Ritvik","Daryl","Shreyam","Pinsu"};
        for (int i=0;i<names.length;i++){
            namesArrayList.add(names[i]);
        }
        ChildRecyclerAdapter childRecyclerAdapter=new ChildRecyclerAdapter(namesArrayList);
        holder.childRV.setAdapter(childRecyclerAdapter);
        childRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView item_name;
        RecyclerView childRV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.text_title_rv_item);
            childRV=itemView.findViewById(R.id.childRV);
        }
    }
}
