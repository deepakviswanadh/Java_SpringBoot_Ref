package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebClientDTO {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
