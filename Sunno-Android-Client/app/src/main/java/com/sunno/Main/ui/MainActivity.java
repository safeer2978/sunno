package com.sunno.Main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.sunno.Config.Constants;
import com.sunno.Main.AddTrackInterface;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.Model.Object.InnerListObject;
import com.sunno.Main.NewAccessTokenWorker;
import com.sunno.Main.ui.Fragment.Home.Adapter.ChildRecyclerAdapter;
import com.sunno.AuthModule.db.LoginDao;
import com.sunno.AuthModule.db.LoginDatabase;
import com.sunno.Main.ui.Fragment.Album.AlbumResponseFragment;
import com.sunno.Main.ui.Fragment.Artist.ArtistResponseFragment;
import com.sunno.Main.ui.Fragment.Home.HomeFragment;
import com.sunno.Main.ui.Fragment.Genre.GenreResponseFragment;
import com.sunno.Main.ui.Fragment.Profile.ProfileFragment;
import com.sunno.Main.network.NetworkEndpoint;
import com.sunno.Main.network.NetworkService;
import com.sunno.Main.Model.Request.PlayRequest;
import com.sunno.R;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnCategoryClickListener, AddTrackInterface {

    private MediaPlayer mediaPlayer;
    private SlideUp slideUp;
    private View dim;
    private View fragmentContainter;
    private View slideView;
    private ConstraintLayout crd;

    Queue<String> urlQueue;

    private BottomNavigationView bottomNavView;

    //CardView Widgets
    private ImageButton playBtn_mp;
    private ImageButton playBtn_crd;
    private ImageButton nextBtn_mp;
    private ImageButton prevBtn_mp;
    private SeekBar seekBar_mp;
    private TextView songNameTextView_crd;
    private TextView artistNameTextView_crd;
    private Handler mHandler;

    NetworkEndpoint api;
    LoginDao dao;

    //https://www.mboxdrive.com/Molecule%20-%20MAFA%20(ETIYINMELO.COM.NG).mp3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = LoginDatabase.getDatabase(getApplication()).wordDao();
        urlQueue = new ArrayDeque<>();

        WorkRequest request =
                new PeriodicWorkRequest.Builder(NewAccessTokenWorker.class, Constants.TOKEN_REFRESH_TIME, TimeUnit.SECONDS)
                        .addTag("ACCESS TOKEN REFRESH")
                        .build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(request);


        bottomNavView=findViewById(R.id.navBar);
        bottomNavView.setOnNavigationItemSelectedListener(navListener);
        slideView=findViewById(R.id.slideView);
        dim=findViewById(R.id.dim);
        playBtn_mp=findViewById(R.id.playbtn_mp);
        crd=findViewById(R.id.cardView);
        playBtn_crd=findViewById(R.id.playButton_crd);
        seekBar_mp=findViewById(R.id.seekBar_mp);
        fragmentContainter=findViewById(R.id.fragment_container);



        mHandler=new Handler();

///IMPLEMENTING MUSIC PLAYER

        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.mboxdrive.com/Molecule%20-%20MAFA%20(ETIYINMELO.COM.NG).mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //int duration=mp.getDuration();
                //Toast.makeText(MainActivity.this, String.valueOf((duration/1000)/60), Toast.LENGTH_SHORT).show();
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {
                //String Duration= String.valueOf(mp.getDuration());
                //endText.setText(Duration);
                //seekBar_mp.setMax(mp.getDuration());
                playBtn_crd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mp.isPlaying()){
                            mp.pause();
                            playBtn_crd.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        }
                        else{
                            mp.start();
                            playBtn_crd.setImageResource(R.drawable.ic_baseline_pause_24);
                        }
                    }
                });
                playBtn_mp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Toast.makeText(MainActivity.this, "Music Starts", Toast.LENGTH_SHORT).show();
                        if(mp.isPlaying()){
                            mp.pause();
                            playBtn_mp.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        }
                        else{
                            mp.start();
                            playBtn_mp.setImageResource(R.drawable.ic_baseline_pause_24);
                        }
                    }
                });
            }
        });

        mediaPlayer.prepareAsync();

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null)
                {
                    int mduration=mediaPlayer.getCurrentPosition();
                    seekBar_mp.setProgress(mduration);
                }
                mHandler.postDelayed(this,1000);
            }
        });



        seekBar_mp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                mediaPlayer.seekTo(progress);
            }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

///IMPLEMENTING SLIDING FUNCTIONALITY
//Todo:Fucking crd view dosen't View.gone when I pull up
        slideUp=new SlideUpBuilder(slideView)
                .withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float v) {
                        dim.setAlpha(1 - v / 100);
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        crd.setVisibility(View.VISIBLE);
                    }
                })
                .build();


        crd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          crd.setVisibility(View.INVISIBLE);
          slideUp.show();
      }
  });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;
                     switch (item.getItemId() ){
                         case R.id.nav_home:
                             selectedFragment=new HomeFragment(MainActivity.this);
                             break;
                         case R.id.nav_profile:
                             selectedFragment=new ProfileFragment();
                             break;
                     }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                     return true;
                }
            };


    @Override
    public void onCategoryClick(int type, List<Object> params) {
        Fragment fragment;
        switch (type){
            case 1:
                fragment =new GenreResponseFragment(this, (Genre) params.get(0));
                break;
            case 2:
                fragment =new ArtistResponseFragment(this, (Integer) params.get(0));
                break;
            case 3:
                fragment= new AlbumResponseFragment((Integer) params.get(0), this);
                break;
            default:
                fragment=new GenreResponseFragment(this, (Genre) params.get(0));
        }

        FragmentManager fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
    super.onDestroy();
    if (mediaPlayer != null) {
        mediaPlayer.pause();
        mediaPlayer.release();
    }

    }

    boolean flag = true;
    @Override
    public void onBackPressed() {
        if(flag) {
            Toast.makeText(this, "Are you sure you at to quit? Press Back Again", Toast.LENGTH_SHORT).show();
            flag = false;
        }else
            super.onBackPressed();
    }


    @Override
    public void addTrackToQueue(String trackId){
        PlayRequest request = new PlayRequest(trackId);
        api = NetworkService.getPlayServiceClient(this,dao.getUser().get(0).getAccessToken()).create(NetworkEndpoint.class);
        api.getUrl(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
                //TODO implement Queue for here
                urlQueue.add(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


}