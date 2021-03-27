package com.domin;

public class Mage extends Hero {

    // Mage while being a very powerful hero has limited maximum inventory capacity
    private final int inventoryCapacity;

    public Mage(Player player) {
        super("Mage", 40, 25, 115.5, 60.0, player);
        this.inventoryCapacity = 3;     // Maximum capacity of Mage's inventory is 3
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
