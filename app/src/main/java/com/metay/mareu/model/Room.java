package com.metay.mareu.model;


import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.example.projet4.R;

public enum Room {
    MARIO("Mario",R.drawable.ic_mario),
    LUIGI("Luigi", R.drawable.ic_luigi),
    PEACH("Peach", R.drawable.ic_peach),
    TOAD("Toad", R.drawable.ic_toad),
    YOSHI("Yoshi", R.drawable.ic_yoshi),
    DAISY("Daisy", R.drawable.ic_daisy),
    BOO("Boo", R.drawable.ic_boo),
    DONKEY_KONG("Donkey Kong", R.drawable.ic_donkey),
    BOWSER("Bowser", R.drawable.ic_bowser),
    WALUIGI("Waluigi", R.drawable.ic_waluigi);

    private String room;
    private int image;

    Room(String room, int drawableId) {
        this.room = room;
        this.image = drawableId;
    }

    @NonNull
    @Override
    public String toString() {
        return room;
    }

    public int getImage(){
        return image;
    }

    public String getRoom() { return room; }


}
