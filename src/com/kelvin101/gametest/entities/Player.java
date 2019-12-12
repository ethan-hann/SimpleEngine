package com.kelvin101.gametest.entities;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

import java.awt.*;

public class Player extends GameObject
{
    public Player(Vector2f position, Dimension size, Bounds bounds)
    {
        super(position, size, bounds);
    }

    @Override
    public void tick()
    {

        getPosition().setX(getPosition().getX() + 5);
    }

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) getPosition().getX(), (int) getPosition().getY(),
                getSize().getWidth(), getSize().getHeight());
    }
}
