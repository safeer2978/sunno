package com.sunno.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunno.Adapter.ChildRecyclerAdapter;
import com.sunno.HomeFragmentViewModel;
import com.sunno.InnerListObject;
import com.sunno.Model.AlbumModel;
import com.sunno.Model.ArtistModel;
import com.sunno.Model.Genre;
import com.sunno.OuterListObject;
import com.sunno.Adapter.ParentRecyclerAdapter;
import com.sunno.R;
import com.sunno.URLConstants;

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

        /*Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLConstants.VIEW_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint apiService=retrofit.create(ApiEndpoint.class);

        Call<MetaDataModel> call=apiService.getMetaData();
        call.enqueue(new Callback<MetaDataModel>() {
            @Override
            public void onResponse(Call<MetaDataModel> call, Response<MetaDataModel> response) {
                if(!response.isSuccessful()){
                    Log.d("HomeFragment2", "onFailure: "+response.code());
                    return;
                }
                MetaDataModel metaDataModelFromApi=response.body();

                System.out.println(metaDataModelFromApi);
                genreListFromMApi=metaDataModelFromApi.getGenre();
                //artistListFromMApi=metaDataModelFromApi.getArtists();
            }

            @Override
            public void onFailure(Call<MetaDataModel> call, Throwable t) {
                Log.d("HomeFragment1", "onFailure: "+t.getMessage());
            }
        });*/

        List<OuterListObject> outerListObjectList = new ArrayList<>();

        OuterListObject object1 = new OuterListObject("Genre");
        List<Genre> innerListGenre=viewModel.getGenreList();

        List<InnerListObject> innerList1 = new ArrayList<>();

        for(Genre genre: innerListGenre){
            innerList1.add(new InnerListObject(genre.getName(),genre.getImg_url(), URLConstants.GENRE_FRAGMENT_ID));
        }
        object1.setInnerListObjectList(innerList1);
        outerListObjectList.add(object1);

        // Adding Album

        OuterListObject object2 = new OuterListObject("Album");
        List<AlbumModel> innerListAlbum=viewModel.getAlbumList();

        List<InnerListObject> innerList2 = new ArrayList<>();

        for(AlbumModel albumModel: innerListAlbum){
            innerList2.add(new InnerListObject(albumModel.getName(),albumModel.getImg_url(), URLConstants.ALBUM_FRAGMENT_ID));
        }
        object2.setInnerListObjectList(innerList2);
        outerListObjectList.add(object2);


        //adding artist

        OuterListObject object3 = new OuterListObject("Artists");
        List<ArtistModel> innerListArtist=viewModel.getArtistList();

        List<InnerListObject> innerList3 = new ArrayList<>();

        for(ArtistModel artistModel:innerListArtist){
            innerList3.add(new InnerListObject(artistModel.getName(),artistModel.getImg_url(), URLConstants.ARTIST_FRAGMENT_ID));
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