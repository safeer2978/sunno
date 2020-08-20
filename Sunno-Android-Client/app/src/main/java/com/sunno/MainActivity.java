package com.sunno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
import com.sunno.Adapter.ChildRecyclerAdapter;
import com.sunno.Fragment.ResponseFragment.AlbumResponseFragment;
import com.sunno.Fragment.ResponseFragment.ArtistResponseFragment;
import com.sunno.Fragment.FavFragment;
import com.sunno.Fragment.HomeFragment;
import com.sunno.Fragment.ResponseFragment.GenreResponseFragment;
import com.sunno.Fragment.ProfileFragment;
import com.sunno.Fragment.SearchFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ChildRecyclerAdapter.OnCategoryClickListener  {

    private MediaPlayer mediaPlayer;
    private SlideUp slideUp;
    private View dim;
    private View fragmentContainter;
    private View slideView;
    private CardView crd;

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


    //https://www.mboxdrive.com/Molecule%20-%20MAFA%20(ETIYINMELO.COM.NG).mp3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;
                     switch (item.getItemId() ){
                         case R.id.nav_home:
                             selectedFragment=new HomeFragment(MainActivity.this);
                             break;
                         case R.id.nav_favorites:
                             selectedFragment=new FavFragment();
                             break;
                         case R.id.nav_search:
                             selectedFragment=new SearchFragment();
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
    public void onCategoryClick(int type) {
        Fragment fragment;
        switch (type){
            case 1:
                fragment =new GenreResponseFragment();
                break;
            case 2:
                fragment =new ArtistResponseFragment();
                break;
            case 3:
                fragment= new AlbumResponseFragment();
                break;
            default:
                fragment=new GenreResponseFragment();
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

}