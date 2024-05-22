package exercise.controller;

import exercise.daytime.Daytime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
@RequiredArgsConstructor
public class WelcomeController {
    private final Daytime daytime;
    @GetMapping("/welcome")
    public String welcome() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
// END
