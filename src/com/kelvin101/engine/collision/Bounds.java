package com.kelvin101.engine.collision;

import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

public abstract class Bounds
{
    private Dimension hitBox;
    private Vector2f pos;

    public Bounds(Vector2f pos, Dimension hitBox)
    {
        this.pos = pos;
        this.hitBox = hitBox;
    }

    public Vector2f getPos()
    {
        return pos;
    }

    public Dimension getHitBox()
    {
        return hitBox;
    }

    public abstract boolean isColliding(Bounds other);
}
