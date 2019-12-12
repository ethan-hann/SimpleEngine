package com.kelvin101.engine.collision;

import com.kelvin101.engine.util.Dimension;

public class AABB extends Bounds
{
    public AABB(Dimension hitBox)
    {
        super(hitBox);
    }

    @Override
    public boolean isColliding(Bounds other)
    {
        /*Dimension a = getHitBox();
        Dimension b = other.getHitBox();
        return (a.getWidth() < b.getWidth() + b.)*/
        return true;
    }
}
