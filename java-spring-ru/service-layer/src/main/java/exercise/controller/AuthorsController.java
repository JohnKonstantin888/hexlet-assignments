package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorService authorService;

    // BEGIN
//    GET /authors – просмотр списка всех авторов
    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }
//    GET /authors/{id} – просмотр конкретного автора
    @GetMapping("/{id}")
    public AuthorDTO findAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }
//    POST /authors – добавление нового автора
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        return authorService.createAuthor(authorCreateDTO);
    }
//    PUT /authors/{id} – редактирование автора. При редактировании мы должны иметь возможность поменять имя и фамилию
    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorUpdateDTO authorUpdateDTO) {
        return authorService.updateAuthor(id, authorUpdateDTO);
    }
//    DELETE /authors – удаление автора
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
    // END
}
