package com.example.silvee.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by silvee on 27.12.2017.
 */
public class SoundViewModelTest {
    private BeatBox beatBox;
    private Sound sound;
    private SoundViewModel soundViewModel;

    @Before
    public void setUp() throws Exception {
        beatBox = mock(BeatBox.class);
        sound = new Sound("assetpath");
        soundViewModel = new SoundViewModel(beatBox);
        soundViewModel.setSound(sound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        assertThat(soundViewModel.getTitle(), is(sound.getSoundName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        soundViewModel.onButtonClicked();
        verify(beatBox).play(sound);
    }
}