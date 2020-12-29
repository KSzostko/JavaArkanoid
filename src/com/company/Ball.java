package com.company;

import javax.swing.*;
import java.awt.*;

public class Ball {
    private int radius;
    private int x;
    private int y;
    private int speedX;
    private int speedY;

    public Ball(int radius, int x, int y, int speedX, int speedY) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public Ball() {
        radius = 20;
        x = 80;
        y = 300;
        speedX = -3;
        speedY = -3;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void draw(Graphics2D g) {
        g.drawOval(x, y, radius, radius);
        g.fillOval(x, y, radius, radius);
        g.setColor(Color.RED);
    }

    public void move() {
        // @TODO: ball should have levelWidth and platform coords
        // hardcoded value will be removed
        // this is for testing ball movement
        speedX = adjustSpeedXVector();
        x += speedX;

        speedY = adjustSpeedYVector();
        y += speedY;
    }
    public int adjustSpeedXVector() {
        if(x >= getLevelEnd()) {
            return -speedX;
        }

        if(x <= 0) {
            return -speedX;
        }

        return speedX;
    }


    public int adjustSpeedYVector() {
        if(y <= 0) {
            return -speedY;
        }
        // condition if ball is below platform level
        // then it's game over
        // or if ball hit platform or block there need to be update here

        return speedY;
    }

    // hardcoded value for now
    // later will be updated
    public int getLevelEnd() {
        return 585 - radius;
    }

    //  check if there was collision with the block or platform
    // if there was, multiply by -1
    public void collide() {

    }
}
