package com.company.decorators;

import com.company.Ball;
import com.company.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallSizeIncreaseDecorator extends BallDecorator {
    public BallSizeIncreaseDecorator(Ball ball) {
        super(ball);
    }

    @Override
    public void draw(Graphics2D g, Point p, int radius) {
        super.draw(g, p, increase(radius));
    }

    @Override
    public Ellipse2D getBounds(Point prev, int radius) {
        return super.getBounds(prev, increase(radius));
    }

    private int increase(int r) {
        return r * 2;
    }
}
