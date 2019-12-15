package com.kelvin101.ui;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String TITLE = "2D Engine - Welcome";

    private JButton newProjectButton = new JButton("Create a new project...");
    private JButton openProjectButton = new JButton("Open a project...");
    private JButton exitButton = new JButton("Exit Engine");

    public WelcomeScreen setup()
    {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.getContentPane().add(BorderLayout.WEST, newProjectButton);
        this.getContentPane().add(BorderLayout.EAST, openProjectButton);
        this.getContentPane().add(BorderLayout.SOUTH, exitButton);
        this.setVisible(false);
        /*this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));*/

        return this;
    }

    public void display()
    {
        this.setVisible(true);
    }
}
