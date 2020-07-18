package com.sunno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.slideup.SlideUp;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    private SlideUp slideUp;
    private View dim;
    private View slideView;
    private CardView crd;

    //CardView Widgets
    private ImageButton playBtn_crd;
    private ImageButton forwardBtn_crd;
    private ImageButton backButton_crd;
    private SeekBar seekBar_crd;
    private TextView songNameTextView_crd;
    private TextView artistNameTextView_crd;


    //https://www.mboxdrive.com/Molecule%20-%20MAFA%20(ETIYINMELO.COM.NG).mp3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideView=findViewById(R.id.slideView);
        dim=findViewById(R.id.dim);
        crd=findViewById(R.id.cardView);
        playBtn_crd=findViewById(R.id.playButton_crd);
        seekBar_crd=findViewById(R.id.seekBar_crd);
        forwardBtn_crd=findViewById(R.id.seekForwardButton_crd);
        forwardBtn_crd.setOnClickListener(this);
        backButton_crd=findViewById(R.id.seekBackButton_crd);
        backButton_crd.setOnClickListener(this);



        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.mboxdrive.com/Molecule%20-%20MAFA%20(ETIYINMELO.COM.NG).mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration=mp.getDuration();
                Toast.makeText(MainActivity.this, String.valueOf((duration/1000)/60), Toast.LENGTH_SHORT).show();
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {
                //String Duration= String.valueOf(mp.getDuration());
                //endText.setText(Duration);
                seekBar_crd.setMax(mp.getDuration());
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
            }
        });

        mediaPlayer.prepareAsync();

        seekBar_crd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        //TODO: Maddie Please make changes here

        slideUp=new SlideUp(slideView);
        slideUp.hideImmediately();

        crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.animateIn();
                crd.setVisibility(View.GONE);
            }
        });
        slideUp.setSlideListener(new SlideUp.SlideListener() {
            @Override
            public void onSlideDown(float v) {
                dim.setAlpha(1 - v / 100);
            }

            @Override
            public void onVisibilityChanged(int i) {

                if(i==View.GONE)
                {
                    crd.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.seekForwardButton_crd:
                break;
            case R.id.seekBackButton_crd:
                break;
        }
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