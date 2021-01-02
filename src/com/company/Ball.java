package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends JComponent {
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
    }

    public void draw(Graphics2D g, Point p, int radius) {
        g.drawOval(p.getX(), p.getY(), radius, radius);
        g.fillOval(p.getX(), p.getY(), radius, radius);
        g.setColor(Color.RED);
    }

    public Point move(Point prev) {
        if(prev.getX() >= levelWidth) {
            speed.setVectorX(speed.getVectorX() * -1);
        }

        if(prev.getX() <= 0) {
            speed.setVectorX(speed.getVectorX() * -1);
        }

        if(prev.getY() <= 0) {
            speed.setVectorY(speed.getVectorY() * -1);
        }

        return new Point(prev.getX() + speed.getVectorX(), prev.getY() + speed.getVectorY());
    }

    public void collide(JComponent component) {
        // collision with ball and blocks should look slightly different

        if(component instanceof Block) {
            speed.setVectorX(speed.getVectorX() * -1);
        } else {
            // collision with platform

            // this probably can be even better
            // any idea is welcomed
            Random random = new Random();
            boolean changeDir = random.nextBoolean();

            if(changeDir) {
                speed.setVectorX(speed.getVectorX() * -1);
            }
        }

        speed.setVectorY(speed.getVectorY() * -1);
    }

    public Ellipse2D getBounds(Point prev, int radius) {
        return new Ellipse2D.Double(prev.getX(), prev.getY(), radius, radius);
    }
}
