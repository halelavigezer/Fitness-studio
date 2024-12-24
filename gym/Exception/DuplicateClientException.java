package gym.Exception;

public class DuplicateClientException extends RuntimeException{
    public DuplicateClientException(String message){
        super(message);
    }
    public String getMessage(){
        String s = "Error: The client is already registered";
        return s;
    }
}
