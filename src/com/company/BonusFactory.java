package com.company;

import java.util.HashMap;
import java.util.Map;

public class BonusFactory {
    private static final Map<Bonus.Type, Bonus> bonuses = new HashMap<>();

    public static Bonus getBonus(Bonus.Type type, String imgPath) {
        Bonus bonus = bonuses.get(type);

        if(bonus == null) {
            bonus = new Bonus(type, imgPath);
            bonuses.put(type, bonus);
        }

        return bonus;
    }
}
