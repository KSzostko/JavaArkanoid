package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSnapshot {
    // Getters and setters

    public Platform getP() {
        return p;
    }

    public void setP(Platform p) {
        this.p = p;
    }

    public Ball getB() {
        return b;
    }

    public void setB(Ball b) {
        this.b = b;
    }

    public int getBallR() {
        return ballR;
    }

    public void setBallR(int ballR) {
        this.ballR = ballR;
    }

    public Point getBallP() {
        return ballP;
    }

    public void setBallP(Point ballP) {
        this.ballP = ballP;
    }

    public List<Block> getBlo() {
        return blo;
    }

    public void setBlo(List<Block> blo) {
        this.blo = blo;
    }

    public Map<Point, Bonus> getBonus() {
        return bonus;
    }

    public void setBonus(Map<Point, Bonus> bonus) {
        this.bonus = bonus;
    }

    // Attributes list, important attributes of level class are stored here
    private Platform p;
    private Ball b;
    private int ballR = 20;
    private Point ballP = new Point(80, 300);
    private List<Block> blo;
    private Map<Point, Bonus> bonus;
    private List<Block> destroyedBlocks;
    // Constructor of levelsnapshot, it takes data from already existing level and stores in this class for later usage
    // objects must be inicialized with new keyword because for ex. this.p = platform is only reference passing and it wont do
    public LevelSnapshot(Platform platform, Ball ball, List<Block> blocks, Map<Point, Bonus> bonuses,int br,Point bp,List<Block> destroyedBlocks) {
        this.p = new Platform(platform.getX(),platform.getY(),platform.getLevelWidth(),platform.getSensitivity(),"img/wood/container.jpg");
        this.b = new Ball(ball.getSpeed());
        this.blo = new ArrayList(blocks);
        Map<Point, Bonus> new_map = new HashMap<>();
        new_map.putAll(bonuses);
        this.bonus = new_map;
        this.ballP = new Point(bp.getX(),bp.getY());
        this.ballR = br;
        //this.destroyedBlocks = new ArrayList(destroyedBlocks);
    }
    // return level object inicialized with past level attributes that were stored inside this class
    public Level getState(){
        return new Level(p, b, blo, bonus,ballR,ballP,destroyedBlocks);
    }
}
