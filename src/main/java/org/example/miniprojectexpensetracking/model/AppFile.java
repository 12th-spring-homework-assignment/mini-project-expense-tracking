package org.example.miniprojectexpensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppFile {
    private String fileName;
    private String type;
    private Long size;
}
