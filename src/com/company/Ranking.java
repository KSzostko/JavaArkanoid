package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class Ranking
{
    private List<Score> scores;

    private String file_path = "JavaArkanoid\\files\\ranking.txt";

    public Ranking()
    {
        // load current ranking from file
        scores = loadScores(file_path);
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


    public void addScore(Score score)
    {
        scores.add(score);

        // sorotwanie
    }

    // Wczytywanie listy z pliku
    private List<Score> loadScores(String path)
    {
        List<Score> list = new ArrayList<>();

        try
        {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String[] data = myReader.nextLine().split(" ");
                System.out.println(data);

                Score score = new Score(data[0], Integer.parseInt(data[1]));
                list.add(score);
            }
            return list;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return list;
        }
    }


}
