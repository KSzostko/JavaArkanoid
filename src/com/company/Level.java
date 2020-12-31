package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

public class Level extends JPanel {
    private Platform platform;
    private Ball ball;
    private Point ballPoint = new Point(80, 300);
    // later it will be list of bonuses
    private Bonus bonus;

    public Level(Platform platform, Ball ball, Bonus bonus) {
        this.platform = platform;
        this.ball = ball;
        this.bonus = bonus;

        initLevel();
    }

    private void initLevel() {
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        platform.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        platform.moveRight();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        platform.stop();
                        break;
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    platform.tick();
                    ballPoint = ball.move(ballPoint);
                    //System.out.println(ball.getBounds(ballPoint, 20).getX());

                    checkCollision();

                    repaint();
                    Thread.yield();

                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void checkCollision() {
                Ellipse2D ballBounds = ball.getBounds(ballPoint, 20);
                Rectangle bonusBounds = bonus.getBounds();

                // later we will be checking whole lists
                if(ballBounds.intersects(bonusBounds.getX(), bonusBounds.getY(), bonusBounds.getWidth(), bonusBounds.getHeight())) {
                    System.out.println("Collision detected!");
                    ball = bonus.addBonus(ball);
                    // now should be method which will change ball speed vector somehow
                }
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        super.paint(g2d);

        platform.draw(g2d);
        bonus.draw(g2d);
        // hardcoded radius needs to be changed
        ball.draw(g2d, ballPoint, 20);
    }
}
