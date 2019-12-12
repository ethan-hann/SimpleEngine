package physics.collision;

import base.GameObject;
import util.Dimension;

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
