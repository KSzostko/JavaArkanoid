package com.company;

import java.awt.*;

public class Platform {
    private int width;
    private int height;
    private int x;
    private int y;
    private int levelWidth;
    private Image image;

    public Platform(int width, int height, int x, int y, int levelWidth) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.levelWidth = levelWidth;
    }

    public Platform() {
        width = 40;
        height = 20;
        x = 300;
        y = 500;
        levelWidth = 600;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    // @TODO: adjust right step
    public void move(boolean left) {
        if(left) {
            if(x - 5 >= 0) x -= 5;
            // @TODO: drawing platform
        } else {
            if(x + 5 <= levelWidth) x += 5;
        }
    }
}
