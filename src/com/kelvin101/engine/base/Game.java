package com.kelvin101.engine.base;

import com.kelvin101.engine.config.Config;
import com.kelvin101.engine.files.Files;
import com.kelvin101.engine.files.Logger;
import com.kelvin101.engine.gamestates.StateManager;
import com.kelvin101.engine.graphics.Display;
import com.kelvin101.engine.gui.ConfigGUI;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * The main Game class which contains the game loop and is responsible for
 * ticking and rendering all <code>GameObjects</code> and <code>States</code>.
 */
public class Game implements Runnable
{
    private static Game instance;
    private Display display;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics2D g2d;

    private int windowWidth;
    private int windowHeight;

    private Game()
    {
        new ConfigGUI().display(); //get the location of the com.kelvin101.engine.config file
        Files.getInstance(); //initialize all files and logging
        display = Display.getInstance(); //get an instance of the Display class
        windowWidth = Integer.parseInt(Config.getInstance().getOptions().get("window_width"));
        windowHeight = Integer.parseInt(Config.getInstance().getOptions().get("window_height"));

        start(); //start the game
    }

    public static Game getInstance()
    {
        if (instance == null)
        {
            instance = new Game();
        }
        return instance;
    }

    private void tick()
    {
        StateManager.getInstance().getCurrentState().tick();
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g2d = (Graphics2D) bs.getDrawGraphics();
        clearScreen();

        if (StateManager.getInstance().getCurrentState() != null)
        {
            StateManager.getInstance().getCurrentState().render(g2d);
        }

        bs.show();
        g2d.dispose();
    }

    private synchronized void start()
    {
        if (!running)
        {
            thread = new Thread(this);
            running = true;
            thread.start();
        }
    }

    @Override
    public void run()
    {
        int FPS = Integer.parseInt(Config.getInstance().getOptions().get("fps"));
        double timePerTick = 1_000_000_000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int gTicks = 0;
        String logText = "Running at: ".concat(String.format("%d", FPS)).concat(" FPS as per com.kelvin101.engine.config file");
        Logger.getInstance().write(logText);

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                //Tick and render states and game objects
                tick();
                render();

                gTicks++;
                delta--;
            }

            if (timer >= 1_000_000_000)
            {
                gTicks = 0;
                timer = 0;
            }
        }

        stop();
    }

    private void clearScreen()
    {
        g2d.clearRect(0, 0, windowWidth, windowHeight);
    }

    private synchronized void stop()
    {
        if (running)
        {
            try
            {
                running = false;
                thread.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
