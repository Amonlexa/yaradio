package com.amonlexasoftware.yaradio.ui.activity;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amonlexasoftware.yaradio.databinding.ActivitySteamBinding;
import com.amonlexasoftware.yaradio.support.service.MyMediaPlayer;
import com.amonlexasoftware.yaradio.support.service.Notification;
import com.amonlexasoftware.yaradio.support.Assistant;
import com.amonlexasoftware.yaradio.models.RadioModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class StreamRadioActivity extends AppCompatActivity implements Serializable {


    ArrayList<RadioModel> radioList;
    RadioModel currentRadio;
    public static MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    ActivitySteamBinding binding;
    boolean prepared = false;
    boolean started = false;
    AsyncTask<String, Void, Boolean> playerTask = null;
    NotificationManager notificationManager;

    @Override
    public void onBackPressed() {
        if(playerTask != null && playerTask.getStatus() == AsyncTask.Status.FINISHED) {
            if (mediaPlayer.isPlaying()) {
                onPause();
            }
        } else if (playerTask != null && playerTask.getStatus() != AsyncTask.Status.FINISHED) {
            // It mean your task is running, should stop your mediaPlayer inside your task
            playerTask.cancel(true);
        }
        startActivity(new Intent(StreamRadioActivity.this,MainActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySteamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationSettings();
        }



        init();

    }

    void init () {
        binding.pausePlay.setEnabled(false);
        binding.pausePlay.setText("Загрузка, пожалуйста подождите ...");
        radioList = (ArrayList<RadioModel>) getIntent().getSerializableExtra("data");
        setResourcesWithMusic();

        funButton();
    }

    void funButton() {
        binding.buttonBackStream.setOnClickListener(view -> onBackPressed());
        binding.pausePlay.setOnClickListener(view -> enableRadio());
    }

    private void notificationSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(Notification.channelID,
                    "Amonlexa", NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager !=null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void enableRadio() {
        if (started){
            started=false;
            mediaPlayer.pause();
            binding.pausePlay.setText("Включить");
        }else {
            started = true;
            mediaPlayer.start();
            binding.pausePlay.setText("Пауза");
        }
    }


    void setResourcesWithMusic(){
        currentRadio = radioList.get(MyMediaPlayer.currentRadio);
        binding.txtNameRadio.setText(currentRadio.getName());
        Assistant.loadUrlGlide(this,currentRadio.getLogo(),20,binding.imgRadio);
        binding.pausePlay.setOnClickListener(v-> pausePlay());
        startAudio();
    }

    private void startAudio() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        playerTask = new PlayTask().execute(currentRadio.getUrl());
        Notification.createNotification(StreamRadioActivity.this,radioList.get(MyMediaPlayer.currentRadio),currentRadio.getLogo(),MyMediaPlayer.currentRadio);

        Log.e("key:",currentRadio.getUrl().toString());

       // HttpsURLConnection httpsURLConnection = (HttpsURLConnection) currentRadio.getUrl().openConnection();

    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        else
            mediaPlayer.start();
    }


    private class PlayTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            }catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            binding.pausePlay.setEnabled(true);
            binding.pausePlay.setText("Включить");

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        if (started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prepared){
            mediaPlayer.start();
        }
    }

}