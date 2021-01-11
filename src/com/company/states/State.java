package com.company.states;

import com.company.Block;

public abstract class State
{
    public Block block;

    public State(Block block)
    {
        this.block = block;
    }

    public abstract void playSound();

    public abstract void changeImage();

    // test
    public abstract void changeState();

}
