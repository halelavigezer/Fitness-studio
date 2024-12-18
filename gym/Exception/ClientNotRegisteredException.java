package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException(String message) {
        super(message);
    }
    public String getMessage(){
        String s = "Client Not Registered Exception";
        return s;
    }
}
