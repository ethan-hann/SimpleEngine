package base;

import config.Config;
import files.Files;
import files.Logger;
import graphics.Display;
import gui.ConfigGUI;

/**
 * The main Game class which contains the GameLoop and is responsible for
 * ticking and rendering all GameObjects and States
 */
public class Game implements Runnable
{
    private static Game instance;
    private Display display;

    private boolean running = false;
    private Thread thread;

    private Game()
    {
        new ConfigGUI().display(); //get the location of the config file
        Files.getInstance(); //initialize all files and logging
        display = Display.getInstance(); //get an instance of the Display class
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
        Logger.getInstance().write("Running at: ".concat(String.format("%d", FPS)).concat(" as per config file"));

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
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
