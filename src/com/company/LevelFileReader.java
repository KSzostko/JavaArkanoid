package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LevelFileReader
{
    private static LevelFileReader instance;

    private LevelFileReader() {}

    public static LevelFileReader getInstance()
    {
        if(instance == null)
        {
            instance = new LevelFileReader();
        }

        return instance;
    }

    // changed to String instead of Character just for convenience
    public Map<String, Integer> readFile(int levelNumber)
    {
        Map<String, Integer> blocksCount = new HashMap<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("levels/" + levelNumber + ".txt"));

            String fileLine;
            while((fileLine = br.readLine()) != null) {
                String name = fileLine.split(" ")[0];
                int count = Integer.parseInt(fileLine.split(" ")[1]);

                blocksCount.put(name, count);
            }

            br.close();
            return blocksCount;
        } catch (IOException e) {
            throw new Error("Level could not be loaded");
        }
    }
}
