package com.example.demo.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Auditable {
    @Column(name = "created_at", nullable = false)
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Column(name = "created_by", nullable = false)
    private String createdBy="spring-boot";

}
