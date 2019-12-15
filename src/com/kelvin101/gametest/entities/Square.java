package com.kelvin101.gametest.entities;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

import java.awt.*;

public class Square extends GameObject
{
    public Square(Vector2f position, Dimension size, Bounds bounds)
    {
        super(position, size, bounds);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.RED);
        g2d.fillRect((int) getPosition().getX(), (int) getPosition().getY(),
                getSize().getWidth(), getSize().getHeight());
    }
}
