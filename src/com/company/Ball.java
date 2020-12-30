package com.company;

import javax.swing.*;
import java.awt.*;

public class Ball {
    // @TODO: add ball image
    private Image img;
    private Speed speed;
    // hardcoded for movement test
    private int levelWidth = 585 - 20;

    public Ball(Speed speed) {
        this.speed = speed;
    }

    public Ball() {
        speed = new Speed(-3, -3);
        //radius = 20;
        //x = 80;
        //y = 300;
    }

    public void draw(Graphics2D g, Point p, int radius) {
        g.drawOval(p.getX(), p.getY(), radius, radius);
        g.fillOval(p.getX(), p.getY(), radius, radius);
        g.setColor(Color.RED);
    }

    public Point move(Point prev) {
        // @TODO: ball should have levelWidth and platform coords
        // hardcoded value will be removed
        // this is for testing ball movement
        if(prev.getX() >= levelWidth) {
            speed.setVectorX(speed.getVectorX() * -1);
        }

        if(prev.getX() <= 0) {
            speed.setVectorX(speed.getVectorX() * -1);
        }

        if(prev.getY() <= 0) {
            speed.setVectorY(speed.getVectorY() * -1);
        }

        // condition if it was on platform level or smth like this

        return new Point(prev.getX() + speed.getVectorX(), prev.getY() + speed.getVectorY());
    }

    //  check if there was collision with the block or platform
    // if there was, multiply by -1
    public void collide() {

    }
}
