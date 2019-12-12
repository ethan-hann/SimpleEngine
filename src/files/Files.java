package files;

import config.Config;
import exception.InvalidConfigException;

import java.io.*;
import java.util.HashMap;

public class Files
{
    private HashMap<String, File> gameFiles;
    private static Files instance;

    private StringBuilder sb;
    private BufferedWriter bw;
    private BufferedReader br;

    private Files()
    {
        sb = new StringBuilder();
        readConfig();
        initLogs();
    }

    public static Files getInstance()
    {
        if (instance == null)
        {
            instance = new Files();
        }
        return instance;
    }

    private boolean readConfig()
    {
        try
        {
            sb = new StringBuilder();
            br = new BufferedReader(new FileReader(Global.configFileLocation));
            String line;
            while ((line = br.readLine()) != null)
            {
                try
                {
                    parseOption(line);
                } catch (InvalidConfigException e)
                {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private void parseOption(String s) throws InvalidConfigException
    {
        String optString = s.replaceAll("\\\"", "");
        int equalsIndex = optString.indexOf('=');
        String key = optString.substring(0, equalsIndex);
        String value = optString.substring(equalsIndex + 1);
        if (!Config.getInstance().getConfigOptions().contains(key))
        {
            throw new InvalidConfigException("Config file contains invalid option -> "
                    .concat(key).concat(":").concat(value));
        }else
        {
            Config.getInstance().getOptions().put(key, value);
        }
    }

    private void initLogs()
    {
        Logger.getInstance().write("This is a log test");
    }
}
