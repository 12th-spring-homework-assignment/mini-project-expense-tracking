package org.example.miniprojectexpensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectexpensetracking.model.dto.response.AppUserResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Integer expenseId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private AppUserResponse user;
    private Category category;
}
