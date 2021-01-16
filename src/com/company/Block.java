package com.company;


import com.company.states.AlmostDestroyedState;
import com.company.states.HealthyState;
import com.company.states.HitState;
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
    private final int startEndurance;
    private boolean removed = false;
    //
    private Image img;
    //
    private String levelType = null;

    public Block(Block b){
        if(b.startEndurance == b.endurance) {
            state = new HealthyState(this);
        } else if(b.startEndurance > 1) {
            state = new HitState(this);
        } else {
            state = new AlmostDestroyedState(this);
        }

        this.x = b.x;
        this.y = b.y;
        this.endurance = b.endurance;
        this.img = b.img;
        this.removed = b.removed;
        startEndurance = b.endurance;

        this.levelType = b.getLevelType();
    }

    public Block(String imgPath, int x, int y, int endurance, String levelType)
    {
        this.x = x;
        this.y = y;
        this.endurance = endurance;
        startEndurance = endurance;
        //
        this.state = new HealthyState(this);
        //
        this.img = ImgUtils.getImage(imgPath);

        this.levelType = levelType;
    }

    public String getLevelType(){return this.levelType;}

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
        // zmiejszenie wytrzymałosci
        endurance--;

        // zmiana stanu na kolejny(block jest coraz bardziej obity)
        state.changeState();
        // zmiana wyglądu i puszczenie dzwięku z nowego stanu
        playSound();
        changeImage();

        // zniszczenie block-a
        if(endurance == 0)
            destroy();
    }

     public void destroy()
     {
         // here will be more code of course
         removed = true;
     }
}
