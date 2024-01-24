package fireTMS.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({VehicleAlreadyExistsException.class, SemitrailerAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExistsException(RuntimeException e, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(),
                request.getDescription(false), ZonedDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OverlappingAssignmentException.class, InvalidDateException.class})
    public ResponseEntity<Object> handleOverlappingException(RuntimeException e, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(),
                request.getDescription(false), ZonedDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({VehicleNotFoundException.class, SemitrailerNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(),
                request.getDescription(false), ZonedDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }


}