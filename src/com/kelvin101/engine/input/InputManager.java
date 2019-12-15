package com.kelvin101.engine.input;

import com.kelvin101.engine.config.Config;
import com.kelvin101.engine.graphics.Display;
import com.kelvin101.engine.logger.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Handles all inputs for the engine/game. Gets its key mappings from {@link Config#getOptions()}
 */
public class InputManager implements KeyListener, MouseListener
{
    private static HashMap<Integer, Boolean> keys;
    private static HashMap<Integer, Boolean> mouseButtons;
    private static InputManager instance;

    private InputManager()
    {
        Display.getInstance().getFrame().addKeyListener(this);
        Display.getInstance().getCanvas().addMouseListener(this);

        keys = new HashMap<>();
        mouseButtons = new HashMap<>();

        for (String s : Config.getInstance().getConfigOptions())
        {
            if (s.contains("key"))
            {
                String option = Config.getInstance().getOptions().get(s);
                Integer i = Integer.parseInt(option);
                keys.put(i, false);
            }

            if (s.contains("mouse"))
            {
                String option = Config.getInstance().getOptions().get(s);
                Integer i = Integer.parseInt(option);
                mouseButtons.put(i, false);
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

    /**
     * Allows the changing of the mouse button mappings from the mapping defined in the Config file.
     * @param newMouseButton : the new Integer mouse button for the {@param configKey}
     * @param configKey : the key descriptor contained in the Config file
     */
    public void changeMouseMapping(Integer newMouseButton, String configKey)
    {
        Integer oldMouseButton = Integer.parseInt(Config.getInstance().getOptions().get(configKey));
        Config.getInstance().getOptions().put(configKey, String.format("%d", newMouseButton));
        mouseButtons.remove(oldMouseButton);
        mouseButtons.put(newMouseButton, false);
        String logText = String.format("Key mapping changed! %s: %s -> %s", configKey, oldMouseButton, newMouseButton);
        Logger.getInstance().write(logText);
    }

    /**
     * Gets the current key bindings and their states
     * @return keys : a hashMap containing Integer keys and Boolean values
     */
    public HashMap<Integer, Boolean> getKeys()
    {
        return keys;
    }

    /**
     * Gets the current mouse bindings and their states
     * @return mouseButtons : a hashMap containing Integer keys and Boolean values
     */
    public static HashMap<Integer, Boolean> getMouseButtons()
    {
        return mouseButtons;
    }

    /**
     * Utility method to check if a key is pressed
     * @param configKey : the key descriptor contained in the Config file
     * @return true if the key is pressed; false if not
     */
    public boolean isKeyPressed(String configKey)
    {
        if (keys != null)
        {
            return keys.get(Integer.parseInt(Config.getInstance().getOptions().get(configKey)));
        }
        return false;
    }

    /**
     * Utility method to check if a mouse button is pressed
     * @param configKey : the key descriptor contained in the Config file
     * @return true if the mouse button is pressed; false if not
     */
    public boolean isMousePressed(String configKey)
    {
        if (mouseButtons != null)
        {
            return mouseButtons.get(Integer.parseInt(Config.getInstance().getOptions().get(configKey)));
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //No current use for this method.
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

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int buttonCode = e.getButton();
        if (mouseButtons.containsKey(buttonCode))
        {
            mouseButtons.put(buttonCode, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        int buttonCode = e.getButton();
        if (mouseButtons.containsKey(buttonCode))
        {
            mouseButtons.put(buttonCode, false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
