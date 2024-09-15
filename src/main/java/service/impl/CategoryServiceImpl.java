package service.impl;

import dto.CategoryDTO;
import entity.Category;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(String id) {
        UUID categoryId = parseUUID(id);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return category.toDTO();
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = Category.fromDTO(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.toDTO();
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(UUID.fromString(id));
    }

    private UUID parseUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid UUID format for id: " + id);
        }
    }
}
