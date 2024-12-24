package gym.Exception;

public class InvalidAgeException extends RuntimeException
{
    public InvalidAgeException(String message)
    {
        super(message);
    }
    public String getMessage()
    {
        return "Error: Client must be at least 18 years old to register";
    }
}
