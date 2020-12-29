package com.company;

import java.awt.*;

public class BallSizeDecreaseDecorator extends BallDecorator {
    private boolean wasDecreased;

    public BallSizeDecreaseDecorator(Ball ball) {
        super(ball);
        wasDecreased = false;
    }

    @Override
    public void draw(Graphics2D g) {
        if(!wasDecreased) {
            ball.setRadius(ball.getRadius() / 2);
            wasDecreased = true;
        }

        ball.draw(g);
    }
}
