package org.example.miniprojectexpensetracking.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.exception.NotFoundException;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.Expense;
import org.example.miniprojectexpensetracking.model.dto.request.ExpenseRequest;
import org.example.miniprojectexpensetracking.repository.ExpenseRepository;
import org.example.miniprojectexpensetracking.service.AuthService;
import org.example.miniprojectexpensetracking.service.CategoryService;
import org.example.miniprojectexpensetracking.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final AuthService authService;
    private final CategoryService categoryService;

    @Override
    public Expense createExpense(ExpenseRequest expenseRequest) {
        UUID userId = authService.findCurrentUser();
        categoryService.findCategoryById(expenseRequest.getCategoryId());
        return expenseRepository.createExpense(expenseRequest, userId);
    }

    @Override
    public List<Category> findAllExpenses(Integer limit, Integer offset, String sortBy, Boolean orderBy) {
        UUID userId = authService.findCurrentUser();
        offset = (offset - 1) * limit;
        String order = orderBy ? "DESC" : "ASC";
        return expenseRepository.findAllExpenses(limit, offset, sortBy, order, userId);
    }

    @Override
    public Expense findExpenseById(UUID expenseId) {
        UUID userId = authService.findCurrentUser();
        Expense expense = expenseRepository.findExpenseById(expenseId, userId);
        if (expense == null){
            throw new NotFoundException("The expense id " + expenseId + " has not been founded.");
        }
        return expense;
    }

    @Override
    public void removeExpense(UUID expenseId) {
        UUID userId = authService.findCurrentUser();
        findExpenseById(expenseId);
        expenseRepository.removeExpense(expenseId, userId);
    }

    @Override
    public Expense updateExpense(UUID expenseId, ExpenseRequest expenseRequest) {
        UUID userId = authService.findCurrentUser();
        findExpenseById(expenseId);
        categoryService.findCategoryById(expenseRequest.getCategoryId());
        return expenseRepository.updateExpense(expenseId, expenseRequest, userId);
    }
}
