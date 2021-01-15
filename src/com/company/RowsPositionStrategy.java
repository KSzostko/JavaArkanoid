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
            if(currentX + Block.WIDTH + GAP > Game.FRAME_WIDTH) {
                currentX = 0;
                currentY += Block.HEIGHT;
            }

            chooseBuildType(nextBlock);

            currentX += Block.WIDTH + GAP;
            totalBlocks--;
        }

        builder.addBall(new Speed(-3, -3));
        builder.addPlatform(300 - 100 / 2, 400);

        return builder.build();
    }
}
