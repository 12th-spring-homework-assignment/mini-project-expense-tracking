package org.example.miniprojectexpensetracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectexpensetracking.configuration.UuidTypeHandler;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.dto.request.CategoryRequest;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CategoryRepository {

    @Select("INSERT INTO categories (name, description, user_id) VALUES (#{category.name}, #{category.description}, #{userId}) RETURNING *")
    @Results(id = "categoryMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.example.miniprojectexpensetracking.repository.AppUserRepository.findUserById"))
    })
    Category createCategory(@Param("category") CategoryRequest categoryRequest, UUID userId);

    @Select("SELECT * FROM categories WHERE user_id = #{userId} LIMIT #{limit} OFFSET #{offset}")
    @ResultMap("categoryMapper")
    List<Category> findAllCategories(Integer limit, Integer offset, UUID userId);

    @Select("SELECT * FROM categories WHERE category_id = #{categoryId} AND user_id = #{userId}")
    @ResultMap("categoryMapper")
    Category findCategoryById(UUID categoryId, UUID userId);

    @Delete("DELETE FROM categories WHERE category_id = #{categoryId} AND user_id = #{userId}")
    void removeCategory(UUID categoryId, UUID userId);

    @Select("UPDATE categories SET name = #{category.name}, description = #{category.description} WHERE category_id = #{categoryId} AND user_id = #{userId} RETURNING *")
    @ResultMap("categoryMapper")
    Category updateCategory(UUID categoryId, @Param("category")  CategoryRequest categoryRequest, UUID userId);

    @Select("SELECT category_id, name, description FROM categories WHERE category_id = #{categoryId}")
    @ResultMap("categoryMapper")
    Category findCategoryByCategoryId(UUID categoryId);
}
