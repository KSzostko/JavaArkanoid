package com.company;

import java.util.HashMap;
import java.util.Map;

public class LevelFileReader
{
    private static LevelFileReader instance;

    public static LevelFileReader getInstance()
    {
        if(instance == null)
        {
            instance = new LevelFileReader();
        }

        return instance;
    }

    // @TODO: funkcja do czytania plikow
    public Map<Character,Integer> readFile(String Path)
    {
        Map<Character, Integer> blocksCount = new HashMap<>();
        return null;
    }

    private LevelFileReader()
    {

    }
}
