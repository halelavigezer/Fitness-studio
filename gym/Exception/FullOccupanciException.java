package gym.Exception;

public class FullOccupanciException extends RuntimeException {
    public FullOccupanciException(String message){
        super(message);
    }
    public String getMessage(){
        String s = "FullOccupanciException";
        return s;
    }
}
