package org.example.miniprojectexpensetracking.service;

import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryRequest);

    List<Category> findAllCategories(Integer limit, Integer offset);

    Category findCategoryById(Integer categoryId);

    void removeCategory(Integer categoryId);

    Category updateCategory(Integer categoryId, CategoryRequest categoryRequest);
}
