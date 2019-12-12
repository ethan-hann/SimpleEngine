package com.kelvin101.gametest.entities;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.input.InputManager;
import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;

import java.awt.*;

public class Player extends GameObject
{
    float baseSpeed = 5;
    float xSpeed;
    float ySpeed;

    public Player(Vector2f position, Dimension size, Bounds bounds)
    {
        super(position, size, bounds);
    }

    private void input()
    {
        xSpeed = 0;
        ySpeed = 0;

        if (InputManager.getInstance().isPressed("up_key"))
        {
            ySpeed = -baseSpeed;
        }
        if (InputManager.getInstance().isPressed("down_key"))
        {
            ySpeed = baseSpeed;
        }
        if (InputManager.getInstance().isPressed("left_key"))
        {
            xSpeed = -baseSpeed;
        }
        if (InputManager.getInstance().isPressed("right_key"))
        {
            xSpeed = baseSpeed;
        }
    }

    private void move()
    {
        getPosition().setX(getPosition().getX() + xSpeed);
        getPosition().setY(getPosition().getY() + ySpeed);
    }

    @Override
    public void tick()
    {
        input();
        move();
    }

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) getPosition().getX(), (int) getPosition().getY(),
                getSize().getWidth(), getSize().getHeight());
    }
}
