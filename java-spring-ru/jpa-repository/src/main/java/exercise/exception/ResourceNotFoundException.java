package exercise.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(long id) {
        super("Product with id " + id + " not found");
    }
}
