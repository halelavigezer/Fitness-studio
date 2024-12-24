package gym.Exception;

public class DuplicateClientException extends RuntimeException{
    private String message;
    public DuplicateClientException(String message){
        super(message);
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
