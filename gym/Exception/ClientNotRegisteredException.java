package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    private String additionalMessage;
    public ClientNotRegisteredException(String message) {
        super(message);
        this.additionalMessage=message;
    }
    public String getMessage(){
        return this.additionalMessage;
    }
}
