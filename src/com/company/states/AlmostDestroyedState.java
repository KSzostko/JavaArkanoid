package com.company.states;

import com.company.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class AlmostDestroyedState extends State
{
    public AlmostDestroyedState(Block block)
    {
        super(block);
    }

    @Override
    public void playSound()
    {
        new Thread(new SoundThread("sounds/hit.wav")).start();
    }

    @Override
    public void changeImage()
    {
        // jak już będziemy mieli grafikę to się ustawi odpowiednią
        String imagePath = "img/stone/almost_destroyed.png";

        block.setImg(imagePath);
    }

    @Override
    public void changeState()
    {
        if (!this.block.hasEndurance())
            block.setState(new DestroyedState(block));
    }
}
