package exercise.controller;

import exercise.exception.PostNotFoundException;
import exercise.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.repository.PostRepository;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostRepository postRepository;

    @GetMapping
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable long id,
                                 @RequestBody Post postData) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return postRepository.save(
                post
                        .setTitle(postData.getTitle())
                        .setBody(postData.getBody())
        );
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        postRepository.deleteById(id);
    }
}
// END
