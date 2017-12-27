package com.example.silvee.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by silvee on 22.12.2017.
 */

public class SoundViewModel extends BaseObservable {
    private Sound sound;
    private BeatBox beatBox;

    public SoundViewModel(BeatBox beatBox) {
        this.beatBox = beatBox;
    }

    @Bindable
    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
        notifyChange();
    }

    public String getTitle() {
        return sound.getSoundName();
    }

}
