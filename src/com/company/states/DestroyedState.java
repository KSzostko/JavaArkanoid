package com.company.states;

import com.company.Block;


public class DestroyedState extends State
{
    public DestroyedState(Block block)
    {
        super(block);
    }

    @Override
    public void playSound()
    {
        new Thread(new SoundThread("sounds/destroyed.wav")).start();
    }

    @Override
    public void changeImage()
    {
    }

    @Override
    public void changeState() {
    }

}
