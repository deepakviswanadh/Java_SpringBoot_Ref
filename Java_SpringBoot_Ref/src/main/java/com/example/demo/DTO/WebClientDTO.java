package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebClientDTO {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
