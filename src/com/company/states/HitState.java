package com.company.states;

import com.company.Block;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class HitState extends State
{
    public HitState(Block block)
    {
        super(block);
    }

    @Override
    public void playSound()
    {
        // próbowałem 4 różnych metod do pusczenia dzwięku, żadna nie działa
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
        block.setState(new AlmostDestroyedState(block));
    }
}
