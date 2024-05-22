package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exercise.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
