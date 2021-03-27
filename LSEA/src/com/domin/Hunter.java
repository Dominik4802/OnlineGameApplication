package com.domin;

import java.util.Iterator;

public class Hunter extends Hero {

    // Hunter is a special class with great base statistics, but has a limited attack range to 110
    private final double maxAttackRange = 110.0;

    public Hunter(Player player) {
        super("Hunter", 50, 45, 86.0, 95.5, player);
    }

    // method calculating attack range for the Warrior hero
    @Override
    protected void calculateAttackRange() {
        double totalAttackRange = super.getBaseAttackRange();
        if (super.getInventory() != null) {
            Iterator<Item> i = super.getInventory().iterator();

            while (i.hasNext()) {
                Item item = i.next();
                totalAttackRange += item.getBonusAttackRange() * item.getLevel();
            }
            if (totalAttackRange > this.maxAttackRange) {       // in case hero exceeded the maximal attack range,
                totalAttackRange = this.maxAttackRange;         // its attack range is limited to that maximum
            }
        }
        super.setTotalAttackRange(totalAttackRange);
    }

    public double getMaxAttackRange() {
        return maxAttackRange;
    }
}