package com.example.silvee.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvee on 20.12.2017.
 */

public class BeatBox {
    public static final String TAG = "BeatBox";
    public static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager assetManager;
    private List<Sound> soundList = new ArrayList<>();

    public BeatBox(Context context) {
        assetManager = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;

        try {
            soundNames = assetManager.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.i(TAG, "Could not find list of assets", ioe);
            return;
        }

        for (String filename: soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            soundList.add(sound);
        }
    }

    public List<Sound> getSoundList() {
        return soundList;
    }
}
