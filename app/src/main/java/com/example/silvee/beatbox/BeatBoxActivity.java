package com.example.silvee.beatbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeatBoxActivity extends SingleFragmentActivity {

    Fragment bbfragment;

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();

        if (bbfragment == null) {
            bbfragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, bbfragment).commit();
        }
    }
}
