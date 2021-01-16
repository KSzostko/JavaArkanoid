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

    @Override
    public void playSound()
    {

    }

    @Override
    public void changeImage()
    {
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
