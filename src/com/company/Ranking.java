package com.company;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Score> scores;

    public Ranking() {
        scores = new ArrayList<>();
        // load current ranking from file
    }

    public List<Score> getScores() {
        return scores;
    }

    public int getSize() {
        return scores.size();
    }

    public Score getScore(int index) {
        return scores.get(index);
    }

    public void addScore(Score score) {
        scores.add(score);
        // update ranking file
    }
}
