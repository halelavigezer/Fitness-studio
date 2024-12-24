package gym.Exception;

public class InstructorNotQualifiedException extends RuntimeException {
    private String message;
    public InstructorNotQualifiedException(String message) {
        super(message);
        this.message=message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
