package exercise.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Task with id " + id + " not found");
    }
}
