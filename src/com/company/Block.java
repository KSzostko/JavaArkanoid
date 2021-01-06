package com.company;

import com.company.states.State;

import javax.swing.*;
import java.awt.*;


// to tylko zarys bo jeszcze nie do końca ogarniam jak to ma wyglądać
public class Block extends JComponent {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;

    private State state;
    //
    private int x;
    private int y;
    private int endurance;
    //
    private Image img;
    private boolean removed = false;

    public Block(String imgPath, int x, int y, int endurance)
    {
        this.x = x;
        this.y = y;
        this.endurance = endurance;
        //
        this.img = ImgUtils.getImage(imgPath);

    }

    // just for object quick creation while testing
    public Block() {
        this.x = 50;
        this.y = 190;
        this.endurance = 2;
        this.img = ImgUtils.getImage("img/wood/container.jpg");
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean hasEndurance() {
        return endurance > 0;
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(img, x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
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

    public void hit() {
        endurance--;
        // here will be some state change
    }

     public void destroy() {
        // here will be more code of course
        removed = true;
     }
}
