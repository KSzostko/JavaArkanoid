package com.company;

import com.company.decorators.*;

import javax.swing.*;
import java.awt.*;

// this has to extend from JComponent because otherwise we can't remove it from JPanel
public class Bonus extends JComponent {
    private int width;
    private int height;
    private int x;
    private int y;
    private Image image;
    private Type type;
    private boolean removed = false;

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
        type = Type.SPEED_DECREASE;
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

    public boolean isRemoved() {
        return removed;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Ball addBonus(Ball ball) {
        removed = true;
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
