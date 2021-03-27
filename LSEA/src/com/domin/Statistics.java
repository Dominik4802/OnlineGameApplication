package com.domin;

public class Statistics {

    private int duelsWon;
    private int duelsLost;
    private double score;
    private Player player;

    public Statistics(int duelsWon, int duelsLost, Player player) {
        this.duelsWon = duelsWon;
        this.duelsLost = duelsLost;
        this.player = player;
        if (duelsWon + duelsLost > 0) { // if the player played at least one duel
            this.score = calculateScore();
        }
        else {                          // if the player haven't played any duels yet
            this.score = calculateScore(100);
        }
        this.player.setStatistics(this);
    }

    // total score of a player is a balance of duels won and lost, multiplied by number of duels
    // player took part in, points of hero's statistics (total damage, total armour...) are added
    // to the obtained result and at the end there are 100 points added to the player's
    // score for every won duel
    private double calculateScore () {

        return ((this.duelsWon - this.duelsLost) * (this.duelsWon + this.duelsLost) +
                (player.getHero().getTotalDamage() + player.getHero().getTotalArmour() +
                    player.getHero().getTotalAttackRange() + player.getHero().getTotalAttackSpeed())
                + this.duelsWon * 100);
    }

    // overloaded method
    // in case the player haven't played any duels yet base 100 points are given to him and
    // only the sum of player's hero statistics in addition is taken into consideration
    // while calculating their total score
    private double calculateScore (int noDuelsPlayedYet) {

        return noDuelsPlayedYet + (player.getHero().getTotalDamage() + player.getHero().getTotalArmour() +
                player.getHero().getTotalAttackRange() + player.getHero().getTotalAttackSpeed());
    }

    // Getters:
    public int getDuelsWon() {
        return duelsWon;
    }

    public int getDuelsLost() {
        return duelsLost;
    }

    public double getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
