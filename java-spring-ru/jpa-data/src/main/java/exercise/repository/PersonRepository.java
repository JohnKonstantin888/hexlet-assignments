package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercise.model.Person;

import java.util.UUID;

// BEGIN
public interface PersonRepository extends JpaRepository<Person, UUID> {
}
// END
