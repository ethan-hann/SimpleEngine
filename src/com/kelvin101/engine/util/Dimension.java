package com.kelvin101.engine.util;

public class Dimension
{
    private Vector2i dim;

    public Dimension(int width, int height)
    {
        this.dim = new Vector2i(width, height);
    }

    public int getWidth()
    {
        return dim.getX();
    }

    public int getHeight()
    {
        return dim.getY();
    }

    public void setWidth(int w)
    {
        dim.setX(w);
    }

    public void setHeight(int h)
    {
        dim.setY(h);
    }
}
