package com.company.states;

import com.company.Block;

public abstract class State
{
    private Block block;

    public State(Block block)
    {
        this.block = block;
    }

    public abstract void changeSound();

    public abstract void changeImage();

}
