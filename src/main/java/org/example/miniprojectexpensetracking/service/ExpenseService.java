package org.example.miniprojectexpensetracking.service;

import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.Expense;
import org.example.miniprojectexpensetracking.model.dto.request.ExpenseRequest;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    Expense createExpense(ExpenseRequest expenseRequest);

    List<Category> findAllExpenses(Integer limit, Integer offset, String sortBy, Boolean orderBy);

    Expense findExpenseById(UUID expenseId);

    void removeExpense(UUID expenseId);

    Expense updateExpense(UUID expenseId, ExpenseRequest expenseRequest);
}
