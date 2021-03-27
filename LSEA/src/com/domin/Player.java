package com.domin;


public class Player {

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

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}