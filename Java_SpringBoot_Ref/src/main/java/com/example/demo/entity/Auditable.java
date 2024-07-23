package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Auditable {

    @Column(name = "created_at", nullable = false)
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Column(name = "created_by", nullable = false)
    private String createdBy="spring-boot";

}
