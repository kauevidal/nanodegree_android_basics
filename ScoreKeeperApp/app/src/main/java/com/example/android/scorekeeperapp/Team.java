package com.example.android.scorekeeperapp;

public class Team {

    private String name;
    private Integer point;
    private Integer set;

    public Team(String name, Integer point, Integer set) {
        this.name = name;
        this.point = point;
        this.set = set;
    }

    public void addPoint() {
        this.point++;
    }

    public void addSet() {
        this.set++;
    }

    public String getName() {
        return name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }
}
