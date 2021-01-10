package com.company;

public class StoneLevelBuilder extends LevelBuilder {
    // TODO: Add stone image to every element

    @Override
    public void addStrongBlock(int x, int y) {
        Block block = new Block("img/wood/container.jpg", x, y, 100000);
        blocks.add(block);
    }

    @Override
    public void addWeakBlock(int x, int y) {
        Block block = new Block("img/wood/container.jpg", x, y, 1);
        blocks.add(block);
    }

    @Override
    public void addMediumBlock(int x, int y) {
        Block block = new Block("img/wood/container.jpg", x, y, 2);
        blocks.add(block);
    }

    @Override
    public void addNegativeSpeedBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SPEED_DECREASE, "img/wood/container.jpg");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addPositiveSpeedBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SPEED_INCREASE, "img/wood/container.jpg");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addPositiveSizeBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SIZE_INCREASE, "img/wood/container.jpg");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addNegativeSizeBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SIZE_DECREASE, "img/wood/container.jpg");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addBall(Speed speed) {
        ball = new Ball(speed);
    }

    @Override
    public void addPlatform(int x, int y) {
        platform = new Platform(x, y, 585, 10, "img/wood/container.jpg");
    }
}