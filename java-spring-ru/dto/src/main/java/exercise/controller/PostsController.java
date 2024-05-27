package exercise.controller;

import exercise.dto.PostDTO;
import exercise.service.PostCore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostCore postCore;

    @GetMapping
    public List<PostDTO> findAll() {
        return postCore.findAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO findPostById(@PathVariable Long id) {
        return postCore.findPostById(id);
    }
}
// END
