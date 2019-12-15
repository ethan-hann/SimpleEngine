package com.kelvin101.engine.gamestates;

import com.kelvin101.engine.base.GameObject;

import java.awt.*;
import java.util.HashMap;

public abstract class State
{
    private String stateName;
    private HashMap<String, GameObject> stateObjects = new HashMap<>();

    public State(String stateName)
    {
        this.stateName = stateName;
        StateManager.getInstance().getStates().put(stateName, this);
    }

    public HashMap<String, GameObject> getStateObjects()
    {
        return stateObjects;
    }

    public String getStateName()
    {
        return stateName;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g2d);
}
