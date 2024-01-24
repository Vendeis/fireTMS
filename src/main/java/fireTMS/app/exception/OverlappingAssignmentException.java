package fireTMS.app.exception;

public class OverlappingAssignmentException extends RuntimeException {
    public OverlappingAssignmentException() {
        super("This assignment would overlap with assignment that already exists");
    }
}
