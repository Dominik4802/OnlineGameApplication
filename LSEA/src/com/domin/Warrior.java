package com.domin;

import java.util.Iterator;

public class Warrior extends Hero {

    // Warrior being a tank type of hero with high base armour has a limited maximal total damage points to 60
    private final int maxDamage = 60;

    public Warrior(Player player) {
        super("Warrior", 35, 62, 61.4, 80.0, player);
    }

    // method calculating damage for the Warrior hero
    @Override
    protected void calculateDamage() {
        int totalDamage = super.getBaseDamage();
        if (super.getInventory() != null) {
            Iterator<Item> i = super.getInventory().iterator();

            while (i.hasNext()) {
                Item item = i.next();
                totalDamage += item.getBonusAttackRange() * item.getLevel();
            }
        }
        if (totalDamage > this.maxDamage) {     // in case hero exceeded the maximal damage points,
            totalDamage = this.maxDamage;       // its attack is limited to that maximum
        }
        super.setTotalDamage(totalDamage);
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}