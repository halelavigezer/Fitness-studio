package gym.Exception;

public class InvalidAgeException extends RuntimeException
{
    public InvalidAgeException(String message)
    {
        super(message);
    }
    public String getMessage()
    {
        return "InvalidAgeException";
    }
}
