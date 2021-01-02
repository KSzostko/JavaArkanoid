package com.company;

import com.company.states.State;

import javax.swing.*;
import java.awt.*;


// to tylko zarys bo jeszcze nie do końca ogarniam jak to ma wyglądać
public class Block extends JComponent {
    private State state;
    //
    private int x;
    private int y;
    private int width;
    private int height;
    private int endurance;
    //
    private Image img;
    private boolean removed = false;

    public Block(Image img, int x, int y, int width, int height, int endurance)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.endurance = endurance;
        //
        this.img = img;

    }

    // just for object quick creation while testing
    public Block() {
        this.x = 50;
        this.y = 190;
        this.width = 100;
        this.height = 40;
        this.endurance = 2;
        this.img = ImgUtils.getImage("img/wood/container.jpg");
    }

    public boolean isRemoved() {
        return removed;
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(img, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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

     public void destroy() {
        // here will be more code of course
        removed = true;
     }
}
