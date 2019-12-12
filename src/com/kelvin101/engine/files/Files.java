package com.kelvin101.engine.files;

import com.kelvin101.engine.config.Config;
import com.kelvin101.engine.exception.InvalidConfigException;

import java.io.*;
import java.util.HashMap;

/**
 * Handles reading and writing to com.kelvin101.engine.files
 */
public class Files
{
    private HashMap<String, File> gameFiles;
    private static Files instance;

    private StringBuilder sb;
    private BufferedWriter bw;
    private BufferedReader br;

    private Files()
    {
        gameFiles = new HashMap<>();
        sb = new StringBuilder();
        readConfig();
        Logger.getInstance(); // Initialize log file by simply getting an instance of the Logger class
    }

    public static Files getInstance()
    {
        if (instance == null)
        {
            instance = new Files();
        }
        return instance;
    }

    /**
     * Writes data to a specified file
     * @param path : the path to the file to write to
     * @param data : the data to write to the file
     * @return true if file writing was successful; false if not
     */
    public boolean writeToPath(String path, String data)
    {
        try
        {
            File f = new File(path);
            sb = new StringBuilder();
            bw = new BufferedWriter(new FileWriter(f));

            bw.write(data);
            bw.close();
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Writes data to a specified file and add it to the <code>gameFiles</code> HashMap.
     * @param desc : a key description for the <code>gameFiles</code> HashMap
     * @param path : the path to the file to write to
     * @param data : the data to write to the file
     * @return true if file writing was successful; false if not.
     */
    public boolean writeToHashMap(String desc, String path, String data)
    {
        try
        {
            File f = new File(path);
            gameFiles.put(desc, f);
            sb = new StringBuilder();
            bw = new BufferedWriter(new FileWriter(f));

            bw.write(data);
            bw.close();
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Read from a file
     * @param path : the path to the file
     * @return <code>String</code> representing the file contents; <code>null</code> if file could
     *  not be read from.
     */
    public String readFromPath(String path)
    {
        try
        {
            File f = new File(path);
            sb = new StringBuilder();
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read from a file contained in the <code>gameFiles</code> HashMap
     * @param key : the key for the file to read from
     * @return <code>String</code> representing the file contents; <code>null</code> if file could
     *  not be read from.
     */
    public String readFromHashMap(String key)
    {
        try
        {
            File f = gameFiles.get(key);
            sb = new StringBuilder();
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads in a configuration file and calls <code>parseOption(line)</code> to parse
     * configuration options. <code>line</code> is a line in the file denoted by a line break
     * @return true if file reading was successful; false if not.
     */
    private boolean readConfig()
    {
        try
        {
            gameFiles.put("configFile", new File(Global.configFileLocation));

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

    /**
     * Parse a configuration option from the com.kelvin101.engine.config file.
     * Parses the key and value pair contained in a line and ensures it is a valid
     * configuration option.
     * @param s : the full line to parse from
     * @throws InvalidConfigException if the key parsed is an invalid configuration option
     */
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

    public HashMap<String, File> getGameFiles()
    {
        return gameFiles;
    }
}
