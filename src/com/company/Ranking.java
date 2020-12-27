package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Score> scores;

    public Ranking() {
        scores = new ArrayList<>();
        loadRanking();
    }

    private void loadRanking() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("ranking/ranking.txt"));

            String fileLine, username = "";
            int points = -1, lineCount = 0;

            while((fileLine = br.readLine()) != null) {
                if(lineCount % 2 == 0) {
                    if(lineCount != 0) {
                        if(points == -1) {
                            throw new Error("Something is wrong with the data order!");
                        }

                        Score score = new Score(username, points);
                        scores.add(score);
                    }

                    username = fileLine;
                } else {
                    points = Integer.parseInt(fileLine);
                }
                lineCount++;
            }

            Score score = new Score(username, points);
            scores.add(score);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
