package com.company;

public class Score
{
    private String username;
    private int points;

    public Score(String username, int points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "User " + username + " scored " + points + " points";
    }

}
