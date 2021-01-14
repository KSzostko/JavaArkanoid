package com.company;

import java.util.Map;
import java.util.Random;

public class RowsPositionStrategy extends PositionStrategy {
    private static final int GAP = 40;

    public RowsPositionStrategy(LevelBuilder builder, Map<String, Integer> blocksCount) {
        super(builder, blocksCount);
    }

    @Override
    public Level arrangeObjects() {
        Random random = new Random();
        int nextBlock = random.nextInt(blockTypes.length);

        // TODO: Probably there can be few bugs in this loop, needs rechecking for sure
        while(totalBlocks > 0) {
            while(currentCount[nextBlock] == 0) {
                nextBlock = random.nextInt(blockTypes.length);
            }

            currentCount[nextBlock]--;
            switch (blockTypes[nextBlock]) {
                case "S":
                    builder.addStrongBlock(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH + GAP;
                    }
                    break;
                case "W":
                    builder.addWeakBlock(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH + GAP;
                    }
                    break;
                case "M":
                    builder.addMediumBlock(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH + GAP;
                    }
                    break;
                case "BSZN":
                    builder.addNegativeSizeBonus(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.WIDTH;
                    } else {
                        currentX += Bonus.HEIGHT + GAP;
                    }
                    break;
                case "BSZP":
                    builder.addPositiveSizeBonus(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH + GAP;
                    }
                    break;
                case "BSN":
                    builder.addNegativeSpeedBonus(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH + GAP;
                    }
                    break;
                case "BSP":
                    builder.addPositiveSpeedBonus(currentX, currentY);
                    if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH + GAP;
                    }
                    break;
            }

            totalBlocks--;
        }

        builder.addBall(new Speed(-3, -3));
        builder.addPlatform(300 - 100 / 2, 400);

        return builder.build();
    }
}