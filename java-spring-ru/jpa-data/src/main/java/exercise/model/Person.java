package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.AUTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// BEGIN
@Entity
@Table(name = "PERSONS")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
}
// END
