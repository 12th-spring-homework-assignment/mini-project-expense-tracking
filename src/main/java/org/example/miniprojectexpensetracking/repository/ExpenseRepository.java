package org.example.miniprojectexpensetracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectexpensetracking.model.Category;
import org.example.miniprojectexpensetracking.model.Expense;
import org.example.miniprojectexpensetracking.model.dto.request.ExpenseRequest;

import java.util.List;

@Mapper
public interface ExpenseRepository {

    @Results(id = "expenseMapper", value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.example.miniprojectexpensetracking.repository.AppUserRepository.findUserById")),
            @Result(property = "category", column = "category_id", one = @One(select = "org.example.miniprojectexpensetracking.repository.CategoryRepository.findCategoryByCategoryId"))
    })
    @Select("INSERT INTO expenses (amount, description, date, user_id, category_id) VALUES (#{expense.amount}, #{expense.description}, #{expense.date}, #{userId}, #{expense.categoryId}) RETURNING *")
    Expense createExpense(@Param("expense") ExpenseRequest expenseRequest, Integer userId);

    @ResultMap("expenseMapper")
    @Select("SELECT * FROM expenses WHERE user_id = #{userId} ORDER BY ${sortBy} ${order} LIMIT #{limit} OFFSET #{offset}")
    List<Category> findAllExpenses(Integer limit, Integer offset, String sortBy, String order, Integer userId);

    @ResultMap("expenseMapper")
    @Select("SELECT * FROM expenses WHERE expense_id = #{expenseId} AND user_id = #{userId}")
    Expense findExpenseById(Integer expenseId, Integer userId);

    @Delete("DELETE FROM expenses WHERE expense_id = #{expenseId} AND user_id = #{userId}")
    void removeExpense(Integer expenseId, Integer userId);

    @Select("UPDATE expenses SET amount = #{expense.amount}, description = #{expense.description}, date = #{expense.date}, category_id = #{expense.categoryId} WHERE expense_id = #{expenseId} AND user_id = #{userId} RETURNING *")
    @ResultMap("expenseMapper")
    Expense updateExpense(Integer expenseId, @Param("expense") ExpenseRequest expenseRequest, Integer userId);
}
