package com.sunno.Main.ui.Fragment.Genre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.ui.Fragment.Artist.AlbumsAdapter;
import com.sunno.Main.ui.MainActivity;
import com.sunno.Main.ui.MainActivityViewModel;
import com.sunno.Main.ui.OnCategoryClickListener;
import com.sunno.R;

import java.util.List;
import java.util.Objects;

public class GenreResponseFragment extends Fragment {


    List<AlbumModel> albumList;
    RecyclerView recyclerView;
    MainActivityViewModel viewModel;
    OnCategoryClickListener onCategoryClickListener;
    Genre genre;

    public GenreResponseFragment(OnCategoryClickListener onCategoryClickListener, Genre genre) {
        this.onCategoryClickListener = onCategoryClickListener;
        this.genre = genre;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_music,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.genre_fragment_recyclerView);
        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainActivityViewModel.class);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(viewModel.getAlbums(genre),onCategoryClickListener);

        recyclerView.setAdapter(albumsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));



    }
}
