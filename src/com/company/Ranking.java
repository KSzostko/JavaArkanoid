package com.company;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Score> scores;

    public Ranking() {
        scores = new ArrayList<>();
        // load current ranking from file

        // test data, will be removed later
        scores.add(new Score("Anon", 123));
        scores.add(new Score("Anon1", 2123));
        scores.add(new Score("Anon2", 3123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
        scores.add(new Score("Anon3", 4123));
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

    // @TODO: Sort scores after adding new one
    public void addScore(Score score) {
        scores.add(score);
        // update ranking file
    }
}
