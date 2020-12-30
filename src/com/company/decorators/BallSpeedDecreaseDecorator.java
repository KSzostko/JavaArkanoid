package com.company.decorators;

import com.company.Ball;
import com.company.Point;

public class BallSpeedDecreaseDecorator extends BallDecorator {
    public BallSpeedDecreaseDecorator(Ball ball) {
        super(ball);
    }

    public Point move(Point prev) {
        Point newPoint = super.move(prev);

        int newX;
        int newY;

        if(newPoint.getX() < prev.getX()) {
            newX = newPoint.getX() + 2;
        } else {
            newX = newPoint.getX() - 2;
        }

        if(newPoint.getY() < prev.getY()) {
            newY = newPoint.getY() + 2;
        } else {
            newY = newPoint.getY() - 2;
        }

        return new Point(newX, newY);
    }
}
