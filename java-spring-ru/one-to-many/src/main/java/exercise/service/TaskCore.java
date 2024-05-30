package exercise.service;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.TaskNotFoundException;
import exercise.exception.UserNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCore {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .toList();
    }

    public TaskDTO findTaskById(Long id) {
        return taskMapper.map(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)));
    }

    @Transactional
    public TaskDTO createTask(TaskCreateDTO createDTO) {
        return taskMapper.map(
                taskRepository.save(taskMapper.map(createDTO))
        );
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskUpdateDTO updateDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        User user = userRepository.findById(updateDTO.getAssigneeId()).orElseThrow(() -> new UserNotFoundException(updateDTO.getAssigneeId()));
        taskMapper.update(updateDTO, task);
        task.setAssignee(user);
        return taskMapper.map(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
