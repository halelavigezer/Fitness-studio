package gym.Exception;

public class DuplicateClientException extends RuntimeException{
    public DuplicateClientException(String message){
        super(message);
    }
    public String getMessage(){
        String s = "DuplicateClientException";
        return s;
    }
}
