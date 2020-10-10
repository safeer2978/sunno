package com.sunno.Main.ui.Fragment.Artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sunno.Config.CircleTransform;
import com.sunno.Config.Constants;
import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.ui.OnCategoryClickListener;
import com.sunno.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    List<ArtistModel> artistList;

    OnCategoryClickListener onCategoryClickListener;

    public ArtistAdapter(List<ArtistModel> artistList, OnCategoryClickListener onCategoryClickListener) {
        this.artistList = artistList;
        this.onCategoryClickListener = onCategoryClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fat_albums,parent,false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ArtistModel artist=artistList.get(position);

        holder.textView.setText(artist.getName());

        String url = artist.getImage_url();
        if(url.length()!=0){
            Picasso.get()
                    .load(url)
                    .transform(new CircleTransform())
                    .resize(300,300)
                    .into(holder.imageView);
        }
        else{
            Picasso.get()
                    .load("https://i.imgur.com/nszu54A.jpg")
                    .resize(300,300)
                    .transform(new CircleTransform())
                    .into(holder.imageView);

        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Object> objects =  new ArrayList<Object>();
                objects.add(artist.getId());
                onCategoryClickListener.onCategoryClick(Constants.ARTIST_FRAGMENT_ID, objects);
            }
        });

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_fat_al_imageView);
            textView = itemView.findViewById(R.id.item_fat_al_textView);
            constraintLayout = itemView.findViewById(R.id.item_fat_al_cl);
        }
    }
}