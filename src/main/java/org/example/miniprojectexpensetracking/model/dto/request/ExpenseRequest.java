package org.example.miniprojectexpensetracking.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

    @NotNull
    @DecimalMin(value = "0.00")
    private Double amount;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Min(value = 0)
    private Integer categoryId;
}
