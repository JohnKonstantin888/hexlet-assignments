package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN

    @GetMapping("/posts/{id}")
    public Post findById(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id,
                       @RequestBody Post post) {
        Optional<Post> mayBePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (mayBePost.isPresent()) {
            var updatedPost = mayBePost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setBody(post.getBody());
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        int end = Math.min(page * limit, posts.size());
        int start = limit < posts.size() ? (page > 1 ? (((page - 1) * limit) < posts.size() ? ((page - 1) * limit) : 0) : 0) : 0;
        return posts.subList(start, end);
    }
    // END
}
