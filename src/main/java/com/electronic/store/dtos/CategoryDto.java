package com.electronic.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "Title is required!!")
    @Size(min = 5, message = "Title must be of minimum length 5")
    private String title;

    @NotBlank(message = "Description required!!")
    private String description;

    @NotBlank(message = "Cover image required")
    private String  coverImage;

}
