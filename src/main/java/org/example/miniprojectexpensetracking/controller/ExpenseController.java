package org.example.miniprojectexpensetracking.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.Expense;
import org.example.miniprojectexpensetracking.model.dto.request.ExpenseRequest;
import org.example.miniprojectexpensetracking.model.dto.response.APIResponse;
import org.example.miniprojectexpensetracking.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/expense")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
        APIResponse<Expense> response = APIResponse.<Expense>builder()
                .message("The expense has been successfully created.")
                .payload(expenseService.createExpense(expenseRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAllExpenses(
            @Positive(message = "Offset cannot be negative or zero") @RequestParam(defaultValue = "1") Integer offset,
            @Positive(message = "Limit cannot be negative or zero") @RequestParam(defaultValue = "5") Integer limit,
            @RequestParam(defaultValue = "expense_id") String sortBy,
            @RequestParam(defaultValue = "false") Boolean orderBy
            ){
        APIResponse<List<Category>> response = APIResponse.<List<Category>>builder()
                .message("All categories have been successfully founded.")
                .payload(expenseService.findAllExpenses(limit, offset, sortBy, orderBy))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findExpenseById(@PathVariable("id") UUID expenseId){
        APIResponse<Expense> response = APIResponse.<Expense>builder()
                .message("The expense has been successfully founded.")
                .payload(expenseService.findExpenseById(expenseId))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeExpense(@PathVariable("id") UUID expenseId){
        expenseService.removeExpense(expenseId);
        APIResponse<String> response = APIResponse.<String>builder()
                .message("The expense has been successfully removed.")
                .payload(null)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable("id") UUID expenseId, @RequestBody @Valid ExpenseRequest expenseRequest){
        APIResponse<Expense> response = APIResponse.<Expense>builder()
                .message("The expense has been successfully updated.")
                .payload(expenseService.updateExpense(expenseId, expenseRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
