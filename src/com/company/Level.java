package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Level extends JPanel {
    private Platform platform;

    public Level(Platform platform) {
        this.platform = platform;

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

        new Thread(new LevelController(this, platform)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        platform.draw(g);
    }
}
