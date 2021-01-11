package com.company.states;

import com.company.Block;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class HealthyState extends State
{
    public HealthyState(Block block)
    {
        super(block);
    }

    @Override
    public void playSound()
    {
        new Thread(new SoundThread("JavaArkanoid\\sounds\\hit.wav")).start();
    }

    @Override
    public void changeImage()
    {
        // jak już będziemy mieli grafikę to się ustawi odpowiednią
        String imagePath = "JavaArkanoid\\img\\wood\\container.jpg";

        block.setImg(imagePath);
    }

    @Override
    public void changeState() {
        block.setState(new HitState(block));
    }
}
