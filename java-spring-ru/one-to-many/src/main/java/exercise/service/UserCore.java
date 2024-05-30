package exercise.service;

import exercise.dto.UserCreateDTO;
import exercise.dto.UserDTO;
import exercise.exception.UserNotFoundException;
import exercise.mapper.UserMapper;
import exercise.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCore {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO findUserById(Long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        return userMapper.map(
                userRepository.save(userMapper.map(userCreateDTO))
        );
    }
}
