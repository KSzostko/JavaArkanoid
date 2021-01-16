package com.company;

public class GrassLevelBuilder extends LevelBuilder {
    // TODO: Add stone image to every element
    public GrassLevelBuilder(Game game) {
        super(game);
    }

    @Override
    public void addStrongBlock(int x, int y) {
        Block block = new Block("img/grass/healthy.png", x, y, 4, "grass");
        blocks.add(block);
    }

    @Override
    public void addWeakBlock(int x, int y) {
        Block block = new Block("img/grass/healthy.png", x, y, 1,"grass");
        blocks.add(block);
    }

    @Override
    public void addMediumBlock(int x, int y) {
        Block block = new Block("img/grass/healthy.png", x, y, 2,"grass");
        blocks.add(block);
    }

    @Override
    public void addNegativeSpeedBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SPEED_DECREASE, "img/assets/slow.png");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addPositiveSpeedBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SPEED_INCREASE, "img/assets/fast.png");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addPositiveSizeBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SIZE_INCREASE, "img/assets/sizeup.png");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addNegativeSizeBonus(int x, int y) {
        Bonus bonus = BonusFactory.getBonus(Bonus.Type.SIZE_DECREASE, "img/assets/sizedown.png");
        bonuses.put(new Point(x, y), bonus);
    }

    @Override
    public void addBall(Speed speed) {
        ball = new Ball(speed);
    }

    @Override
    public void addPlatform(int x, int y) {
        platform = new Platform(x, y, Game.FRAME_WIDTH, 10, "img/assets/platform.png");
    }
}
