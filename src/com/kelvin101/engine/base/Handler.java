package com.kelvin101.engine.base;

import java.util.HashMap;

public class Handler
{
    private static HashMap<String, GameObject> gameObjects;
    private static Handler instance;

    public static Handler getInstance()
    {
        if (instance == null)
        {
            instance = new Handler();
            gameObjects = new HashMap<>();
        }
        return instance;
    }

    public HashMap<String, GameObject> getGameObjects()
    {
        return gameObjects;
    }
}
