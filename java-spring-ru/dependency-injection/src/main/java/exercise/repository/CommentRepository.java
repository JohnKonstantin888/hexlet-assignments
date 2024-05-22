package exercise.repository;

import exercise.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import exercise.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByPost(Post post);
}
