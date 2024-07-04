package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Schema(
        description = "User DTO schema"
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    @Schema(description = "User's name")
    @NotEmpty(message = "User name cannot be null")
    public String name;

    @Schema(description = "User's email")
    @NotEmpty(message = "User email cannot be null")
    @Email(message = "User email format is incorrect")
    public String email;

    @Schema(description = "User's phone number")
    @NotEmpty(message = "User phone number cannot be null")
    public String phonenumber;

    @Schema(description = "User's address")
    @NotEmpty(message = "User address cannot be null")
    public String address;

}
