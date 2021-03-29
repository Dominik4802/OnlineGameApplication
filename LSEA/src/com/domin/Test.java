package com.domin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        // initializing Player classes
        Player player1 = new Player("BestPlayer1234", "2018-06-20", "Poland");
        Player player2 = new Player("MajorPlayer32", "2016-01-22", "Russia");
        Player player3 = new Player("PataPon", "2015-07-12", "Finland");
        Player player4 = new Player("Udon", "2019-04-27", "USA");
        Player player5 = new Player("Tyson325", "2018-12-04", "Netherlands");

        // initializing Hero classes
        Mage mage1 = new Mage(player1);
        Mage mage2 = new Mage(player2);
        Hunter hunter1 = new Hunter(player3);
        Warrior warrior1 = new Warrior(player4);
        Warrior warrior2 = new Warrior(player5);

        // creating array list of Heroes in order to print them later on and demonstrate polymorphism
        ArrayList<Hero> heroesArrayList = new ArrayList<Hero>();

        // adding Heroes to the array list
        heroesArrayList.add(mage1);
        heroesArrayList.add(mage2);
        heroesArrayList.add(hunter1);
        heroesArrayList.add(warrior1);
        heroesArrayList.add(warrior2);

        // initializing Item classes
        Item sword = new Item("sword", 5.5, 1, 15, 0, 4.20);
        Item bow = new Item("bow", 2.4, 2, 8, 0, 15);
        Item shield = new Item("shield", 3.0, 1, 0, 18, 0);

        // creating Inventory array lists
        ArrayList<Item> inventory1 = new ArrayList<Item>();
        ArrayList<Item> inventory2 = new ArrayList<Item>();
        ArrayList<Item> inventory3 = new ArrayList<Item>();
        ArrayList<Item> inventory4 = new ArrayList<Item>();
        ArrayList<Item> inventory5 = new ArrayList<Item>();

        // adding Items to the Inventory array lists
        inventory1.add(bow);
        inventory1.add(sword);
        inventory2.add(sword);
        inventory3.add(bow);
        inventory5.add(sword);
        inventory5.add(shield);

        // setting Inventories to the corresponding Heroes
        mage1.setInventory(inventory1);
        mage2.setInventory(inventory2);
        hunter1.setInventory(inventory3);
        warrior1.setInventory(inventory4);
        warrior2.setInventory(inventory5);

        // initializing Statistics classes
        Statistics statistics1 = new Statistics(14, 18, player1);
        Statistics statistics2 = new Statistics(28, 12, player2);
        Statistics statistics3 = new Statistics(0, 0, player3);
        Statistics statistics4 = new Statistics(0, 1, player4);
        Statistics statistics5 = new Statistics(6, 6, player5);

        // testing implemented methods
        player1.displayPlayerInfo();
        player1.getHero().displayHeroStatistics();
        player1.getHero().displayInventory();
        player1.getHero().removeItemFromInventory(shield);
        player1.getHero().removeItemFromInventory(sword);
        player1.getHero().addItemToInventory(shield);
        player1.getHero().displayInventory();
        player1.getHero().displayHeroStatistics();

        player4.displayPlayerInfo();
        player4.getHero().displayHeroStatistics();
        player4.getHero().displayInventory();
        player4.getHero().removeItemFromInventory(shield);
        player4.getHero().removeItemFromInventory(sword);
        player4.getHero().addItemToInventory(shield);
        player4.getHero().displayInventory();
        player4.getHero().displayHeroStatistics();

        // creating array list iterator
        Iterator<Hero> i = heroesArrayList.iterator();

        // Polymorphism usage example:
        // in some cases (Mage Hero) when the inventory reached its full capacity the overridden addItemToInventory method
        // is executed instead of the one in the parent class
        while (i.hasNext()) {
            Hero hero = i.next();
            hero.addItemToInventory(sword);
            hero.addItemToInventory(shield);
            hero.addItemToInventory(bow);
            hero.displayInventory();
        }

        // creating array list of players in order to create a ranking
        ArrayList<Player> playersList = new ArrayList<Player>();
        playersList.add(player1);
        playersList.add(player2);
        playersList.add(player3);
        playersList.add(player4);
        playersList.add(player5);

        // initializing scoreCompare class used to compare Player class by its Score field in Statistics class field
        ScoreCompare scoreCompare = new ScoreCompare();

        // sorting players list by score using scoreCompare Comparator
        Collections.sort(playersList, scoreCompare);

        // initializing variable used to numerate items of the list and set the rankingPosition field in player's statistics
        int j = 1;

        // displaying list of players with their scores and ranks ordered descending by their score
        System.out.println("\nPlayers ranking is:");
        for (Player player : playersList) {
            System.out.println("\t" + j + ". " + player.getNickname() + ", " + player.getStatistics().getScore() + " points" +
                     " ( " + player.getStatistics().getRank() + " )");
            player.getStatistics().setRankingPosition(j);
            j++;
        }

        // creating deep copy of player1
        Player deepCopy = (Player) player1.clone();

        // displaying info about player1 and its deep copy
        player1.displayPlayerInfo();
        deepCopy.displayPlayerInfo();

        // changing player1's score field by changing their duelsWon field
        player1.getStatistics().incrementDuelsWon();

        // displaying info about player1 with changed score and its deep copy with score unchanged
        player1.displayPlayerInfo();
        deepCopy.displayPlayerInfo();

    }
}
