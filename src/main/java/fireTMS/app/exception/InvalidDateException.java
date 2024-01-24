package fireTMS.app.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("startDate should be after endDate");
    }
}
