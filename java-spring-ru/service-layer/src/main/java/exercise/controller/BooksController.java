package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    // BEGIN
//    GET /books – просмотр списка всех книг
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
//    GET /books/{id} – просмотр конкретной книги
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }
//    POST /books – добавление новой книги. При указании id несуществующего автора должен вернуться ответ с кодом 400 Bad request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        return bookService.createBook(bookCreateDTO);
    }
//    PUT /books/{id} – редактирование книги. При редактировании мы должны иметь возможность поменять название и автора. При указании id несуществующего автора также должен вернуться ответ с кодом 400 Bad request
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        return bookService.updateBook(id, bookUpdateDTO);
    }
//    DELETE /books/{id} – удаление книги
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    // END
}
