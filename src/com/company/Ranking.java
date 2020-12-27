package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private boolean updateFile() {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("ranking/ranking.txt"));

            for(Score score : scores) {
                writer.append(score.getUsername());
                writer.append("\n");
                writer.append(Integer.toString(score.getPoints()));
                writer.append("\n");
            }
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public void addScore(Score score) {
        scores.add(score);
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.compare(o2.getPoints(), o1.getPoints());
            }
        });

        if(!updateFile()) {
            throw new Error("File couldn't be updated!");
        }
    }
}
