package exercise.exception;

public class TaskHasAlreadyExistException extends RuntimeException {
    public TaskHasAlreadyExistException(String title) {
        super("Task with title " + title + " already exists");
    }
}
