package com.kelvin101.engine.collision;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.util.Dimension;

public abstract class Bounds
{
    private Dimension hitBox;

    public Bounds(Dimension hitBox)
    {
        this.hitBox = hitBox;
    }

    public abstract boolean isColliding(Bounds other);

    public Dimension getHitBox()
    {
        return hitBox;
    }
}
