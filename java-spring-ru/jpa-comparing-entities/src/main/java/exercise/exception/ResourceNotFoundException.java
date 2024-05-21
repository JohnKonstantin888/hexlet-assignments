package exercise.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }
}
