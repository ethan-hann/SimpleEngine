package com.kelvin101.engine.collision;

import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

public class AABB extends Bounds
{
    public AABB(Vector2f pos, Dimension hitBox)
    {
        super(pos, hitBox);
    }

    @Override
    public boolean isColliding(Bounds other)
    {
        Dimension a = getHitBox();
        Vector2f aPos = getPos();
        Dimension b = other.getHitBox();
        Vector2f bPos = other.getPos();
        return (aPos.getX() < bPos.getX() + b.getWidth()) &&
                (aPos.getX() + a.getWidth() > bPos.getX()) &&
                (aPos.getY() < bPos.getY() + b.getHeight()) &&
                (aPos.getY() + a.getHeight() > bPos.getY());
    }
}
