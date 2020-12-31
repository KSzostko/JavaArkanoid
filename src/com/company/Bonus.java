package com.company;

import com.company.decorators.*;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class Bonus {
    private int width;
    private int height;
    private int x;
    private int y;
    private Image image;
    private Type type;

    public Bonus(int width, int height, int x, int y, Image image, Type type) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.image = image;
        this.type = type;
    }

    public Bonus() {
        this.width = 20;
        this.height = 20;
        this.x = 50;
        this.y = 200;
        this.image = ImgUtils.getImage("img/wood/container.jpg");
        type = Type.SPEED_INCREASE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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