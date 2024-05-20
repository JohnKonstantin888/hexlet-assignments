package exercise;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class Application {
    private final UserProperties userProperties;

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @GetMapping("/admins")
    public List<String> findAdminUsers() {
        return users.stream()
                .filter(user -> userProperties.getAdmins().contains(user.getEmail()))
                .map(User::getName)
                .sorted()
                .toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
