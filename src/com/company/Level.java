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
    private List<Block> blocks;
    private Map<Point, Bonus> bonuses;
    private DestroyedBlocks destroyedBlocks = new DestroyedBlocks();
    private int pts = 0;

    private final int startingBlocksAmount;

    public Level(Game game, Platform platform, Ball ball, List<Block> blocks, Map<Point, Bonus> bonuses) {
        this.game = game;
        this.platform = platform;
        this.ball = ball;
        this.blocks = blocks;
        this.bonuses = bonuses;
        startingBlocksAmount = blocks.size();
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

            private int countdownToFifteen = 4000;

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
                    if(countdownToFifteen >= 4000){
                        countdownToFifteen = 0;
                        game.history.add(save());
                    }else {
                        countdownToFifteen += 20;
                    }

                    try {
                        Thread.sleep(20);
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

                        break;
                    }
                }

                // ball below platform is game over
                if(platformBounds.getY() - ballBounds.getHeight() < ballBounds.getY() - 10
                        || (destroyedBlocks.getBlocks().size() == startingBlocksAmount)) {
                    gameover = true;
                    game.clearScreen();

                    calculatePoints();
                    Score score = new Score(game.getUsername(), pts);
                    game.displayLevelEndView(score);
                }

                // platform was hit by ball
                if(ballBounds.intersects(platformBounds.getX(), platformBounds.getY(), platformBounds.getWidth(), platformBounds.getHeight())) {
                    ball.collide(ball);
                }

                // ball hit block
                for(Block block : blocks) {
                    Rectangle blockBounds = block.getBounds();
                    if(ballBounds.intersects(blockBounds.getX(), blockBounds.getY(), blockBounds.getWidth(), blockBounds.getHeight())
                            && !block.isRemoved()) {
                        block.hit();
                        ball.collide(block);

                        if(!block.hasEndurance()) {

                            destroyedBlocks.getBlocks().add(block);
                            block.destroy();

                            remove(block);
                            revalidate();
                            repaint();

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

    // Returns current state of level in form of a snapshot
    public LevelSnapshot save(){
        return new LevelSnapshot(platform,ball,blocks,bonuses,ballRadius,ballPoint,destroyedBlocks);
    }

    // Takessnap shot objects and changes current state of level to match snapshot's data
    public void restore(LevelSnapshot levelSnapshot){
        this.platform = levelSnapshot.getP();
        this.ball = levelSnapshot.getB();
        this.ballPoint = levelSnapshot.getBallP();
        this.ballRadius = levelSnapshot.getBallR();
        this.blocks = levelSnapshot.getBlocks();
        this.bonuses = levelSnapshot.getBonuses();
        this.destroyedBlocks = levelSnapshot.getDestroyedBlocks();

    }

    // Calculate points using destroyed blocks etc..
    public void calculatePoints(){

        BlockIterator iterator = new BlockIterator(destroyedBlocks);
        while(iterator.hasNext()){
            Block block = iterator.getNext();
            pts += block.getStartEndurance();
        }
    }
}
