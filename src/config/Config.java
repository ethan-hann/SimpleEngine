package config;

import java.util.HashMap;
import java.util.HashSet;

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
