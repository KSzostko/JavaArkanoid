package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Level extends JPanel {
    private Platform platform;
    private Ball ball;
    private Point ballPoint = new Point(80, 300);

    public Level(Platform platform, Ball ball) {
        this.platform = platform;
        this.ball = ball;

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
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        super.paint(g2d);

        platform.draw(g2d);
        // hardcoded radius needs to be changed
        ball.draw(g2d, ballPoint, 20);
    }
}
