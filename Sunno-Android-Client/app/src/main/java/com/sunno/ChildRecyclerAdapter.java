package com.sunno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.MyViewHolder> {

    private ArrayList<String> innerList;
    private OnCategoryClickListener mOnCategoryClickListener;
    private Context context;

    public ChildRecyclerAdapter(ArrayList<String> innerList, Context context,OnCategoryClickListener onCategoryClickListener) {
        this.innerList = innerList;
        this.context = context;
        this.mOnCategoryClickListener=onCategoryClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_row_rv,parent,false);
        return new MyViewHolder(view,mOnCategoryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(innerList.get(position));

    }

    @Override
    public int getItemCount() {
        return innerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        OnCategoryClickListener onCategoryClickListener;
        public MyViewHolder(@NonNull View itemView,OnCategoryClickListener onCategoryClickListener) {
            super(itemView);
            name=itemView.findViewById(R.id.nameText_aka_dayText);
            this.onCategoryClickListener=onCategoryClickListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        onCategoryClickListener.onCategoryClick();
        }
    }

    public interface OnCategoryClickListener{
        void onCategoryClick();
    }
}