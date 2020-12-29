package com.company;

import java.awt.*;

public class BallDecorator extends Ball {
    protected Ball ball;

    public BallDecorator(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void draw(Graphics2D g) {
        ball.draw(g);
    }

    @Override
    public int adjustSpeedXVector() {
        return ball.adjustSpeedXVector();
    }

    @Override
    public int adjustSpeedYVector() {
        return ball.adjustSpeedYVector();
    }

    @Override
    public int getLevelEnd() {
        return ball.getLevelEnd();
    }

    @Override
    public void move() {
        ball.move();
    }

    @Override
    public void collide() {
        ball.collide();
    }
}
