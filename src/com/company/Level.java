package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class Level extends JPanel {
    private Platform platform;
    private Ball ball;
    private Point ballPoint = new Point(80, 300);
    // later it will be list of blocks
    private Block block;
    // later it will be list of bonuses
    private Bonus bonus;

    public Level(Platform platform, Ball ball, Block block, Bonus bonus) {
        this.platform = platform;
        this.ball = ball;
        this.block = block;
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

        startLevel();
    }

    private void startLevel() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    platform.tick();
                    ballPoint = ball.move(ballPoint);

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
                Rectangle platformBounds = platform.getBounds();
                Rectangle blockBonuds = block.getBounds();

                // later we will be checking whole lists
                // collision with the bonus
                if(ballBounds.intersects(bonusBounds.getX(), bonusBounds.getY(), bonusBounds.getWidth(), bonusBounds.getHeight())
                        && !bonus.isRemoved()) {
                    System.out.println("Collision detected!");
                    ball = bonus.addBonus(ball);

                    remove(bonus);
                    revalidate();
                    repaint();
                }

                // ball below platform is game over
                if(platformBounds.getY() - ballBounds.getHeight() < ballBounds.getY() - 3) {
                    System.out.println("Game over!");
                }

                // platform was hit by ball
                if(ballBounds.intersects(platformBounds.getX(), platformBounds.getY(), platformBounds.getWidth(), platformBounds.getHeight())) {
                    ball.collide(ball);
                }

                if(ballBounds.intersects(blockBonuds.getX(), blockBonuds.getY(), blockBonuds.getWidth(), blockBonuds.getHeight())
                        && !block.isRemoved()) {
                    // here will be check if it has endurance, if not we need to remove it
                    block.hit();
                    if(!block.hasEndurance()) {
                        block.destroy();

                        remove(block);
                        revalidate();
                        repaint();
                    }

                    ball.collide(block);
                }
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        super.paint(g2d);

        platform.draw(g2d);

        if(!bonus.isRemoved()) {
            bonus.draw(g2d);
        }

        if(!block.isRemoved()) {
            block.draw(g2d);
        }

        // hardcoded radius needs to be changed
        ball.draw(g2d, ballPoint, 20);
    }
}
