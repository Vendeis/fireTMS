package fireTMS.app.exception;

public class VehicleAlreadyExistsException extends RuntimeException {
    public VehicleAlreadyExistsException(String id) {
        super("Vehicle with registration number " + id + " already exists");
    }
}
