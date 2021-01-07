package com.company.decorators;

import com.company.Ball;
import com.company.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallSizeDecreaseDecorator extends BallDecorator {
    public BallSizeDecreaseDecorator(Ball ball) {
        super(ball);
    }

    @Override
    public void draw(Graphics2D g, Point p, int radius) {
        super.draw(g, p, decrease(radius));
    }

    @Override
    public Ellipse2D getBounds(Point prev, int radius) {
        return super.getBounds(prev, decrease(radius));
    }

    private int decrease(int r) {
        return r / 2;
    }
}
