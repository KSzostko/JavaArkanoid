package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Map;

public class Level extends JPanel {
    private Game game;
    private Platform platform;
    private Ball ball;
    private int ballRadius = 20;
    private Point ballPoint = new Point(80, 300);

    public List<Block> getBlocks() {
        return blocks;
    }

    private List<Block> blocks;
    private Map<Point, Bonus> bonuses;
    private DestroyedBlocks destroyedBlocks = new DestroyedBlocks();
    // New constructor to aid getState func in levelSnapshot class
    public Level(Platform platform, Ball ball, List<Block> blocks, Map<Point, Bonus> bonuses,int br,Point bp,DestroyedBlocks destroyedBlocks) {
        this.platform = platform;
        this.ball = ball;
        this.blocks = blocks;
        this.bonuses = bonuses;
        this.ballRadius = br;
        this.ballPoint = bp;
        this.destroyedBlocks = destroyedBlocks;
        initLevel();

    }
    public Level(Game game, Platform platform, Ball ball, List<Block> blocks, Map<Point, Bonus> bonuses) {
        this.game = game;
        this.platform = platform;
        this.ball = ball;
        this.blocks = blocks;
        this.bonuses = bonuses;

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
            private volatile boolean gameover = false;

            private int countdownToFifteen = 0;

            @Override
            public void run() {
                while(!gameover) {
                    platform.tick();
                    // ball movement
                    ballPoint = ball.move(ballPoint);

                    checkCollision();

                    repaint();
                    Thread.yield();

                    // memento
                    // currently set to save every 5 second

                    if(countdownToFifteen >= 1*5000){
                        countdownToFifteen = 0;
                        game.history.add(save());
                    }else {
                        countdownToFifteen += 25;
                    }

                    try {
                        Thread.sleep(25);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            private void checkCollision() {
                Ellipse2D ballBounds = ball.getBounds(ballPoint, ballRadius);
                Rectangle platformBounds = platform.getBounds();

                // collision with the bonus
                for (Map.Entry<Point, Bonus> entry : bonuses.entrySet()) {
                    Point p = entry.getKey();
                    Bonus bonus = entry.getValue();
                    Rectangle bonusBounds = bonus.getBounds(p);

                    if (ballBounds.intersects(bonusBounds.getX(), bonusBounds.getY(), bonusBounds.getWidth(), bonusBounds.getHeight())) {
                        ball = bonus.addBonus(ball);

                        remove(bonus);
                        bonuses.remove(p);
                        revalidate();
                        repaint();

                        // probably only on bonus can be hit at the moment
                        // so there's no need to check the others
                        break;
                    }
                }

                // ball below platform is game over
                if(platformBounds.getY() - ballBounds.getHeight() < ballBounds.getY() - 10) {
                    gameover = true;
                    game.clearScreen();

                    // proper score will be calculated with the help of iterator
                    calculatePoints();
                    System.out.println(pts);
                    Score score = new Score(game.getUsername(), pts);
                    game.displayLevelEndView(score);
                }

                // platform was hit by ball
                if(ballBounds.intersects(platformBounds.getX(), platformBounds.getY(), platformBounds.getWidth(), platformBounds.getHeight())) {
                    ball.collide(ball);
                }

                for(Block block : blocks) {
                    Rectangle blockBounds = block.getBounds();
                    if(ballBounds.intersects(blockBounds.getX(), blockBounds.getY(), blockBounds.getWidth(), blockBounds.getHeight())
                            && !block.isRemoved()) {
                        block.hit();
                        ball.collide(block);

                        if(!block.hasEndurance()) {
                            // adding destroyed blocks to destroyedBlocks list
                            destroyedBlocks.getBlocks().add(block);
                            block.destroy();

                            remove(block);
                            revalidate();
                            repaint();

                            // same as with bonuses
                            break;
                        }
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

        for (Map.Entry<Point, Bonus> entry : bonuses.entrySet()) {
            Point p = entry.getKey();
            Bonus bonus = entry.getValue();

            bonus.draw(g2d, p);
        }

        for(Block block : blocks) {
            if(!block.isRemoved()) {
                block.draw(g2d);
            }
        }

        ball.draw(g2d, ballPoint, ballRadius);
    }
    //
    // Returns current state of level in form of a snapshot
    public LevelSnapshot save(){
        return new LevelSnapshot(platform,ball,blocks,bonuses,ballRadius,ballPoint,destroyedBlocks);
    }
    // Takes snap shot objects and changes current state of level to match snapshot's data
    public void restore(LevelSnapshot levelSnapshot){
        this.platform = levelSnapshot.getP();
        this.ball = levelSnapshot.getB();
        this.ballPoint = levelSnapshot.getBallP();
        this.ballRadius = levelSnapshot.getBallR();
        this.blocks = levelSnapshot.getBlo();
        this.bonuses = levelSnapshot.getBonus();

    }
    // Calculate points usign destroyed blocks etc..
    int pts = 0;
    public void calculatePoints(){

        BlockIterator iterator = new BlockIterator(destroyedBlocks);
        while(iterator.hasNext()){
            iterator.getNext();
            pts++;
        }
    }

}
