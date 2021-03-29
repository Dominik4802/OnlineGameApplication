package com.domin;

import java.util.ArrayList;

public class Mage extends Hero {

    // Mage while being a very powerful hero has limited maximum inventory capacity
    private final int inventoryCapacity;

    public Mage(Player player) {
        super("Mage", 40, 25, 115.5, 60.0, player);
        this.inventoryCapacity = 3;     // Maximum capacity of Mage's inventory is 3
    }

    // overriding clone method inherited from Object class
    @Override
    public Object clone() {
        Player playerCopy = new Player(this.getPlayer().getNickname(), this.getPlayer().getJoinDate(),
                this.getPlayer().getCountry());
        Mage mage = null;
        ArrayList<Item> inventoryCopy = new ArrayList<Item>();
        try {
            mage = (Mage) super.clone();
        } catch (CloneNotSupportedException e) {
            mage = new Mage(playerCopy);
        }
        mage.name = this.getName();
        for (Item item : this.getInventory()) {
            inventoryCopy.add((Item)item.clone());
        }
        mage.setInventory(inventoryCopy);
        mage.setNumOfItems(this.numOfItems);
        mage.setTotalDamage(this.totalDamage);
        mage.setTotalArmour(this.totalArmour);
        mage.setTotalAttackRange(this.totalAttackRange);
        mage.setTotalAttackSpeed(this.totalAttackSpeed);

        return mage;
    }


    // overridden method adding item to the Mage hero's inventory, which capacity is limited
    @Override
    public void addItemToInventory(Item item) {
        if (super.getNumOfItems() < this.inventoryCapacity) {
            super.addItemToInventory(item);
        }
        else {
            System.out.println("The inventory of " + super.getPlayer().getNickname() + " is full!");
        }
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }
}
