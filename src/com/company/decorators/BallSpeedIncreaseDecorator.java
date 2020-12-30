package com.company.decorators;

import com.company.Ball;
import com.company.Point;

public class BallSpeedIncreaseDecorator extends BallDecorator {
    public BallSpeedIncreaseDecorator(Ball ball) {
        super(ball);
    }

    @Override
    public Point move(Point prev) {
        Point newPoint = super.move(prev);

        int newX;
        int newY;

        if(newPoint.getX() < prev.getX()) {
            newX = newPoint.getX() - 4;
        } else {
            newX = newPoint.getX() + 4;
        }

        if(newPoint.getY() < prev.getY()) {
            newY = newPoint.getY() - 4;
        } else {
            newY = newPoint.getY() + 4;
        }

        return new Point(newX, newY);
    }
}
