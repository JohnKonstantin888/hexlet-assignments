package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.AuthorNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    // BEGIN
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO findAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::map)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Transactional
    public AuthorDTO createAuthor(AuthorCreateDTO createDTO) {
        return authorMapper.map(
                authorRepository.save(authorMapper.map(createDTO))
        );
    }

    @Transactional
    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO updateDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorMapper.update(updateDTO, author);
        return authorMapper.map(author);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
