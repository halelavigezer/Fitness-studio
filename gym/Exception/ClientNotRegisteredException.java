package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException(String message)
    {
        super(message);
    }
    public String getMessage(){
        String s = "ClientNotRegisteredException";
        return s;
    }
}