package com.example.silvee.beatbox;

import android.databinding.Bindable;

/**
 * Created by silvee on 20.12.2017.
 */

public class Sound {
    private String assetPath;
    private String soundName;


    // Constructor
    public Sound(String assetPath) {
        this.assetPath = assetPath;
        String[] components = this.assetPath.split("/");
        String filename = components[components.length - 1];
        soundName = filename.replace(".wav", "");

    }

    public String getAssetPath() {
        return assetPath;
    }

    public String getSoundName() {
        return soundName;
    }

}
