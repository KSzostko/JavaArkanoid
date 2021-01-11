package com.company;


import com.company.states.HealthyState;
import com.company.states.State;
//
import javax.swing.*;
import java.awt.*;


public class Block extends JComponent
{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;

    private State state;
    //
    private int x;
    private int y;
    private int endurance;
    private boolean removed = false;
    //
    private Image img;


    public Block(String imgPath, int x, int y, int endurance)
    {
        this.x = x;
        this.y = y;
        this.endurance = endurance;
        //
        this.state = new HealthyState(this);
        //
        this.img = ImgUtils.getImage(imgPath);
    }

    // just for object quick creation while testing
    public Block()
    {
        this.x = 50;
        this.y = 190;
        this.endurance = 2;
        this.img = ImgUtils.getImage("img/wood/container.jpg");
    }


    public boolean isRemoved() { return removed; }

    public boolean hasEndurance() { return endurance > 0; }

    public void draw(Graphics2D g) { g.drawImage(img, x, y, WIDTH, HEIGHT, null); }

    public Rectangle getBounds() { return new Rectangle(x, y, WIDTH, HEIGHT); }

    public void setState(State state) { this.state = state; }

    public void playSound() { this.state.playSound(); }

    public void changeImage() { this.state.changeImage(); }
    public void setImg(String imgPath) {this.img = ImgUtils.getImage(imgPath);}

    public void hit()
    {
        //chyba tak to powinno wyglądac, zostawiam do oceny
        playSound();
        changeImage();

        endurance--;
        if(endurance == 0)
            destroy();
        else
            state.changeState();
    }

     public void destroy()
     {
         // here will be more code of course
         removed = true;
     }
}
