package graphics;

import config.Config;

import javax.swing.*;
import java.awt.*;

/**
 * Handles initializing the JFrame and Canvas for the Game to run in.
 * Gets its options from the configuration file that is accessed via {@link Config#getOptions()}
 */
public class Display
{
    private static Display instance = null;

    private JFrame frame;
    private Canvas canvas;

    private Display()
    {
        initDisplay();
    }

    public static Display getInstance()
    {
        if (instance == null)
        {
            instance = new Display();
        }
        return instance;
    }

    private void initDisplay()
    {
        String title = Config.getInstance().getOptions().get("title");
        int width = Integer.parseInt(Config.getInstance().getOptions().get("window_width"));
        int height = Integer.parseInt(Config.getInstance().getOptions().get("window_height"));
        boolean resizable = Boolean.parseBoolean(Config.getInstance().getOptions().get("resizable"));

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(resizable);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Adding a canvas to the JFrame to draw graphics
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
}
