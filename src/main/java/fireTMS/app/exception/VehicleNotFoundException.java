package fireTMS.app.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String id) {
        super("Vehicle with registration number " + id + " was not found");
    }
}
