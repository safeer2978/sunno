package com.sunno.Main.ui.Fragment.Album;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sunno.Main.AddTrackInterface;
import com.sunno.Main.Model.Entities.tracks;
import com.sunno.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    List<tracks> tracksList;
    AddTrackInterface addTrackInterface;
    public Adapter(List<tracks> tracksList, AddTrackInterface addTrackInterface) {
        this.tracksList = tracksList;
        this.addTrackInterface = addTrackInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_fragment_rv_item,parent,false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(tracksList.get(position).getTitle());
        final int pos = position;
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrackInterface.addTrackToQueue(tracksList.get(pos).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracksList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.far_rv_textview);
            constraintLayout = itemView.findViewById(R.id.far_cl);
        }
    }
}