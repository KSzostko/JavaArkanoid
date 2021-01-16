package com.company.states;

import com.company.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class State
{
    public Block block;

    public State(Block block)
    {
        this.block = block;
    }

    public abstract void playSound();
    public abstract void changeImage();
    public abstract void changeState();

    public class SoundThread implements Runnable
    {
        String filePath;
        public SoundThread(String filePath) {this.filePath = filePath;}

        @Override
        public void run() {
            try
            {
                File file = new File(filePath);
                AudioInputStream audio = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                TimeUnit.SECONDS.sleep(1);
            }
            catch (Exception e) { }
        }
    }
}