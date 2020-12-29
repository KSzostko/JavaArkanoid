package com.company;

import java.awt.*;

public class Platform {
    private int width;
    private int height;
    private int x;
    private int y;
    private int levelWidth;
    private Image image;
    private int moving = 0;
    private final int sensitivity;

    public Platform(int width, int height, int x, int y, int levelWidth, int sensitivity, String file) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.levelWidth = levelWidth;
        this.sensitivity = Math.abs(sensitivity);
        image = ImgUtils.getImage(file);
    }

    public Platform() {
        width = 100;
        height = 20;
        x = 300 - 100 / 2;
        y = 400;
        levelWidth = 585;
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
            if(x + width + moving <= levelWidth) x += moving;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
