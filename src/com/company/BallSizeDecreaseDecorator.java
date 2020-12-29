package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BallSizeDecreaseDecorator extends BallDecorator {
    public BallSizeDecreaseDecorator(Ball ball) {
        super(ball);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform savedAt = g.getTransform();
        super.draw(changeCoordinateSystem(g));
        //g.setTransform(savedAt);
    }

    // not finished
    // now ball change direction halfway
    private Graphics2D changeCoordinateSystem(Graphics2D g) {
        AffineTransform tr = new AffineTransform();

        tr.scale(0.5, 0.5);
        //tr.translate(585, 0);
        g.transform(tr);

        return g;
    }
}
