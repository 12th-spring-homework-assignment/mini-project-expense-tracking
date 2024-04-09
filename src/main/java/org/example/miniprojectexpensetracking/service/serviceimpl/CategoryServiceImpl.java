package org.example.miniprojectexpensetracking.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.exception.NotFoundException;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.dto.request.CategoryRequest;
import org.example.miniprojectexpensetracking.repository.CategoryRepository;
import org.example.miniprojectexpensetracking.service.AuthService;
import org.example.miniprojectexpensetracking.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AuthService authService;

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        UUID userId = authService.findCurrentUser();
        return categoryRepository.createCategory(categoryRequest, userId);
    }

    @Override
    public List<Category> findAllCategories(Integer limit, Integer offset) {
        offset = (offset - 1) * limit;
        UUID userId = authService.findCurrentUser();
        return categoryRepository.findAllCategories(limit, offset, userId);
    }

    @Override
    public Category findCategoryById(UUID categoryId) {
        UUID userId = authService.findCurrentUser();
        Category category = categoryRepository.findCategoryById(categoryId, userId);
        if (category == null){
            throw new NotFoundException("The category id " + categoryId + " has not been founded.");
        }
        return category;
    }

    @Override
    public void removeCategory(UUID categoryId) {
        UUID userId = authService.findCurrentUser();
        findCategoryById(categoryId);
        categoryRepository.removeCategory(categoryId, userId);
    }

    @Override
    public Category updateCategory(UUID categoryId, CategoryRequest categoryRequest) {
        UUID userId = authService.findCurrentUser();
        findCategoryById(categoryId);
        return categoryRepository.updateCategory(categoryId, categoryRequest, userId);
    }
}
