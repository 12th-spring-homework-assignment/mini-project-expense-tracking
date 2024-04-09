package org.example.miniprojectexpensetracking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectexpensetracking.model.dto.response.AppUserResponse;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private UUID categoryId;
    private String name;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUserResponse user;
}
