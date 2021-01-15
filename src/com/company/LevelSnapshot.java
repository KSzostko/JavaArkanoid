package com.company;

import java.util.List;
import java.util.Map;

public class LevelSnapshot {
    public Game getG() {
        return g;
    }

    public void setG(Game g) {
        this.g = g;
    }

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

    private Game g;
    private Platform p;
    private Ball b;
    private int ballR = 20;
    private Point ballP = new Point(80, 300);
    private List<Block> blo;
    private Map<Point, Bonus> bonus;

    public LevelSnapshot(Game game, Platform platform, Ball ball, List<Block> blocks, Map<Point, Bonus> bonuses,int br,Point bp) {
        this.g = game;
        this.p = platform;
        this.b = ball;
        this.blo = blocks;
        this.bonus = bonuses;
        this.ballP = bp;
        this.ballR = br;
    }

    public Level getState(){
        return new Level(g, p, b, blo, bonus,ballR,ballP);
    }
}
