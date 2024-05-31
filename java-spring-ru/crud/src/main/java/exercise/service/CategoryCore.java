package exercise.service;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.exception.CategoryNotFoundException;
import exercise.mapper.CategoryMapper;
import exercise.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCore {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::map)
                .toList();
    }

    public CategoryDTO findCategoryById(Long id) {
        return categoryMapper.map(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException(id))
        );
    }

    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO createDTO) {
        return categoryMapper.map(
                categoryRepository.save(categoryMapper.map(createDTO))
        );
    }
}
