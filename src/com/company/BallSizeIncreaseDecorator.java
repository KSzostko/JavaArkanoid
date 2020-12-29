package com.company;

import java.awt.*;

public class BallSizeIncreaseDecorator extends BallDecorator {
    private boolean wasIncreased;

    public BallSizeIncreaseDecorator(Ball ball) {
        super(ball);
        wasIncreased = false;
    }

    @Override
    public void draw(Graphics2D g) {
        if(!wasIncreased) {
            ball.setRadius(ball.getRadius() * 2);
            wasIncreased = true;
        }

        ball.draw(g);
    }
}
