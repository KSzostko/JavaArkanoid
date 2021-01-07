package com.company;

import java.util.Map;

public abstract class PositionStrategy {
    protected LevelBuilder builder;
    protected Map<String, Integer> blocksCount;

    public PositionStrategy(LevelBuilder builder, Map<String, Integer> blocksCount) {
        this.builder = builder;
        this.blocksCount = blocksCount;
    }

    public abstract Level arrangeObjects();
}
