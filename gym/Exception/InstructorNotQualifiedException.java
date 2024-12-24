package gym.Exception;

public class InstructorNotQualifiedException extends RuntimeException {
    public InstructorNotQualifiedException(String message) {
        super(message);
    }

    public String getMessage()
    {
        return "Error: Instructor is not qualified to conduct this session type.";
    }
}
