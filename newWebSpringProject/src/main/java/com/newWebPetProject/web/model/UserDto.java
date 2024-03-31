package com.newWebPetProject.web.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    //@Email(message = "password shouldn't be empty")
    private String password;

    @NotNull
    @NotEmpty
    //@Email(message = "Email shouldn't be empty")
    private String email;

}
