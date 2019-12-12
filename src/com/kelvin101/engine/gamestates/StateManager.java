package com.kelvin101.engine.gamestates;

import java.util.HashMap;

public class StateManager
{
    private static HashMap<String, State> states;
    private State currentState = null;

    private static StateManager instance;

    public static StateManager getInstance()
    {
        if (instance == null)
        {
            instance = new StateManager();
            states = new HashMap<>();
        }
        return instance;
    }

    public HashMap<String, State> getStates()
    {
        return states;
    }

    public State getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(State currentState)
    {
        this.currentState = currentState;
    }
}
