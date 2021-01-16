package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSnapshot {
    // Getters and setters
    public Platform getP() { return platform; }
    public void setP(Platform p) { this.platform = p; }
    public Ball getB() { return ball; }
    public void setB(Ball b) { this.ball = b; }
    public int getBallR() { return ballR; }
    public void setBallR(int ballR) { this.ballR = ballR; }
    public Point getBallP() { return ballP; }
    public void setBallP(Point ballP) { this.ballP = ballP; }
    public List<Block> getBlocks() { return blocks; }
    public void setBlocks(List<Block> blocks) { this.blocks = blocks; }
    public Map<Point, Bonus> getBonuses() { return bonuses; }
    public void setBonuses(Map<Point, Bonus> bonuses) { this.bonuses = bonuses; }
    public DestroyedBlocks getDestroyedBlocks() { return destroyedBlocks; }
    public void setDestroyedBlocks(DestroyedBlocks destroyedBlocks) { this.destroyedBlocks = destroyedBlocks; }
    // Attributes list, important attributes of level class are stored here
    private Platform platform;
    private Ball ball;
    private int ballR;
    private Point ballP;
    private List<Block> blocks;
    private Map<Point, Bonus> bonuses;
    private DestroyedBlocks destroyedBlocks;
    // Constructor of level snapshot, it takes data from already existing level
    // and stores in this class for later usage
    // objects must be inicialized with new keyword because
    // for ex. this.p = platform is only reference passing and it wont do
    public LevelSnapshot(Platform platform, Ball ball, List<Block> blocks,
                         Map<Point, Bonus> bonuses,int br,Point bp,DestroyedBlocks destroyedBlocks) {
        this.platform = new Platform(platform);
        this.ball = new Ball(ball.getSpeed());

        List<Block> new_blocklist = new ArrayList<>();
        for(int   i = 0; i<blocks.size() ;i++){
            new_blocklist.add(new Block(blocks.get(i)));
        }

        this.blocks = new_blocklist;
        Map<Point, Bonus> new_map = new HashMap<>();

        for (Map.Entry<Point, Bonus> entry : bonuses.entrySet()) {
            Point p = entry.getKey();
            Bonus bonus = entry.getValue();
            new_map.put(p,bonus);
        }
        this.bonuses = new_map;
        this.ballP = new Point(bp.getX(),bp.getY());
        this.ballR = br;
        this.destroyedBlocks = new DestroyedBlocks(destroyedBlocks);
    }
}
