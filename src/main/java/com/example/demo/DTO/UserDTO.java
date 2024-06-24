package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    @NotEmpty(message = "User name cannot be null")
    public String name;

    @NotEmpty(message = "User email cannot be null")
    @Email(message = "User email format is incorrect")
    public String email;

    @NotEmpty(message = "User phone number cannot be null")
    public String phonenumber;


    @NotEmpty(message = "User address cannot be null")
    public String address;

}
