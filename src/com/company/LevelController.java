package com.company;

import javax.swing.*;

public class LevelController implements Runnable {
    private final JPanel panel;
    private final Platform platform;

    public LevelController(JPanel panel, Platform platform) {
        this.panel = panel;
        this.platform = platform;
    }

    @Override
    public void run() {
        while(true) {
            platform.tick();

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
