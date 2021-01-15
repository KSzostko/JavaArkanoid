package com.company;

import java.util.Map;
import java.util.Random;

public class RectanglePositionStrategy extends PositionStrategy {
    public RectanglePositionStrategy(LevelBuilder builder, Map<String, Integer> blocksCount) {
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

            if(currentX + Block.WIDTH > Game.FRAME_WIDTH) {
                currentX = 0;
                currentY += Block.HEIGHT;
            }

            chooseBuildType(nextBlock);

            currentX += Block.WIDTH;
            totalBlocks--;
        }

        builder.addBall(new Speed(-3, -3));
        builder.addPlatform(300 - 100 / 2, 400);

        return builder.build();
    }
}
