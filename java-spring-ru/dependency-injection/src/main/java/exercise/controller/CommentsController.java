package exercise.controller;

import exercise.exception.CommentNotFoundException;
import exercise.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.repository.CommentRepository;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentRepository commentRepository;

    @GetMapping
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable long id,
                                 @RequestBody Comment commentData) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        return commentRepository.save(
                comment
                        .setBody(commentData.getBody())
                        .setPost(commentData.getPost())
        );
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
