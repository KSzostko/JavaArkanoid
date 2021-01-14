package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LevelBuilder {
    protected Platform platform;
    protected Ball ball;
    protected List<Block> blocks = new ArrayList<>();
    protected Map<Point, Bonus> bonuses = new HashMap<>();
    // not sure whether we cann pass parameters to builder
    // this is need for level gameover
    private Game game;

    public LevelBuilder(Game game) {
        this.game = game;
    }

    public abstract void addStrongBlock(int x, int y);
    public abstract void addWeakBlock(int x, int y);
    public abstract void addMediumBlock(int x, int y);
    public abstract void addNegativeSpeedBonus(int x, int y);
    public abstract void addPositiveSpeedBonus(int x, int y);
    public abstract void addPositiveSizeBonus(int x, int y);
    public abstract void addNegativeSizeBonus(int x, int y);
    public abstract void addBall(Speed speed);
    public abstract void addPlatform(int x, int y);

    public Level build() {
        return new Level(game, platform, ball, blocks, bonuses);
    }
}
