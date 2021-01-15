package com.company;

import javax.swing.*;
import java.awt.*;

public class Platform extends JComponent {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public int getLevelWidth() {
        return levelWidth;
    }

    public Image getImage() {
        return image;
    }

    public int getMoving() {
        return moving;
    }

    public int getSensitivity() {
        return sensitivity;
    }
    //
    private int x;
    private int y;
    private int levelWidth;
    private Image image;
    private int moving = 0;



    private final int sensitivity;

    public Platform(int x, int y, int levelWidth, int sensitivity, String file) {
        this.x = x;
        this.y = y;
        this.levelWidth = levelWidth;
        this.sensitivity = Math.abs(sensitivity);
        image = ImgUtils.getImage(file);
    }


    public Platform() {
        x = 300 - 100 / 2;
        y = 400;
        levelWidth = Game.FRAME_WIDTH - 15;
        sensitivity = 10;
        image = ImgUtils.getImage("img/wood/container.jpg");
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void moveLeft() {
        moving = -sensitivity;
    }

    public void moveRight() {
        moving = sensitivity;
    }

    public void stop() {
        moving = 0;
    }

    public void tick() {
        if(moving < 0) {
            if(x + moving >= 0) x += moving;
        } else if (moving > 0) {
            if(x + WIDTH + moving <= levelWidth) x += moving;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
