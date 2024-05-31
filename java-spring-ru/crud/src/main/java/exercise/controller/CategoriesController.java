package exercise.controller;

import exercise.dto.CategoryDTO;
import exercise.service.CategoryCore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.dto.CategoryCreateDTO;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryCore categoryCore;

    @GetMapping
    public List<CategoryDTO> index() {
        return categoryCore.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO show(@PathVariable long id) {
        return categoryCore.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@Valid @RequestBody CategoryCreateDTO categoryData) {
        return categoryCore.createCategory(categoryData);
    }
}
