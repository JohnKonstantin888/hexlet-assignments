package exercise.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(long id) {
        super("Author with id " + id + " not found");
    }
}
