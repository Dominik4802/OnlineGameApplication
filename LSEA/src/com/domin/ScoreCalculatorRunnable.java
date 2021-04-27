package com.domin;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ScoreCalculatorRunnable implements Runnable{

    int chunkSize;
    int i;
    ArrayList<Statistics> statsList;
    CountDownLatch countDownLatch;

    public ScoreCalculatorRunnable(int chunkSize, int i, ArrayList<Statistics> statsList, CountDownLatch countDownLatch) {
        this.chunkSize = chunkSize;
        this.i = i;
        this.statsList = statsList;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int j = chunkSize * i; j < chunkSize * (i + 1); j++) {
            if (statsList.get(j).getDuelsWon() + statsList.get(j).getDuelsLost() > 0) { // if the player played at least one duel
                statsList.get(j).setScore(statsList.get(j).calculateScore());
            } else {                          // if the player haven't played any duels yet
                statsList.get(j).setScore(statsList.get(j).calculateScore(100));
            }
            statsList.get(j).setRank(statsList.get(j).calculateRank());

            // adding operations to increase the time of computations
//            int counter = 0;
//            int dummy = 0;
//            while(counter <100000) {
//                counter++;
//                dummy = (dummy + counter) * dummy * dummy / (dummy+1);
//            }

        }
        countDownLatch.countDown();
        return;
    }
}
