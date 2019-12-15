package com.kelvin101.gametest.entities;

import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.gamestates.StateManager;
import com.kelvin101.engine.input.InputManager;
import com.kelvin101.engine.logger.Logger;
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

        if (InputManager.getInstance().isKeyPressed("up_key"))
        {
            ySpeed = -baseSpeed;
        }
        if (InputManager.getInstance().isKeyPressed("down_key"))
        {
            ySpeed = baseSpeed;
        }
        if (InputManager.getInstance().isKeyPressed("left_key"))
        {
            xSpeed = -baseSpeed;
        }
        if (InputManager.getInstance().isKeyPressed("right_key"))
        {
            xSpeed = baseSpeed;
        }
        if (InputManager.getInstance().isMousePressed("left_mouse"))
        {
            Logger.getInstance().write("Left mouse button pressed!");
        }
        if (InputManager.getInstance().isMousePressed("right_mouse"))
        {
            Logger.getInstance().write("Right mouse button pressed!");
        }
    }

    private void move()
    {
        if (getBounds().isColliding(StateManager.getInstance().getCurrentState()
                .getStateObjects().get("obstacle").getBounds()))
        {
            Logger.getInstance().write("COLLISION!");
        }
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
