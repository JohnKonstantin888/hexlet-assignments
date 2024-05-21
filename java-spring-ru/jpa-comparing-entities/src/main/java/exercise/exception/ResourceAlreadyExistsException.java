package exercise.exception;

// BEGIN
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String title, int price) {
        super("Товар с описанием = '" + title + "' и ценой = '" + price + "' уже существует");
    }
}
// END
