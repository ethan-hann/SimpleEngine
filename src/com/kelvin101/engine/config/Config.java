package com.kelvin101.engine.config;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Provides access to configuration options for the engine and the game.
 * Options are stored in a HashMap where the <code>key</code> is the option name
 * and the <code>value</code> is the option argument
 */
public class Config
{
    private HashMap<String, String> options;
    private HashSet<String> configOptions;
    private static Config instance;

    private Config()
    {
        options = new HashMap<>();
        configOptions = new HashSet<>();
        for (ConfigOptions c : ConfigOptions.values())
        {
            configOptions.add(c.name().toLowerCase());
        }
    }

    public static Config getInstance()
    {
        if (instance == null)
        {
            instance = new Config();
        }
        return instance;
    }

    public HashMap<String, String> getOptions()
    {
        return options;
    }

    public HashSet<String> getConfigOptions()
    {
        return configOptions;
    }
}
