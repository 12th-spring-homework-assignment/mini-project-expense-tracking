package org.example.miniprojectexpensetracking.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.dto.request.CategoryRequest;
import org.example.miniprojectexpensetracking.model.dto.response.APIResponse;
import org.example.miniprojectexpensetracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        APIResponse<Category> response = APIResponse.<Category>builder()
                .message("The category has been successfully created.")
                .payload(categoryService.createCategory(categoryRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategories(@Positive(message = "Offset cannot be negative or zero") @RequestParam(defaultValue = "1") Integer offset, @Positive(message = "Limit cannot be negative or zero") @RequestParam(defaultValue = "5") Integer limit){
        APIResponse<List<Category>> response = APIResponse.<List<Category>>builder()
                .message("All categories have been successfully founded.")
                .payload(categoryService.findAllCategories(limit, offset))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable("id") UUID categoryId){
        APIResponse<Category> response = APIResponse.<Category>builder()
                .message("The category has been successfully founded.")
                .payload(categoryService.findCategoryById(categoryId))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable("id") UUID categoryId){
        categoryService.removeCategory(categoryId);
        APIResponse<String> response = APIResponse.<String>builder()
                .message("The category has been successfully removed.")
                .payload(null)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") UUID categoryId, @RequestBody @Valid CategoryRequest categoryRequest){
        APIResponse<Category> response = APIResponse.<Category>builder()
                .message("The category has been successfully updated.")
                .payload(categoryService.updateCategory(categoryId, categoryRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
