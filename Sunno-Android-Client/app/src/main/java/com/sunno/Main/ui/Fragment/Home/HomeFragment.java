package com.sunno.Main.ui.Fragment.Home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunno.Main.ui.Fragment.Home.Adapter.ChildRecyclerAdapter;
import com.sunno.Main.Model.Object.InnerListObject;
import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.Model.Object.OuterListObject;
import com.sunno.Main.ui.Fragment.Home.Adapter.ParentRecyclerAdapter;
import com.sunno.R;
import com.sunno.Config.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    HomeFragmentViewModel viewModel;

    @Nullable
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<String> itemsArrayList;
    private List<Genre> genreListFromMApi;
    private List<ArtistModel> artistListFromMApi;
    ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener;

    public HomeFragment(ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener){
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
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        itemsArrayList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        List<OuterListObject> outerListObjectList = new ArrayList<>();

        OuterListObject genreObject = new OuterListObject("Genre");
        List<Genre> innerListGenre=viewModel.getGenreList();

        List<InnerListObject> innerListForGenre = new ArrayList<>();

        for(Genre genre: innerListGenre){
            innerListForGenre.add(new InnerListObject(genre.getName(),genre.getImg_url(), Constants.GENRE_FRAGMENT_ID));
        }
        genreObject.setInnerListObjectList(innerListForGenre);
        outerListObjectList.add(genreObject);

        // Adding Album

        OuterListObject object2 = new OuterListObject("Album");
        List<AlbumModel> innerListAlbum=viewModel.getAlbumList();

        List<InnerListObject> innerList2 = new ArrayList<>();

        for(AlbumModel albumModel: innerListAlbum){
            InnerListObject innerListObject = new InnerListObject(albumModel.getName(),albumModel.getImage_url(), Constants.ALBUM_FRAGMENT_ID);
            innerListObject.setParams(albumModel.getId());
            innerListObject.setAlbumId(albumModel.getId());
            Log.d("HOME", "albumId set:"+albumModel.getId());
            innerList2.add(innerListObject);

        }
        object2.setInnerListObjectList(innerList2);
        outerListObjectList.add(object2);


        //adding artist

        OuterListObject object3 = new OuterListObject("Artists");
        List<ArtistModel> innerListArtist=viewModel.getArtistList();

        List<InnerListObject> innerList3 = new ArrayList<>();

        for(ArtistModel artistModel:innerListArtist){
            innerList3.add(new InnerListObject(artistModel.getName(),artistModel.getImg_url(), Constants.ARTIST_FRAGMENT_ID));
        }
        object3.setInnerListObjectList(innerList3);
        outerListObjectList.add(object3);

//        OuterListObject object2=new OuterListObject("Artist");
//        List<ArtistModel> innerListArtist=viewModel.getArtistList();
//
//        List<InnerListObject> innerList2 = new ArrayList<>();
//
//        for(ArtistModel artist:innerListArtist){
//            innerList2.add(new InnerListObject(artist.getName(),artist.getImg_url(),URLConstants.ARTIST_FRAGMENT_ID));
//        }
//
//        object2.setInnerListObjectList(innerList2);
//        outerListObjectList.add(object2);

        /*List<ArtistModel> innerListArtists=artistListFromMApi;
        innerList = new ArrayList<>();
        OuterListObject object2 = new OuterListObject("Artist");

        for(ArtistModel artist: artistListFromMApi ){
            innerList.add(new InnerListObject(artist.getName(),artist.getImg_url(), URLConstants.ARTIST_FRAGMENT_ID));
        }
        object2.setInnerListObjectList(innerList);
        outerListObjectList.add(object2);
*/

        adapter= new ParentRecyclerAdapter(outerListObjectList,context, onCategoryClickListener);

        String [] items= { "Genre","Artist","PlayList"};
        for(int i=0;i<items.length;i++){
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
