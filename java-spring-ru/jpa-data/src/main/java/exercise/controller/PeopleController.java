package exercise.controller;

import exercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable UUID id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping()
    public List<Person> finaAll() {
        return personRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        personRepository.deleteById(id);
    }
    // END
}
