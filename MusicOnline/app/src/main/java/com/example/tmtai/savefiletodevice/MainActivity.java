package com.example.tmtai.savefiletodevice;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "LOG";
    private Button btnAddFile;

    boolean prepared = false;
    boolean started = true;
    MediaPlayer mediaPlayer;
    String steam = "http://stream.radioreklama.bg:80/radio1rock128";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddFile = findViewById(R.id.btnAddFile);
        btnAddFile.setEnabled(true);
        btnAddFile.setText("LOADING");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerMusic().execute(steam);

        btnAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started){
                    started = false;
                    mediaPlayer.pause();
                    btnAddFile.setText("PLAY");
                }else{
                    started = true;
                    mediaPlayer.start();
                    btnAddFile.setText("PAUSE");
                }
            }
        });
    }

    class PlayerMusic extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            btnAddFile.setEnabled(true);
            btnAddFile.setText("PLAY");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (started){
            mediaPlayer.release();
        }
    }
}
