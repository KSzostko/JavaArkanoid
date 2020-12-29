package com.company;

public class BallSpeedIncreaseDecorator extends BallDecorator {
    private boolean wasIncreased;

    public BallSpeedIncreaseDecorator(Ball ball) {
        super(ball);
        wasIncreased = false;
    }

    @Override
    public void move() {
        if(!wasIncreased) {
            ball.setSpeedX(ball.getSpeedX() * 2);
            ball.setSpeedY(ball.getSpeedY() * 2);
            wasIncreased = true;
        }

        ball.move();
    }
}
