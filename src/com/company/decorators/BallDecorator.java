package com.company.decorators;

import com.company.Ball;
import com.company.Point;

import java.awt.*;

public class BallDecorator extends Ball {
    protected Ball ball;

    public BallDecorator(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void draw(Graphics2D g, com.company.Point p, int radius) {
        ball.draw(g, p, radius);
    }

    @Override
    public com.company.Point move(Point prev) {
        return ball.move(prev);
    }

    @Override
    public void collide() {
        ball.collide();
    }
}
