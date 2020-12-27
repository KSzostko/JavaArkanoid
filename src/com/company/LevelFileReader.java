package com.company;

import java.util.HashMap;

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
    // nie dałem tego bo nie ustalone jak ma plik wyglądać
    // poza tym w Map nie można trzymać intów itp
    /*
    public HashMap<char,int> readFile(String Path)
    {

        return null;
    }
    */

    private LevelFileReader()
    {

    }
}
