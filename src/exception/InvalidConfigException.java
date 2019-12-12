package exception;

/**
 * Exception thrown when an invalid configuration option is detected.
 */
public class InvalidConfigException extends Exception
{
    public InvalidConfigException(String msg)
    {
        super(msg);
    }
}
