package com.domin;

// class of an item being part of an inventory of a hero, it can give bonus points to damage, armour, or attack range of a hero
public class Item {

    private String name;
    private double weight;
    private int level; // bonuses points the item gives are multiplied by its level while calculating the final stat
    private int bonusDamage;
    private int bonusArmour;
    private double bonusAttackRange;

    public Item(String name, double weight, int level, int bonusDamage, int bonusArmour, double bonusAttackRange) {
        this.name = name;
        this.weight = weight;
        this.level = level;
        this.bonusDamage = bonusDamage;
        this.bonusArmour = bonusArmour;
        this.bonusAttackRange = bonusAttackRange;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getLevel() {
        return level;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public int getBonusArmour() {
        return bonusArmour;
    }

    public double getBonusAttackRange() {
        return bonusAttackRange;
    }
}
