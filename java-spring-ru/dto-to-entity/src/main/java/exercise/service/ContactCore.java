package exercise.service;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactCore {
    private final ContactRepository contactRepository;

    @Transactional
    public ContactDTO save(ContactCreateDTO createDTO) {
        return buildContactDTO(contactRepository.save(new Contact()
                .setFirstName(createDTO.getFirstName())
                .setLastName(createDTO.getLastName())
                .setPhone(createDTO.getPhone())));

    }

    private ContactDTO buildContactDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}
