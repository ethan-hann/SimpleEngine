package util;
import java.lang.Math;

/**
 * Utility class for floating point Vector operations.
 */
public class Vector2f
{
    private float x;
    private float y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v)
    {
        this.x = v.x;
        this.y = v.y;
    }


    public Vector2f()
    {
        x = 0.0f;
        y = 0.0f;
    }

    /**
     * Normalizes the vector using approximations
     * @return <code>Vector2f</code> a normalized Vector
     */
    public Vector2f normalize()
    {
        Vector2f newV = new Vector2f(this);
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

    public void add(Vector2f v)
    {
        x += v.x;
        y += v.y;
    }

    public void add(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void subtract(Vector2f v)
    {
        x -= v.x;
        y -= v.y;
    }

    public void subtract(float x, float y)
    {
        this.x -= x;
        this.y -= y;
    }

    public void multiply(float amt)
    {
        x *= amt;
        y *= amt;
    }

    public void divide(float amt)
    {
        if (amt == 0) return;
        x /= amt;
        y /= amt;
    }

    /**
     * Calculates the dot product between <b>this</b> vector and the
     * passed in Vector.
     * @param v another <code>Vector2f</code>
     * @return float representing the dot product
     */
    public float dotP(Vector2f v)
    {
        return (x * v.x) + (y * v.y);
    }

    /**
     * Calculates the angle (in radians) between two vectors.
     * @param v another <code>Vector2f</code>
     * @return float representing the radian angle between the vectors
     */
    public float angleBetween(Vector2f v)
    {
        float thisLength = length();
        float otherLength = v.length();
        float dotProduct = dotP(v);
        return (float) Math.cos(dotProduct / (thisLength * otherLength));
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
