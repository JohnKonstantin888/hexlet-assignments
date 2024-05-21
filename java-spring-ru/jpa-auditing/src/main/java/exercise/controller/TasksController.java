package exercise.controller;

import exercise.exception.TaskHasAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id,
                           @RequestBody Task taskData) {
        Optional<Task> optional = taskRepository.findByTitle(taskData.getTitle());
        if (optional.isPresent()) {
            throw new TaskHasAlreadyExistException(taskData.getTitle());
        }
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return taskRepository.save(
                task
                        .setTitle(taskData.getTitle())
                        .setDescription(taskData.getDescription())
        );
    }
    // END

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
