package com.kelvin101.engine.animation;

import java.util.HashMap;

/**
 * Animation handler class to store all animations for the game.
 * An animation is defined by the {@link Animation} class.
 */
public class AnimationManager
{
    private static HashMap<String, Animation> animations;

    private static AnimationManager instance;

    public static AnimationManager getInstance()
    {
        if (instance == null)
        {
            instance = new AnimationManager();
            animations = new HashMap<>();
        }
        return instance;
    }

    public HashMap<String, Animation> getAnimations()
    {
        return animations;
    }
}
