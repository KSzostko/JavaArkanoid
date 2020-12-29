package com.company;

import java.awt.*;

public class BallDecorator extends Ball {
    protected Ball ball;

    public BallDecorator(Ball ball) {
        this.ball = ball;
    }

    public void draw(Graphics2D g) {
        ball.draw(g);
    }

    public void move() {
        ball.move();
    }
}
