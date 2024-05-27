package exercise.service;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.PostNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCore {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostDTO findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return buildPostDTO(post);
    }

    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::buildPostDTO)
                .toList();
    }

    private PostDTO buildPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .comments(
                        commentRepository.findByPostId(post.getId()).stream()
                                .map(this::buildCommentDTO)
                                .toList()
                )
                .build();
    }

    private CommentDTO buildCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .build();
    }
}
