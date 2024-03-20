package org.example.miniprojectexpensetracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Integer userId;
    private String email;
    private String profileImage;
}
