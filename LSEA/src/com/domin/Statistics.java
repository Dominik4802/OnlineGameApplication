package com.domin;

import java.util.ArrayList;

public class Statistics implements Cloneable {

    // enum representing ranks based on the score from MASTER (the highest), through DIAMOND1 (highest od diamonds),
    // down to BRONZE3 (the lowest)
    public enum Rank {
        MASTER,
        DIAMOND1,
        DIAMOND2,
        DIAMOND3,
        GOLD1,
        GOLD2,
        GOLD3,
        SILVER1,
        SILVER2,
        SILVER3,
        BRONZE1,
        BRONZE2,
        BRONZE3,
        UNRANKED
    }

    private int duelsWon;
    private int duelsLost;
    private double score;
    private int rankingPosition;
    private Rank rank;
    private Player player;

    public Statistics(int duelsWon, int duelsLost, Player player) {
        this.duelsWon = duelsWon;
        this.duelsLost = duelsLost;
        this.player = player;
        if (this.duelsWon + this.duelsLost > 0) { // if the player played at least one duel
            this.score = calculateScore();
        }
        else {                          // if the player haven't played any duels yet
            this.score = calculateScore(100);
        }
        this.rank = calculateRank();
        this.player.setStatistics(this);
    }


    public Statistics() {
    }

    // overriding clone method inherited from Object class
    @Override
    public Object clone() {
        Player playerCopy = new Player(this.getPlayer().getNickname(), this.getPlayer().getJoinDate(),
                this.getPlayer().getCountry());
        try {
            return (Statistics) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Statistics(this.getDuelsWon(), this.getDuelsLost(), playerCopy);
        }
    }

    // total score of a player is a balance of duels won and lost, multiplied by number of duels
    // player took part in, points of hero's statistics (total damage, total armour...) are added
    // to the obtained result and at the end there are 100 points added to the player's
    // score for every won duel
    public double calculateScore () {

        return ((this.duelsWon - this.duelsLost) * (this.duelsWon + this.duelsLost) +
                (player.getHero().getTotalDamage() + player.getHero().getTotalArmour() +
                    player.getHero().getTotalAttackRange() + player.getHero().getTotalAttackSpeed())
                + this.duelsWon * 100);
    }

    // overloaded method
    // in case the player haven't played any duels yet base 100 points are given to him and
    // only the sum of player's hero statistics in addition is taken into consideration
    // while calculating their total score
    public double calculateScore (int noDuelsPlayedYet) {

        return noDuelsPlayedYet + (player.getHero().getTotalDamage() + player.getHero().getTotalArmour() +
                player.getHero().getTotalAttackRange() + player.getHero().getTotalAttackSpeed());
    }

    // method calculating rank based on player's score using enum
    public Rank calculateRank () {
        if (this.score <= 0) return rank.UNRANKED;
        if (this.score > 0 & this.score <=500) return rank.BRONZE3;
        if (this.score > 500 & this.score <=1000) return rank.BRONZE2;
        if (this.score > 1000 & this.score <=2000) return rank.BRONZE1;
        if (this.score > 2000 & this.score <=5000) return rank.SILVER3;
        if (this.score > 5000 & this.score <=10000) return rank.SILVER2;
        if (this.score > 10000 & this.score <=20000) return rank.SILVER1;
        if (this.score > 20000 & this.score <=50000) return rank.GOLD3;
        if (this.score > 50000 & this.score <=100000) return rank.GOLD2;
        if (this.score > 100000 & this.score <=250000) return rank.GOLD1;
        if (this.score > 250000 & this.score <=500000) return rank.DIAMOND3;
        if (this.score > 500000 & this.score <=750000) return rank.DIAMOND2;
        if (this.score > 750000 & this.score <=1000000) return rank.DIAMOND1;
        else return rank.MASTER;
    }

    public void incrementDuelsWon () {
        setDuelsWon(this.duelsWon + 1);
        setScore(calculateScore());
        System.out.println("Incremented won duels of " + this.getPlayer().getNickname() + " by one.");
    }

    public void incrementDuelsLost () {
        setDuelsLost(this.duelsLost + 1);
        calculateScore();
        System.out.println("Incremented lost duels of " + this.getPlayer().getNickname() + " by one.");
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

    public int getRankingPosition() {
        return rankingPosition;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRankingPosition(int rankingPosition) {
        this.rankingPosition = rankingPosition;
    }

    public void setDuelsWon(int duelsWon) {
        this.duelsWon = duelsWon;
    }

    public void setDuelsLost(int duelsLost) {
        this.duelsLost = duelsLost;
    }

    public void setScore(double score) {
        this.score = score;
        this.rank = calculateRank();
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.player.setStatistics(this);
    }
}
