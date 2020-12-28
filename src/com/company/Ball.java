package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private int radius;
    private int x;
    private int y;
    private int speed;

    public Ball(int radius, int x, int y, int speed) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Ball() {
        radius = 20;
        x = 300;
        y = 300;
        speed = 3;
    }

    public void draw(Graphics g) {
        g.drawOval(x, y, radius, radius);
        g.fillOval(x, y, radius, radius);
        g.setColor(Color.RED);
    }

    public void move() {
        x += speed;
        y += speed;
    }
}
