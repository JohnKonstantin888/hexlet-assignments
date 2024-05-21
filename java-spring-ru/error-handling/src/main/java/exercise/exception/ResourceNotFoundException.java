package exercise.exception;

// BEGIN
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(long id) {
        super("Product with id " + id + " not found");
    }
}
// END
