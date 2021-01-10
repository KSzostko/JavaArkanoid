package com.company;

import states.HealthyState;
import states.State;

import java.awt.*;


// to tylko zarys bo jeszcze nie do końca ogarniam jak to ma wyglądać
public class Block
{
    private State state;
    //
    private int x;
    private int y;
    private int endurance;
    //
    private Image img;

    public Block(Image img, int x, int y, int endurance)
    {
        this.x = x;
        this.y = y;
        this.endurance = endurance;
        //
        this.img = img;
        //
        this.state = new HealthyState(this);

    }

    public void draw(Graphics2D g, Point p)
    {
        // nie wiem czy to ma być położenie z klasy czy z punktu
        //g.drawImage(img, x, y, null);
        g.drawImage(img, p.getX(), p.getY(), null);
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public void changeSound()
    {
        this.state.changeSound();
    }

    public void changeImage()
    {
        this.state.changeImage();
    }

    // public void hit();

    // public void destroy();
}
