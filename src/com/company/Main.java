package com.company;

import java.awt.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game();
        Ranking ranking = new Ranking();
        System.out.println(Arrays.toString(ranking.getScores().toArray(new Score[0])));
    }
}
