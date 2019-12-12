package com.kelvin101.engine.animation;

import java.util.HashMap;

/**
 * Animation handler class to store all animations for the game.
 * An animation is defined by the {@link Animation} class.
 */
public class AnimationHandler
{
    private static HashMap<String, Animation> animations;

    private static AnimationHandler instance;

    public static AnimationHandler getInstance()
    {
        if (instance == null)
        {
            instance = new AnimationHandler();
            animations = new HashMap<>();
        }
        return instance;
    }

    public HashMap<String, Animation> getAnimations()
    {
        return animations;
    }
}
