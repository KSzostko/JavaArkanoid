package com.company;

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

    public void draw(Graphics2D g) {
        g.drawOval(x, y, radius, radius);
        g.fillOval(x, y, radius, radius);
        g.setColor(Color.RED);
    }

    public void move() {
        // @TODO: ball should have levelWidth and platform coords
        // hardcoded value will be removed
        // this is for testing ball movement
        if(x >= 585 - radius) {
            speedX *= -1;
        }

        if(x <= 0) {
            speedX *= -1;
        }

        x += speedX;

        if(y <= 0) {
            speedY *= -1;
        }
        // condition if ball is below platform level
        // then it's game over
        y += speedY;
    }

    //  check if there was collision with the block or platform
    // if there was, multiply by -1
    public void collide() {

    }
}
