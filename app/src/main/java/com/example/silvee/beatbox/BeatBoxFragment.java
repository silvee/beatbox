package com.example.silvee.beatbox;

import android.app.LauncherActivity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.silvee.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.silvee.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

/**
 * Created by silvee on 19.12.2017.
 */

public class BeatBoxFragment extends Fragment {
    private BeatBox beatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(beatBox.getSoundList()));
        return binding.getRoot();
    }

    // Viewholder class
    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding binding;

        // Constructor
        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setViewModel(new SoundViewModel(beatBox));
        }

        public void bind(Sound sound) {
            binding.getViewModel().setSound(sound);
            binding.executePendingBindings();
        }
    }

    // Adapter class
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> soundList;

        public SoundAdapter(List<Sound> soundList) {
            this.soundList = soundList;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            holder.bind(soundList.get(position));
        }

        @Override
        public int getItemCount() {
            return soundList.size();
        }
    }

}
