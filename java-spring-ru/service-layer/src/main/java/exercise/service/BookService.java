package exercise.service;

import exercise.dto.*;
import exercise.exception.BookNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper;
    // BEGIN
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::map)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public BookDTO createBook(BookCreateDTO createDTO) {
        Book book = bookMapper.map(createDTO);
        Author author = authorRepository.findById(createDTO.getAuthorId()).orElseThrow(() -> new ConstraintViolationException(Set.of()));
        book.setAuthor(author);
        return bookMapper.map(
                bookRepository.save(book)
        );
    }

    @Transactional
    public BookDTO updateBook(Long id, BookUpdateDTO updateDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorRepository.findById(updateDTO.getAuthorId().get()).orElseThrow(() -> new ConstraintViolationException(Set.of()));
        bookMapper.update(updateDTO, book);
        book.setAuthor(author);
        return bookMapper.map(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
