package com.kelvin101.engine.base;

import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.gamestates.State;
import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

import java.awt.*;

/**
 * Abstract class which defines the com.kelvin101.engine.base characteristics of a GameObject.
 * All <b>GameObject</b>'s have a position, a dimension (width and height),
 * and a bounding box
 * Nearly all game entities will need to extend this class.
 */
public abstract class GameObject
{
    private Vector2f position;
    private Dimension size;
    private Bounds bounds;
    private State parent;

    public GameObject(Vector2f position, Dimension size, Bounds bounds)
    {
        this.position = position;
        this.size = size;
        this.bounds = bounds;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g2d);

    public Vector2f getPosition()
    {
        return position;
    }

    public Dimension getSize()
    {
        return size;
    }

    public Bounds getBounds()
    {
        return bounds;
    }
}
