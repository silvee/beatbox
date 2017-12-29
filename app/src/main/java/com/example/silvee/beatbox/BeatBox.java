package com.example.silvee.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.nfc.Tag;
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
    public static final int MAX_SOUNDS = 5;

    private AssetManager assetManager;
    private List<Sound> soundList = new ArrayList<>();
    private SoundPool soundPool;

    public BeatBox(Context context) {
        assetManager = context.getAssets();
        //deprecated constructor used for compatibility instead of SoundPool.Builder
        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(sound.getAssetPath());
        int soundId = soundPool.load(assetFileDescriptor, 1);
        sound.setId(soundId);
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
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                soundList.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    public void play(Sound sound) {
        Integer id = sound.getId();
        if (id == null) return;
        soundPool.play(sound.getId(), 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        soundPool.release();
    }

    public List<Sound> getSoundList() {
        return soundList;
    }
}
