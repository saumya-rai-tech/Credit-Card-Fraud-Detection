package com.example.fds.activity;

import static com.example.fds.utils.VolleyApi.SONG_URL;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerFullScreenView;
import com.example.fds.R;

public class FullScreenAudioPlayer  extends AppCompatActivity {

    String path,singer,audioName;
    ArgPlayerFullScreenView argMusicPlayer;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.music_play);

        path= getIntent().getStringExtra("song");
        singer= getIntent().getStringExtra("singer");
        audioName= getIntent().getStringExtra("audioName");
        String url = SONG_URL+""+path;
        ArgAudio audio = ArgAudio.createFromURL(singer, audioName, url);

        ArgPlayerFullScreenView argMusicPlayer = (ArgPlayerFullScreenView) findViewById(R.id.argmusicplayer);
        argMusicPlayer.play(audio);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (argMusicPlayer!=null){
            argMusicPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (argMusicPlayer!=null){
            argMusicPlayer.stop();
        }
    }
}
