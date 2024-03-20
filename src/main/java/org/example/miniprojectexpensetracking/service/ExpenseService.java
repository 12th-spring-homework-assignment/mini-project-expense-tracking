package org.example.miniprojectexpensetracking.service;

import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.Expense;
import org.example.miniprojectexpensetracking.model.dto.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(ExpenseRequest expenseRequest);

    List<Category> findAllExpenses(Integer limit, Integer offset, String sortBy, Boolean orderBy);

    Expense findExpenseById(Integer expenseId);

    void removeExpense(Integer expenseId);

    Expense updateExpense(Integer expenseId, ExpenseRequest expenseRequest);
}
