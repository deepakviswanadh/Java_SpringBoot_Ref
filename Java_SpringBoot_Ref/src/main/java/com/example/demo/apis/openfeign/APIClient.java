package com.example.demo.apis.openfeign;

import com.example.demo.DTO.WebClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS2")
public interface APIClient {
    @GetMapping("api/departments/HR01")
    WebClientDTO getDepartment();
}
