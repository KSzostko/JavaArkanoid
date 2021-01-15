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

    protected void chooseBuildType(int next) {
        switch (blockTypes[next]) {
            case "S":
                builder.addStrongBlock(currentX, currentY);
                break;
            case "W":
                builder.addWeakBlock(currentX, currentY);
                break;
            case "M":
                builder.addMediumBlock(currentX, currentY);
                break;
            case "BSZN":
                builder.addNegativeSizeBonus(currentX, currentY);
                break;
            case "BSZP":
                builder.addPositiveSizeBonus(currentX, currentY);
                break;
            case "BSN":
                builder.addNegativeSpeedBonus(currentX, currentY);
                break;
            case "BSP":
                builder.addPositiveSpeedBonus(currentX, currentY);
                break;
        }
    }

    public abstract Level arrangeObjects();
}
