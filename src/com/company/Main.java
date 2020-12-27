package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        Game game = new Game();
        //Ranking ranking = new Ranking();
        //ranking.addScore(new Score("Anon3", 200));
        //System.out.println(Arrays.toString(ranking.getScores().toArray(new Score[0])));
    }
}
