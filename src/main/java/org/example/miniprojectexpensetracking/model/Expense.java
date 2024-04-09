package org.example.miniprojectexpensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectexpensetracking.model.dto.response.AppUserResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private UUID expenseId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private AppUserResponse user;
    private Category category;
}
