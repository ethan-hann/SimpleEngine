import base.Game;
import util.Vector2f;

public class Launcher
{
    public static void main(String[] args)
    {
        Game.getInstance();
        Vector2f v = new Vector2f(500.6f, 254.5f);
        Vector2f other = new Vector2f(5, 10);
        System.out.println(v.length());
        System.out.println(other.length());
        System.out.println(v.angleBetween(other));
    }
}
