package com.company;

import java.awt.*;

public class Platform {
    private int width;
    private int height;
    private int x;
    private int y;
    private int levelWidth;
    private Image image;
    private int moving;

    public Platform(int width, int height, int x, int y, int levelWidth) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.levelWidth = levelWidth;
        moving = 0;
    }

    public Platform() {
        width = 40;
        height = 20;
        x = 50;
        y = 50;
        levelWidth = 585;
        moving = 0;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void moveLeft() {
        moving = -5;
    }

    public void moveRight() {
        moving = 5;
    }

    public void stop() {
        moving = 0;
    }

    public void tick() {
        if(moving < 0) {
            if(x + moving >= 0) x += moving;
        } else if (moving > 0) {
            System.out.println(x + width + moving);
            if(x + width + moving <= levelWidth) x += moving;
        }
    }

    // @TODO: add platform image
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.drawRect (x, y, width, height);
    }
}
