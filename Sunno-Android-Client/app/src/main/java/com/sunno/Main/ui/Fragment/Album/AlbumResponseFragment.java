package com.sunno.Main.ui.Fragment.Album;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sunno.Main.AddTrackInterface;
import com.sunno.R;

public class AlbumResponseFragment extends Fragment {

    ImageView imageView;
    RecyclerView recyclerView;
    ViewModel viewModel;
    int albumId;

AddTrackInterface addTrackInterface;

    public AlbumResponseFragment(int albumId, AddTrackInterface addTrackInterface) {
        this.albumId = albumId;
        this.addTrackInterface = addTrackInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_response,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.far_rv);
        imageView = view.findViewById(R.id.far_imageView);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setAddTrackInterface(addTrackInterface);
        viewModel.setAlbum(albumId);


        String url = viewModel.getUrl();

        if(url.length()!=0){
            Picasso.get()
                    .load(url)
                    .resize(300,300)
                    .into(imageView);
        }
        else{
            Picasso.get()
                    .load("https://i.imgur.com/nszu54A.jpg")
                    .resize(300,300)
                    .into(imageView);

        }

        Adapter adapter = new Adapter(viewModel.getTracks(), viewModel.getAddTrackInterface());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
