package gym.Exception;

public class InstructorNotQualifiedException extends RuntimeException {
    public InstructorNotQualifiedException(String message) {
        super(message);
    }
    public String getMessage()
    {
        return "Instructor Not Qualified Exception";
    }
}
