package com.company.states;

import com.company.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class HealthyState extends State
{
    public HealthyState(Block block)
    {
        super(block);
    }

    // healthy state nie ma dzwięku i zmiany obrazka bo jest startowy
    @Override
    public void playSound()
    {
        //new Thread(new SoundThread("sounds/hit.wav")).start();
    }

    @Override
    public void changeImage()
    {
        // jak już będziemy mieli grafikę to się ustawi odpowiednią
        //String imagePath = "img/wood/hit.jpg";

        //block.setImg(imagePath);
    }

    @Override
    public void changeState()
    {
        if(this.block.hasEndurance())
            block.setState(new HitState(block));
        else
            block.setState(new DestroyedState(block));
    }

}
