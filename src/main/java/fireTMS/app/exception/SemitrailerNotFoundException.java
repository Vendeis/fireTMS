package fireTMS.app.exception;

public class SemitrailerNotFoundException extends RuntimeException {

    public SemitrailerNotFoundException(String id){
        super("Semitrailer with registration number " + id + " was not found");
    }
}
