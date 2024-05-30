package exercise.controller;

import exercise.service.UserCore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.dto.UserDTO;
import exercise.dto.UserCreateDTO;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCore userCore;

    @GetMapping
    public List<UserDTO> index() {
        return userCore.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable long id) {
        return userCore.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO userData) {
        return userCore.createUser(userData);
    }
}
