package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.utils.constants.USERS_TABLE;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USERS_TABLE)
@EntityListeners(Auditable.class)
public class UserEntity extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="phone_number")
    public String phonenumber;

    @Column(name = "address")
    public String address;

    @Column(name = "email")
    public String email;

}
