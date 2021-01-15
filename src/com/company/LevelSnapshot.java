package com.company;

import java.util.List;
import java.util.Map;

public class LevelSnapshot {
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
