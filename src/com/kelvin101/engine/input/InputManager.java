package com.kelvin101.engine.input;

import com.kelvin101.engine.config.Config;
import com.kelvin101.engine.logger.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Handles all inputs for the engine/game. Gets its key mappings from {@link Config#getOptions()}
 */
public class InputManager implements KeyListener
{
    private static HashMap<Integer, Boolean> keys;
    private static InputManager instance;

    private InputManager()
    {
        keys = new HashMap<>();
        for (String s : Config.getInstance().getConfigOptions())
        {
            if (s.contains("key"))
            {
                String option = Config.getInstance().getOptions().get(s);
                Integer i = Integer.parseInt(option);
                keys.put(i, false);
            }
        }
    }

    public static InputManager getInstance()
    {
        if (instance == null)
        {
            instance = new InputManager();
        }
        return instance;
    }

    /**
     * Allows the changing of key mappings from the mapping defined in the Config file.
     * @param newKeyCode : the new ASCII key code for the {@param configKey}
     * @param configKey : the key descriptor contained in the Config file
     */
    public void changeKeyMapping(Integer newKeyCode, String configKey)
    {
        Integer oldKey = Integer.parseInt(Config.getInstance().getOptions().get(configKey));
        Config.getInstance().getOptions().put(configKey, String.format("%d", newKeyCode));
        keys.remove(oldKey);
        keys.put(newKeyCode, false);
        String logText = String.format("Key mapping changed! %s: %s -> %s", configKey, oldKey, newKeyCode);
        Logger.getInstance().write(logText);
    }

    public HashMap<Integer, Boolean> getKeys()
    {
        return keys;
    }

    /**
     * Utility method to check if a key is pressed
     * @param configKey : the key descriptor contained in the Config file
     * @return true if the key is pressed; false if not
     */
    public boolean isPressed(String configKey)
    {
        if (keys != null)
        {
            return keys.get(Integer.parseInt(Config.getInstance().getOptions().get(configKey)));
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keys.containsKey(keyCode))
        {
            keys.put(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keys.containsKey(keyCode))
        {
            keys.put(keyCode, false);
        }
    }
}
