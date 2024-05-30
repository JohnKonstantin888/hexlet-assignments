package exercise.controller;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.service.TaskCore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {
    private final TaskCore taskCore;
    // BEGIN
//    GET /tasks – просмотр списка всех задач
    @GetMapping
    public List<TaskDTO> findAllTasks() {
        return taskCore.findAllTasks();
    }
//    GET /tasks/{id} – просмотр конкретной задачи
    @GetMapping("/{id}")
    public TaskDTO findTaskById(@PathVariable Long id) {
        return taskCore.findTaskById(id);
    }
//    POST /tasks – создание новой задачи
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody TaskCreateDTO createDTO) {
        return taskCore.createTask(createDTO);
    }
//    PUT /tasks/{id} – редактирование задачи. При редактировании мы должны иметь возможность поменять название, описание задачи и ответственного разработчика
    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO updateDTO) {
        return taskCore.updateTask(id, updateDTO);
    }
//    DELETE /tasks/{id} – удаление задачи
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskCore.deleteTask(id);
    }
    // END
}
