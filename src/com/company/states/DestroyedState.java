package com.company.states;

import com.company.Block;

import javax.print.attribute.standard.Media;
//
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;


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
        // jak już będziemy mieli grafikę to się ustawi odpowiednią
        //String imagePath = "img/wood/destroyed.jpg";

        //block.setImg(imagePath);
    }

    @Override
    public void changeState() {
        ;
        //ostatni stan wiec nia nic sie nie zmienia
    }

}
