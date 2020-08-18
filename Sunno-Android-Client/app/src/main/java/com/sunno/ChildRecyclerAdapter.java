package com.sunno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.MyViewHolder> {

    private List<InnerListObject> innerList;
    private OnCategoryClickListener mOnCategoryClickListener;
    private Context context;

    public ChildRecyclerAdapter(List<InnerListObject> innerList, Context context, OnCategoryClickListener onCategoryClickListener) {
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
        final InnerListObject object = innerList.get(position);
        holder.name.setText(object.getTitle());
        holder.child_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnCategoryClickListener.onCategoryClick(object.getType());
            }
        });

    }

    @Override
    public int getItemCount() {
        return innerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        LinearLayout child_rv;
        OnCategoryClickListener onCategoryClickListener;
        public MyViewHolder(@NonNull View itemView,OnCategoryClickListener onCategoryClickListener) {
            super(itemView);
            name=itemView.findViewById(R.id.nameText_aka_dayText);
            this.onCategoryClickListener=onCategoryClickListener;
            this.child_rv = itemView.findViewById(R.id.child_rv_item);
        }
    }

    public interface OnCategoryClickListener{
        void onCategoryClick(int type);
    }
}
