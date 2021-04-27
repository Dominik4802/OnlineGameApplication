package com.domin;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class OnlineGameApp {

    public static void main(String[] args) throws InterruptedException {

        // creating ArrayList of PLayer class
        ArrayList<Player> playersList = new ArrayList<Player>();
        // creating ArrayList of Hero class
        ArrayList<Hero> heroesList = new ArrayList<Hero>();

        // reading and creating Player class instances from the data in the players.csv file
        try {
            List<String> playerLines = Files.readAllLines(java.nio.file.Paths.get("./players.csv"), StandardCharsets.UTF_8);

            for (String line : playerLines) {
                Player player = new Player();
                String[] tokens = line.split(",");

                player.setNickname(tokens[0]);
                player.setJoinDate(tokens[1]);
                player.setCountry(tokens[2]);
                playersList.add(player);
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + playersList.size() + " records into playersList.");

        // matching every Player with a Hero in a random way
        for (Player player : playersList) {
            Random random = new Random();
            int num = random.nextInt(3);

            switch(num) {
                case 0:
                    Mage mage = new Mage(player);
                    heroesList.add(mage);
                    break;
                case 1:
                    Hunter hunter = new Hunter(player);
                    heroesList.add(hunter);
                    break;
                case 2:
                    Warrior warrior = new Warrior(player);
                    heroesList.add(warrior);
                    break;
            }
        }

        // creating ArrayList of Item class
        ArrayList<Item> itemsList = new ArrayList<Item>();

        // reading and creating Item class instances from the data in the items.csv file
        try {
            List<String> itemLines = Files.readAllLines(java.nio.file.Paths.get("./items.csv"), StandardCharsets.UTF_8);

            for (String line : itemLines) {
                Item item = new Item();
                String[] tokens = line.split(",");

                item.setName(tokens[0]);
                item.setWeight(Double.parseDouble(tokens[1]));
                item.setLevel(Integer.parseInt(tokens[2]));
                item.setBonusDamage(Integer.parseInt(tokens[3]));
                item.setBonusArmour(Integer.parseInt(tokens[4]));
                item.setBonusAttackRange(Double.parseDouble(tokens[5]));
                itemsList.add(item);
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + itemsList.size() + " records into itemsList.");


        // matching every Player with an Inventory in a random way
        for (Player player : playersList) {
            Random random = new Random();
            int numOfItems = random.nextInt(5) + 1; // every Hero can have up to 5 items in its inventory
            if (player.getHero().getName() == "Mage") {
                numOfItems = random.nextInt(3) + 1; // in case of Mage it's only 3 items
            }
            ArrayList<Item> inventory = new ArrayList<Item>();
            for (int i = 0; i < numOfItems; i++) {
                int index = (int)(Math.random() * itemsList.size());
                inventory.add(itemsList.get(index));    // adding Items to the inventory
                itemsList.remove(index);                // removing used Item from the Items list to do not duplicate Items
            }
            player.getHero().setInventory(inventory);
            // test
//            player.getHero().displayInventory();
        }

        // creating ArrayList of Statistics class
        ArrayList<Statistics> statsList = new ArrayList<Statistics>();

        // reading and creating Statistics class instances from the data in the stats.csv file
        try {
            List<String> statLines = Files.readAllLines(java.nio.file.Paths.get("./stats.csv"), StandardCharsets.UTF_8);

            for (String line : statLines) {
                Statistics stat = new Statistics();
                String[] tokens = line.split(",");

                stat.setDuelsWon(Integer.parseInt(tokens[0]));
                stat.setDuelsLost(Integer.parseInt(tokens[1]));
                statsList.add(stat);
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + statsList.size() + " records into statsList.");

        // matching every Player with Statistics in a random way
        int iterator = 0;
        for (Statistics stat : statsList) {
            stat.setPlayer(playersList.get(iterator));
            iterator++;
        }

        // defining number of threads (can be changed to see differences in the computation time)
        int numThreads = 8;
        System.out.println("Performing Score calculation and Rank assignment for the Statistics of every Player using " +
                numThreads + " threads.");

        long start = System.currentTimeMillis();    // start measuring time

        // defining chunk size based on the data size and number of threads we want to use
        int chunkSize = statsList.size() / numThreads;

        CountDownLatch countDownLatch = new CountDownLatch(numThreads);

        // creating threads and performing calculations on the given data chunk
        for (int i = 0; i < numThreads; i++) {
            ScoreCalculatorRunnable runnable = new ScoreCalculatorRunnable(chunkSize, i, statsList, countDownLatch);
            Thread t = new Thread(runnable);
            t.start();
        }
        countDownLatch.await(); // wait for the other threads to finish

        long end = System.currentTimeMillis();      // end measuring time
        long duration = end - start;

        System.out.println("It took: " + duration + " milliseconds to perform the calculations and assignments.\n");

        // initializing scoreCompare class used to compare Player class by its Score field in Statistics class field
        ScoreCompare scoreCompare = new ScoreCompare();

        // sorting players list by score using scoreCompare Comparator
        Collections.sort(playersList, scoreCompare);

        // initializing variable used to numerate items of the list and set the rankingPosition field in player's statistics
        int j = 1;
        // setting ranking position field in a statistics of a player, as the list is now sorted
        for (Player player : playersList) {
            player.getStatistics().setRankingPosition(j);
            j++;
        }


        // ================================== LAB 5 ==================================

        Scanner scanner = new Scanner(System.in);

        // validating user directory path
        String path = null;
        boolean properPath = false;
        while (!properPath) {
        System.out.println("Please specify the directory where You want to save the file:");
        path = scanner.nextLine();  // reading user input
            File f = new File(path);
            if (f.exists() && f.isDirectory()) {
                properPath = true;
            }
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path + "/ranking");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(playersList);
            oos.close();
            System.out.println("Saved sorted ArrayList of Players into the " + path + "/ranking" + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // validating user file path
        properPath = false;
        while (!properPath) {
            System.out.println("Please specify the path to the binary file You want to read data from:");
            path = scanner.nextLine();  // reading user input
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                properPath = true;
            }
        }
        // loading data from the binary file
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating ArrayList which will contain data read from the file
        ArrayList<Player> playersRankingLoaded = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            playersRankingLoaded = (ArrayList<Player>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Ranking from the time it was saved into the " + path +
                " file is now stored in a playersRankingLoaded ArrayList");
        for (int i = 0; i<5; i++){
            System.out.println(playersRankingLoaded.get(i).getStatistics().getRankingPosition() + ". " + playersList.get(i).getNickname() +
                        ", " + playersList.get(i).getStatistics().getScore() + " points" +
                        " ( " + playersList.get(i).getStatistics().getRank() + " )");
            System.out.println("Displayed top 5 players from the playersRankingLoaded ArrayList");
        }

        // validating user directory path
        properPath = false;
        while (!properPath) {
            System.out.println("Please specify the directory where You want to save the text file with nicknames of players from the ranking:");
            path = scanner.nextLine();  // reading user input
            File f = new File(path);
            if (f.exists() && f.isDirectory()) {
                properPath = true;
            }
        }
        try {
            save(path + "/ranking_nicknames.txt", playersList);
            System.out.println("Saved nicknames of the players from the ranking to the " + path + "/ranking_nicknames.txt" + " file.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // validating user file path
        properPath = false;
        while (!properPath) {
            System.out.println("Please specify the file path from which You want to read the data with nicknames of players from the ranking:");
            path = scanner.nextLine();  // reading user input
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                properPath = true;
            }
        }

        // printing out number given by the user of top players from the ranking read from the ranking_nicknames.txt file
        System.out.println("Specify the number of top players info (out of 50000) You want to display from the " + path + " file:");
        boolean flag = false;
        while (!flag) {
            try {
                int n = scanner.nextInt();
                if (n > 50000)
                    n = 50000;
                else if (n < 0) {
                    System.out.println("You provided a negative integer.");
                    n = 0;
                }
                try {
                    load(path, playersList, n);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Displayed top " + n + " players from the ranking.");
                flag = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input type (must be an integer).");
                scanner.nextLine();  // clear invalid input from scanner buffer
            }
        }
        scanner.close();
    }

    // saving nicknames from the Players ArrayList into a ranking_nicknames file
    public static void save(String path, ArrayList<Player> playersList) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(path));
        for (Player player : playersList)
            pw.println(player.getNickname());
        pw.close();
    }

    // reading nicknames from the ranking_nicknames file and displaying additional info of the Players in the ranking order
    public static void load(String path, ArrayList<Player> playersList, int n) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);

        for (int i =0; i < n; i++) {
            String nickname = scan.next();
            for (int j = 0; j < playersList.size(); j++) {
                if (playersList.get(j).getNickname().equals(nickname)) {
                    playersList.get(j).displayPlayerInfo();
                }
            }

        }
        scan.close();
    }
}
