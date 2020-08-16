package com.sunno;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder>{

    private ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener;
    Context context;
    private List<OuterListObject> outerListObjects;

    public ParentRecyclerAdapter(List<OuterListObject> parentArrayList, Context context, ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener) {
        this.outerListObjects = parentArrayList;
        this.context = context;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.parent_row_rv,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OuterListObject object = outerListObjects.get(position);
        holder.item_name.setText(object.getTitle());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.childRV.setLayoutManager(layoutManager);
        holder.childRV.setHasFixedSize(true);

        ChildRecyclerAdapter childRecyclerAdapter=new ChildRecyclerAdapter(outerListObjects.get(position).getInnerListObjectList(),context,onCategoryClickListener);
        holder.childRV.setAdapter(childRecyclerAdapter);
        childRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return outerListObjects.size();
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
