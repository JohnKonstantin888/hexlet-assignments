package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import exercise.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
}
