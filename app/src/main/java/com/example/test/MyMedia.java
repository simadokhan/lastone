package com.example.test;

import android.content.Context;
import android.media.MediaPlayer;

public class MyMedia {
    static MediaPlayer instance;

    public static MediaPlayer getInstance(Context c) {
        if(instance==null){
            instance = MediaPlayer.create(c,R.raw.music1);
        }
        return instance;
    }
}
