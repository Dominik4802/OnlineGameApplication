package com.domin;

import java.util.Comparator;

// class to compare Players by their score, in order to create a ranking from the highest score to the lowest (desc sort)
public class ScoreCompare implements Comparator<Player> {
    public int compare (Player p1, Player p2) {
        if (p1.getStatistics().getScore() > p2.getStatistics().getScore()) {
            return -1;
        }
        else if (p1.getStatistics().getScore() < p2.getStatistics().getScore()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
