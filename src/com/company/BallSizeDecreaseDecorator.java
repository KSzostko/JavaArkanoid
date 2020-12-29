package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BallSizeDecreaseDecorator extends BallDecorator {
    public BallSizeDecreaseDecorator(Ball ball) {
        super(ball);
    }

    @Override
    public int getLevelEnd() {
        return ball.getLevelEnd() * 2;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform savedAt = g.getTransform();
        ball.draw(changeCoordinateSystem(g));
        g.transform(savedAt);
    }

    // not finished
    // now ball change direction halfway
    private Graphics2D changeCoordinateSystem(Graphics2D g) {
        AffineTransform tr = new AffineTransform();

        g.scale(0.5, 0.5);
        //tr.scale(0.5, 0.5);
        //tr.translate(585, 0);
        g.transform(tr);

        return g;
    }
}
