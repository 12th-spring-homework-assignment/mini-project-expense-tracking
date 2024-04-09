package org.example.miniprojectexpensetracking.service;

import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.dto.request.CategoryRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryRequest);

    List<Category> findAllCategories(Integer limit, Integer offset);

    Category findCategoryById(UUID categoryId);

    void removeCategory(UUID categoryId);

    Category updateCategory(UUID categoryId, CategoryRequest categoryRequest);
}
