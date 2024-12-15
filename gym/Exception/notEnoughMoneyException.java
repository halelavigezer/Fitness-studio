package gym.Exception;

public class notEnoughMoneyException extends RuntimeException{
    public notEnoughMoneyException(String message) {
        super(message);
    }
    public String getMessage()
    {
        return "notEnoughMoneyException";
    }
}
