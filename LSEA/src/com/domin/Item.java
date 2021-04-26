package com.domin;

// class of an item being part of an inventory of a hero, it can give bonus points to damage, armour, or attack range of a hero
public class Item implements Comparable<Item>, Cloneable {

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

    public Item() {
    }

    // overriding clone method inherited from Object class
    @Override
    public Object clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Item(this.getName(), this.getWeight(), this.getLevel(), this.getBonusDamage(),
                    this.getBonusArmour(), this.getBonusAttackRange());
        }
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

    // comparing Items by their levels, in order to sort them in a descending order
    @Override
    public int compareTo(Item o) {
        if (this.level > o.getLevel()) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public void setBonusArmour(int bonusArmour) {
        this.bonusArmour = bonusArmour;
    }

    public void setBonusAttackRange(double bonusAttackRange) {
        this.bonusAttackRange = bonusAttackRange;
    }
}
