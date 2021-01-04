package com.company;

import com.company.decorators.*;

import javax.swing.*;
import java.awt.*;

// this has to extend from JComponent because otherwise we can't remove it from JPanel
public class Bonus extends JComponent {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;

    private Image image;
    private Type type;

    public Bonus(Type type, String imagePath) {
        this.type = type;
        this.image = ImgUtils.getImage(imagePath);
    }

    public Bonus() {
        this.image = ImgUtils.getImage("img/wood/container.jpg");
        type = Type.SPEED_DECREASE;
    }

    public void draw(Graphics2D g, Point p) {
        g.drawImage(image, p.getX(), p.getY(), WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds(Point p) {
        return new Rectangle(p.getX(), p.getY(), WIDTH, HEIGHT);
    }

    public Ball addBonus(Ball ball) {
        switch(type) {
            case SIZE_INCREASE:
                return new BallSizeIncreaseDecorator(ball);
            case SIZE_DECREASE:
                return new BallSizeDecreaseDecorator(ball);
            case SPEED_INCREASE:
                return new BallSpeedIncreaseDecorator(ball);
            case SPEED_DECREASE:
                return new BallSpeedDecreaseDecorator(ball);
            default:
                return ball;
        }
    }

    public enum Type {
        SPEED_INCREASE,
        SPEED_DECREASE,
        SIZE_INCREASE,
        SIZE_DECREASE
    }
}
