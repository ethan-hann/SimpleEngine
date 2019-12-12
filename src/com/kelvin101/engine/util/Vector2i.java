package com.kelvin101.engine.util;
import java.lang.Math;


/**
 * Utility class for integer Vector operations
 */
public class Vector2i
{
    private int x;
    private int y;

    public Vector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2i()
    {
        x = 0;
        y = 0;
    }

    public Vector2i normalize()
    {
        Vector2i newV = new Vector2i(this);
        newV.x = Math.abs(newV.x);
        newV.y = Math.abs(newV.y);

        //ratio
        float ratio = (1.0f / Math.max(newV.x, newV.y));
        ratio = ratio * (1.29289f - (newV.x + newV.y) * ratio * 0.29289f);

        newV.x *= ratio;
        newV.y *= ratio;
        return newV;
    }

    /**
     * Calculates the direction this vector is headed in radians.
     * @return <code>float</code> the radian angle direction of the vector
     */
    public float direction()
    {
        return (float) Math.atan2(y, x);
    }

    /**
     * Calculates the magnitude (length) of the vector.
     * @return <code>float</code> the length of the vector
     */
    public float length()
    {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void add(Vector2i v)
    {
        x += v.x;
        y += v.y;
    }

    public void add(int x, int y)
    {
        this.x += x;
        this.y += y;
    }

    public void subtract(Vector2i v)
    {
        x -= v.x;
        y -= v.y;
    }

    public void subtract(int x, int y)
    {
        this.x -= x;
        this.y -= y;
    }

    public void multiply(int amt)
    {
        x *= amt;
        y *= amt;
    }

    public void divide(int amt)
    {
        if (amt == 0) return;
        x /= amt;
        y /= amt;
    }

    /**
     * Calculates the dot product between <b>this</b> vector and the
     * passed in Vector.
     * @param v another <code>Vector2i</code>
     * @return float representing the dot product
     */
    public float dotP(Vector2i v)
    {
        return (x * v.x) + (y * v.y);
    }

    /**
     * Calculates the angle (in radians) between two vectors.
     * @param v another <code>Vector2i</code>
     * @return float representing the radian angle between the vectors
     */
    public float angleBetween(Vector2i v)
    {
        float thisLength = length();
        float otherLength = v.length();
        float dotProduct = dotP(v);
        return (float) Math.cos(dotProduct / (thisLength * otherLength));
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
