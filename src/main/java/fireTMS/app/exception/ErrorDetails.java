package fireTMS.app.exception;

import java.time.ZonedDateTime;

public class ErrorDetails {
    private final String message;
    private final String details;
    private final ZonedDateTime timestamp;

    public ErrorDetails(String message, String details, ZonedDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
