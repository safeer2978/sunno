package com.sunno;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunno.Model.ArtistModel;
import com.sunno.Model.Genre;
import com.sunno.Model.MetaDataModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
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

    HomeFragment(ChildRecyclerAdapter.OnCategoryClickListener onCategoryClickListener){
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

        itemsArrayList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLConstants.VIEW_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService=retrofit.create(ApiService.class);

        Call<MetaDataModel> call=apiService.getMetaData();
        call.enqueue(new Callback<MetaDataModel>() {
            @Override
            public void onResponse(Call<MetaDataModel> call, Response<MetaDataModel> response) {
                if(!response.isSuccessful()){
                    Log.d("HomeFragment2", "onFailure: "+response.code());
                    return;
                }
                MetaDataModel metaDataModelFromApi=response.body();

                genreListFromMApi=metaDataModelFromApi.getGenre();
                artistListFromMApi=metaDataModelFromApi.getArtists();
            }

            @Override
            public void onFailure(Call<MetaDataModel> call, Throwable t) {
                Log.d("HomeFragment1", "onFailure: "+t.getMessage());
            }
        });

        List<OuterListObject> outerListObjectList = new ArrayList<>();

        OuterListObject object1 = new OuterListObject("Genre");
        List<Genre> innerListGenre=genreListFromMApi;

        List<InnerListObject> innerList = new ArrayList<>();
        String [] names={"madhul","Safeer","Daksh","Ritvik","Daryl","Shreyam","Pinsu"};
        for(String name: names){
            innerList.add(new InnerListObject(name, 1));
        }
        object1.setInnerListObjectList(innerList);
        outerListObjectList.add(object1);

        List<ArtistModel> innerListArtists=artistListFromMApi;
        innerList = new ArrayList<>();
        OuterListObject object2 = new OuterListObject("Artist");
        String [] names2={"mal","Sar","Dsh","Riik","Dyl","Sham","Psu"};
        for(String name: names2){
            innerList.add(new InnerListObject(name, 2));
        }
        object2.setInnerListObjectList(innerList);
        outerListObjectList.add(object2);


        adapter= new ParentRecyclerAdapter(outerListObjectList,context, onCategoryClickListener);

        String [] items= { "Genre","Artist","PlayList"};
        for(int i=0;i<items.length;i++){
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
