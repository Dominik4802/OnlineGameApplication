package com.domin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// abstract Hero class to store universal data and methods of different heroes
public abstract class Hero implements Cloneable {

    protected String name;
    protected final int baseDamage;
    protected final int baseArmour;
    protected final double baseAttackRange;
    protected final double baseAttackSpeed;
    protected ArrayList<Item> inventory; // to be set with a setter
    protected int numOfItems;
    protected int totalDamage;
    protected int totalArmour;
    protected double totalAttackRange;
    protected double totalAttackSpeed;
    private Player player;

    public Hero(String name, int baseDamage, int baseArmour, double baseAttackRange, double baseAttackSpeed, Player player) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.baseArmour = baseArmour;
        this.baseAttackRange = baseAttackRange;
        this.baseAttackSpeed = baseAttackSpeed;
        this.numOfItems = 0;
        this.player = player;
        this.player.setHero(this);
    }

    // calculating total damage in case of change in the hero's inventory
    protected void calculateDamage () {
        int totalDamage = this.baseDamage;
        if (this.inventory != null) {
            Iterator<Item> i = this.inventory.iterator();

            while (i.hasNext()) {
                Item item = i.next();
                totalDamage += item.getBonusDamage() * item.getLevel();
            }
        }
        this.setTotalDamage(totalDamage);
    }

    // calculating total armour of the hero in case of the change in the hero's inventory
    protected void calculateArmour () {
        int totalArmour = this.baseArmour;
        if (this.inventory != null) {
            Iterator<Item> i = this.inventory.iterator();

            while (i.hasNext()) {
                Item item = i.next();
                totalArmour += item.getBonusArmour() * item.getLevel();
            }
        }
        this.setTotalArmour(totalArmour);
    }

    // calculating total attack range of the hero in case of the change in the hero's inventory
    protected void calculateAttackRange () {
        double totalAttackRange = this.baseAttackRange;
        if (this.inventory != null) {
            Iterator<Item> i = this.inventory.iterator();

            while (i.hasNext()) {
                Item item = i.next();
                totalAttackRange += item.getBonusAttackRange() * item.getLevel();
            }
        }
        this.setTotalAttackRange(totalAttackRange);
    }

    // calculating total damage of the hero in case of the change in the hero's inventory
    protected void calculateAttackSpeed () {
        double totalAttackSpeed = this.baseAttackSpeed;
        if (this.inventory != null) {
            Iterator<Item> i = this.inventory.iterator();

            while (i.hasNext()) {
                Item item = i.next();
                if (totalAttackSpeed - item.getWeight() >= 10.0) { // Attack speed of a hero can not be lower than 10, even if the total weight of their inventory is very heavy
                    totalAttackSpeed -= item.getWeight();   // Attack speed of a hero is reduced by one unit for every kg in its inventory
                } else {  // if their inventory is heavy enough to reduce hero's attack speed to less than 10, its attack speed is simply set to 10.0
                    totalAttackSpeed = 10.0;
                }
            }
        }
        this.setTotalAttackSpeed(totalAttackSpeed);
    }

    // adding item to inventory and calculating new damage, armour, attack range and attack speed values
    public void addItemToInventory (Item item) {
        this.inventory.add(item);
        Collections.sort(inventory);    // sorting Items in inventory from the highest level to the lowest
        this.setNumOfItems(this.inventory.size());
        calculateDamage();
        calculateArmour();
        calculateAttackRange();
        calculateAttackSpeed();
        System.out.println(item.getName() + " was added to the inventory of " + player.getNickname());
    }

    public void removeItemFromInventory (Item item) {
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            this.setNumOfItems(this.inventory.size());
            calculateDamage();
            calculateArmour();
            calculateAttackRange();
            calculateAttackSpeed();
            System.out.println(item.getName() + " was removed from the inventory of " + player.getNickname());
            return;
        }

        System.out.println(item.getName() + " was not found in the inventory of " + player.getNickname());
    }

    // displaying hero's inventory
    public void displayInventory () {
        if (this.inventory.isEmpty()) {
            System.out.println("Inventory of " + player.getNickname() + " (" + this.name + ") is empty!");
        }
        else {
            System.out.println("Inventory of " + player.getNickname() + " (" + this.name + ")" +
                    " cosists of " + this.numOfItems + " items:");
            for (int i = 0; i < this.inventory.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.inventory.get(i).getName() +
                        " (" + this.inventory.get(i).getLevel() + " lvl)");
            }
        }
    }

    // displaying hero's damage, armour, attack range and attack speed values
    public void displayHeroStatistics () {
        System.out.println(player.getNickname() + " (" + this.getName() + ") " + "statistics:" +
                "\n \t Damage:" + this.getTotalDamage() +
                "\n \t Armour:" + this.getTotalArmour() +
                "\n \t Attack range:" + this.getTotalAttackRange() +
                "\n \t Attack speed:" + this.getTotalAttackSpeed());
    }

    // Getters:
    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getBaseArmour() {
        return baseArmour;
    }

    public double getBaseAttackRange() {
        return baseAttackRange;
    }

    public double getBaseAttackSpeed() {
        return baseAttackSpeed;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public int getTotalArmour() {
        return totalArmour;
    }

    public double getTotalAttackRange() {
        return totalAttackRange;
    }

    public double getTotalAttackSpeed() {
        return totalAttackSpeed;
    }

    public Player getPlayer() {
        return player;
    }

    // Setters:

    // setting inventory and calculating new damage, armour, attack range and attack speed values
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
        this.setNumOfItems(this.inventory.size());
        calculateDamage();
        calculateArmour();
        calculateAttackRange();
        calculateAttackSpeed();
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public void setTotalArmour(int totalArmour) {
        this.totalArmour = totalArmour;
    }

    public void setTotalAttackRange(double totalAttackRange) {
        this.totalAttackRange = totalAttackRange;
    }

    public void setTotalAttackSpeed(double totalAttackSpeed) {
        this.totalAttackSpeed = totalAttackSpeed;
    }


}
