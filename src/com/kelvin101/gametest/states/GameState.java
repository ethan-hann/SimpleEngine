package com.kelvin101.gametest.states;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.gamestates.State;

import java.awt.*;

public class GameState extends State
{
    public GameState(String stateName)
    {
        super(stateName);
    }

    @Override
    public void tick()
    {
        for (GameObject g : getStateObjects().values())
        {
            g.tick();
        }
    }

    @Override
    public void render(Graphics2D g2d)
    {
        for (GameObject g : getStateObjects().values())
        {
            g.render(g2d);
        }
    }
}
