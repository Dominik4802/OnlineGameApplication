package com.domin;


import java.io.Serializable;

public class Player implements Cloneable, Serializable {

    private String nickname;
    private String joinDate;
    private Hero hero;
    private String country;
    private Statistics statistics;

    public Player(String nickname, String joinDate, String country) {
        this.nickname = nickname;
        this.joinDate = joinDate;
        this.country = country;
    }

    public Player() {
    }

    // overriding clone method inherited from Object class
    @Override
    public Object clone() {
        Player player = null;

        try {
            player = (Player) super.clone();
        } catch (CloneNotSupportedException e) {
            player = new Player(this.nickname, this.joinDate, this.country);
        }

        player.statistics = (Statistics) this.statistics.clone();

        return player;
    }

    public void displayPlayerInfo () {
        System.out.println(this.nickname + " player from " + this.country + " joined the game in " + this.joinDate + ", plays with "
            + this.hero.getName() + " and has current score of " + this.statistics.getScore() + " points.");
    }

    public String getNickname() {
        return nickname;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public Hero getHero() {
        return hero;
    }

    public String getCountry() {
        return country;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
