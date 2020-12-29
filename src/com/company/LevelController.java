package com.company;

import javax.swing.*;

public class LevelController implements Runnable {
    private final JPanel panel;
    private final Platform platform;
    private final Ball ball;

    public LevelController(JPanel panel, Platform platform, Ball ball) {
        this.panel = panel;
        this.platform = platform;
        this.ball = ball;
    }

    @Override
    public void run() {
        while(true) {
            platform.tick();
            ball.move();

            panel.repaint();
            Thread.yield();

            try {
                Thread.sleep(10);
            }
             catch (InterruptedException e) {
                e.printStackTrace();
             }
        }
    }
}
