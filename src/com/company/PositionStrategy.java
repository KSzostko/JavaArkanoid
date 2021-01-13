package com.company;

import java.util.Map;

public abstract class PositionStrategy {
    protected LevelBuilder builder;
    protected int currentX;
    protected int currentY;
    protected String[] blockTypes;
    protected int[] currentCount;
    protected int totalBlocks;

    public PositionStrategy(LevelBuilder builder, Map<String, Integer> blocksCount) {
        this.builder = builder;

        currentX = 0;
        currentY = 0;
        totalBlocks = 0;

        blockTypes = new String[blocksCount.size()];
        currentCount = new int[blocksCount.size()];

        initData(blocksCount);
    }

    private void initData(Map<String, Integer> blocksCount) {
        int i = 0;
        for(Map.Entry<String, Integer> entry : blocksCount.entrySet()) {
            blockTypes[i] = entry.getKey();
            currentCount[i] = entry.getValue();

            i++;
            totalBlocks += entry.getValue();
        }
    }

    public abstract Level arrangeObjects();
}
