package fireTMS.app.exception;

public class SemitrailerAlreadyExistsException extends RuntimeException {
    public SemitrailerAlreadyExistsException(String id) {
        super("Semitrailer with registration number " + id + " already exists");
    }
}
