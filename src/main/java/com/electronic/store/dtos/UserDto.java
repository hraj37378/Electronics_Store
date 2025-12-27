package com.electronic.store.dtos;

import com.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    @Size(min = 3, max = 20, message = "Invalid Name!!")
    private String name;

//    @Email(message = "Invalid Email!!")
    @Pattern(regexp="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid Email!!")
    @NotBlank(message = "Email required!!")
    private String email;

    @NotBlank(message = "Password is required!!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid Gender!!")
    private String gender;

    @NotBlank(message = "Invalid About!!")
    private String about;

    // Custom Validator
    @ImageNameValid
    private String imageName;

}
