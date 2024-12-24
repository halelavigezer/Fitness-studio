package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException(String message) {
        super(message);
    }
    public String getMessage(){
        String s = "Error: Registration is required before attempting to unregister";
        return s;
    }
}
