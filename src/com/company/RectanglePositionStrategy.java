package com.company;

import java.util.Map;
import java.util.Random;

public class RectanglePositionStrategy extends PositionStrategy {
    private int currentX;
    private int currentY;

    public RectanglePositionStrategy(LevelBuilder builder, Map<String, Integer> blocksCount) {
        super(builder, blocksCount);
        currentX = 0;
        currentY = 0;
    }

    @Override
    public Level arrangeObjects() {
        String[] blockTypes = new String[blocksCount.size()];
        int[] currentCount = new int[blocksCount.size()];

        int i = 0;
        int totalBlocks = 0;
        for(Map.Entry<String, Integer> entry : blocksCount.entrySet()) {
            blockTypes[i] = entry.getKey();
            currentCount[i] = entry.getValue();

            i++;
            totalBlocks += entry.getValue();
        }

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
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH;
                    }
                    break;
                case "W":
                    builder.addWeakBlock(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH;
                    }
                    break;
                case "M":
                    builder.addMediumBlock(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Block.HEIGHT;
                    } else {
                        currentX += Block.WIDTH;
                    }
                    break;
                case "BSZN":
                    builder.addNegativeSizeBonus(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.WIDTH;
                    } else {
                        currentX += Bonus.HEIGHT;
                    }
                    break;
                case "BSZP":
                    builder.addPositiveSizeBonus(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH;
                    }
                    break;
                case "BSN":
                    builder.addNegativeSpeedBonus(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH;
                    }
                    break;
                case "BSP":
                    builder.addPositiveSpeedBonus(currentX, currentY);
                    if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                        currentX = 0;
                        currentY += Bonus.HEIGHT;
                    } else {
                        currentX += Bonus.WIDTH;
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
